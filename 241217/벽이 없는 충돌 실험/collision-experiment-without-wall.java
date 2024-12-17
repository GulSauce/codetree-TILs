import java.util.*;

public class Main {
    private static class Solver {
        private int[] dy = {0, 1, 0, -1}; // R, U, L, D 순서
        private int[] dx = {1, 0, -1, 0};

        private BidInfo[] bids;
        private HashMap<Long, ArrayList<BidInfo>> posMap = new HashMap<>();

        public Solver(BidInfo[] bids) {
            this.bids = bids;
        }

        public void solve() {
            // 좌표 확장 (정수 처리)
            for (BidInfo b : bids) {
                b.x *= 2;
                b.y *= 2;
            }

            int lastCollideTime = -1;

            // 최대 10000초 시뮬레이션 수행
            for (int t = 1; t <= 10000; t++) {
                moveBids();
                mapPositions();

                boolean collided = checkCollision();
                if (collided) {
                    lastCollideTime = t;
                    resolveCollisions();
                }
            }

            System.out.println(lastCollideTime);
        }

        private void moveBids() {
            for (BidInfo b : bids) {
                int dIndex = getDIndex(b.direction);
                b.x += dx[dIndex];
                b.y += dy[dIndex];
            }
        }

        private void mapPositions() {
            posMap.clear();
            for (BidInfo b : bids) {
                long key = toKey(b.x, b.y);
                posMap.computeIfAbsent(key, k -> new ArrayList<>()).add(b);
            }
        }

        private boolean checkCollision() {
            for (ArrayList<BidInfo> list : posMap.values()) {
                if (list.size() > 1) return true;
            }
            return false;
        }

        private void resolveCollisions() {
            ArrayList<BidInfo> survivors = new ArrayList<>();
            for (ArrayList<BidInfo> list : posMap.values()) {
                if (list.size() == 1) {
                    survivors.addAll(list);
                } else {
                    // 충돌한 위치에서 가장 영향력 있는 구슬 찾기
                    BidInfo maxBid = list.get(0);
                    for (int i = 1; i < list.size(); i++) {
                        BidInfo curr = list.get(i);
                        if (curr.weight > maxBid.weight ||
                                (curr.weight == maxBid.weight && curr.number > maxBid.number)) {
                            maxBid = curr;
                        }
                    }
                    survivors.add(maxBid);
                }
            }
            bids = survivors.toArray(new BidInfo[0]);
        }

        private int getDIndex(char d) {
            if (d == 'R') return 0;
            if (d == 'U') return 1;
            if (d == 'L') return 2;
            if (d == 'D') return 3;
            return -1;
        }

        private long toKey(int x, int y) {
            // 키 생성 시 오프셋을 줘서 음수좌표 처리
            return ((long)(x + 3000) << 16) | (y + 3000);
        }
    }

    private static class BidInfo {
        int number, weight;
        int x, y;
        char direction;

        public BidInfo(int number, int x, int y, int w, char d) {
            this.number = number;
            this.x = x;
            this.y = y;
            this.weight = w;
            this.direction = d;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int testCase = 0; testCase < T; testCase++) {
            int N = sc.nextInt();
            BidInfo[] bids = new BidInfo[N];
            for (int i = 0; i < N; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int w = sc.nextInt();
                char d = sc.next().charAt(0);
                bids[i] = new BidInfo(i, x, y, w, d);
            }

            new Solver(bids).solve();
        }
    }
}
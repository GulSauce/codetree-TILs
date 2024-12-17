import java.util.*;

public class Main {
    private static class Solver {
        // 방향: R(오른쪽), U(위), L(왼쪽), D(아래)
        // 확장 좌표: 1칸 = 2씩 이동
        // 원래 0.5칸씩 이동하던 것을 2배 확장해 매초마다 1씩 이동
        // dx, dy 그대로 두고 좌표를 2배 확장한 상태로 시작
        private int[] dy = {0, 1, 0, -1};
        private int[] dx = {1, 0, -1, 0};

        private BidInfo[] bids;
        private HashMap<Long, ArrayList<BidInfo>> posMap = new HashMap<>();

        public Solver(BidInfo[] bids) {
            this.bids = bids;
        }

        public void solve() {
            // 좌표를 2배 확장
            for (BidInfo b : bids) {
                b.x *= 2;
                b.y *= 2;
            }

            int lastCollideTime = -1;
            int noCollisionCount = 0;
            int maxNoCollisionLimit = 2000; 
            // 특정 시간동안 충돌 없으면 종료 (임의 설정, 문제 상황에 따라 조정 가능)

            for (int t = 1; t <= 10000; t++) {
                moveBids(); 
                mapPositions();

                boolean collided = checkCollision();
                if (collided) {
                    lastCollideTime = t;
                    noCollisionCount = 0; 
                    resolveCollisions();
                } else {
                    noCollisionCount++;
                    if (noCollisionCount > maxNoCollisionLimit) {
                        // 오랜 시간 충돌 없음 -> 종료
                        break;
                    }
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
                    // 충돌 발생 시 가장 영향력 큰 구슬 선별 (정렬 대신 선형 탐색)
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
            // x, y는 -2000~2000 정도 범위 가능(확장 후 -2000~2000?)
            // 고유한 long 키 생성
            return ((long)(x + 3000) << 16) + (y + 3000);
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
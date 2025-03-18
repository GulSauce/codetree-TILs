import java.util.Scanner;

public class Main {

    private static class Solver {

        int gridLength;
        int targetTime;
        BidInfo bidInfo;

        int[] dr = {0, -1, 1, 0};
        int[] dc = {1, 0, 0, -1};


        public Solver(
            int N,
            int T,
            BidInfo bidInfo
        ) {
            this.gridLength = N;
            this.targetTime = T;
            this.bidInfo = bidInfo;
        }

        public void solve() {
            for (int elapsedTime = 1; elapsedTime <= targetTime; elapsedTime++) {
                moveBid();
            }
            printAnswer();
        }

        private void printAnswer() {
            System.out.printf("%d %d", bidInfo.row, bidInfo.col);
        }

        private void moveBid() {
            int currentRow = bidInfo.row;
            int currentColumn = bidInfo.col;
            int directionIndex = convertToDirectionIndex(bidInfo.direction);

            int nextRow = currentRow + dr[directionIndex];
            int nextColumn = currentColumn + dc[directionIndex];
            if (isOutOfGrid(nextRow, nextColumn)) {
                int nextDirection = reverseDirectionIndex(directionIndex);
                bidInfo.direction = convertToDirection(nextDirection);
            } else {
                bidInfo.row = nextRow;
                bidInfo.col = nextColumn;
            }
        }

        private int reverseDirectionIndex(int directionIndex) {
            return 3 - directionIndex;
        }

        private boolean isOutOfGrid(int r, int c) {
            return 1 > r || r > gridLength || 1 > c || c > gridLength;
        }

        private char convertToDirection(int directionIndex) {
            if (directionIndex == 0) {
                return 'R';
            }
            if (directionIndex == 1) {
                return 'U';
            }
            if (directionIndex == 2) {
                return 'D';
            }
            if (directionIndex == 3) {
                return 'L';
            }
            throw new IllegalArgumentException();
        }

        private int convertToDirectionIndex(char direction) {
            if (direction == 'R') {
                return 0;
            }
            if (direction == 'U') {
                return 1;
            }
            if (direction == 'D') {
                return 2;
            }
            if (direction == 'L') {
                return 3;
            }
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        int R = sc.nextInt();
        int C = sc.nextInt();
        String D = sc.next();
        new Main.Solver(N, T, new BidInfo(R, C, D)).solve();
    }

    private static class BidInfo {

        int row;
        int col;
        char direction;

        public BidInfo(
            int R,
            int C,
            String D
        ) {
            this.row = R;
            this.col = C;
            this.direction = D.charAt(0);
        }
    }
}
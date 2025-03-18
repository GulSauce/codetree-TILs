import java.util.Scanner;

public class Main {

    private static class Solver {

        int mirrorGridIndex;
        int targetNumber;

        int curRow;
        int curCol;
        int curDirectionIndex;

        int reflectCount = 0;

        int[] dc = {0, -1, 0, 1};
        int[] dr = {1, 0, 1, 0};

        char[][] mirrorGrid;

        public Solver(
            int N,
            char[][] mirrorGrid,
            int K
        ) {
            this.mirrorGridIndex = N;
            this.mirrorGrid = mirrorGrid;
            this.targetNumber = K;
        }

        public void solve() {
            initStart();
            shootLaser();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(reflectCount);
        }

        private void shootLaser() {
            while (true) {
                setCurrentDirectionIndex();
                curRow += dr[curDirectionIndex];
                curCol += dc[curDirectionIndex];
                if (isOutOfGrid()) {
                    break;
                }
                reflectCount++;
            }
        }

        private void setCurrentDirectionIndex() {
            if (mirrorGrid[curRow][curCol] == '\\') {
                curDirectionIndex = 3 - curDirectionIndex;
                return;
            }
            if (curDirectionIndex == 0) {
                curDirectionIndex = 1;
                return;
            }
            if (curDirectionIndex == 1) {
                curDirectionIndex = 0;
                return;
            }
            if (curDirectionIndex == 2) {
                curDirectionIndex = 3;
                return;
            }
            if (curDirectionIndex == 3) {
                curDirectionIndex = 2;
                return;
            }
        }

        private boolean isOutOfGrid() {
            return 1 > curRow || curRow > mirrorGridIndex || 1 > curCol || curCol > mirrorGridIndex;
        }

        private void initStart() {
            curDirectionIndex = (targetNumber - 1) / mirrorGridIndex;
            if (curDirectionIndex == 0) {
                curRow = 1;
                curCol = targetNumber - curDirectionIndex * mirrorGridIndex;
            }
            if (curDirectionIndex == 1) {
                curCol = mirrorGridIndex;
                curRow = targetNumber - curDirectionIndex * mirrorGridIndex;
            }
            if (curDirectionIndex == 2) {
                curRow = mirrorGridIndex;
                curCol = mirrorGridIndex - (targetNumber - curDirectionIndex * mirrorGridIndex) + 1;
            }
            if (curDirectionIndex == 3) {
                curCol = 1;
                curRow = mirrorGridIndex - (targetNumber - curDirectionIndex * mirrorGridIndex) + 1;
            }
            reflectCount++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[][] mirrors = new char[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            String mirrorString = sc.next();
            for (int col = 1; col <= N; col++) {
                mirrors[row][col] = mirrorString.charAt(col - 1);
            }
        }
        int K = sc.nextInt();
        new Main.Solver(N, mirrors, K).solve();
    }
}
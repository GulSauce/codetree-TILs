import java.util.Scanner;

public class Main {

    private static class Solver {

        int gridIndex;

        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        int[][] grid;
        int[][] dp;

        public Solver(
            int N,
            int[][] grid
        ) {
            this.gridIndex = N;
            this.grid = grid;
            this.dp = new int[N + 1][N + 1];
        }

        public void solve() {
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int[] array : dp) {
                for (int value : array) {
                    answer = Math.max(answer, value);
                }
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int row = 1; row <= gridIndex; row++) {
                for (int col = 1; col <= gridIndex; col++) {
                    memoize(row, col);
                }
            }
        }

        private void memoize(int prevRow, int prevCol) {
            for (int i = 0; i < dr.length; i++) {
                int curRow = prevRow + dr[i];
                int curCol = prevCol + dc[i];
                if (isOutOfRange(curRow, curCol)) {
                    continue;
                }
                if (grid[curRow][curCol] <= grid[prevRow][prevCol]) {
                    continue;
                }
                if (dp[curRow][curCol] == 0) {
                    memoize(curRow, curCol);
                }
                dp[prevRow][prevCol] = Math.max(dp[prevRow][prevCol], dp[curRow][curCol] + 1);
            }
            if (dp[prevRow][prevCol] == 0) {
                dp[prevRow][prevCol] = 1;
            }
        }

        private boolean isOutOfRange(int row, int col) {
            return 1 > row || row > gridIndex || 1 > col || col > gridIndex;
        }

    }

    public static void main(String[] args) {

        int N;
        int[][] grid;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        grid = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                grid[row][col] = sc.nextInt();
            }
        }
        sc.close();

        new Main.Solver(N, grid).solve();
    }
}
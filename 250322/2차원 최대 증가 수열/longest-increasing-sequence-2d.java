import java.util.Scanner;

public class Main {

    private static class Solver {

        int maxRow;
        int maxCol;
        int[][] grid;
        int[][] dp;

        public Solver(
            int N,
            int M,
            int[][] grid
        ) {
            this.maxRow = N;
            this.maxCol = M;
            this.grid = grid;
            this.dp = new int[N + 1][M + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int row = 1; row <= maxRow; row++) {
                for (int col = 1; col <= maxCol; col++) {
                    answer = Math.max(answer, dp[row][col]);
                }
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int row = 1; row <= maxRow; row++) {
                for (int col = 1; col <= maxCol; col++) {
                    setDPValueAt(row, col);
                }
            }
        }

        private void setDPValueAt(int curRow, int curCol) {
            for (int row = 1; row < curRow; row++) {
                for (int col = 1; col < curCol; col++) {
                    if (grid[curRow][curCol] <= grid[row][col]) {
                        continue;
                    }
                    dp[curRow][curCol] = Math.max(dp[curRow][curCol], dp[row][col] + 1);
                }
            }
        }

        private void initDP() {
            dp[1][1] = 1;
        }

    }

    public static void main(String[] args) {
        int N, M;
        int[][] grid;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        grid = new int[N + 1][M + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                grid[row][col] = sc.nextInt();
            }
        }
        sc.close();

        new Solver(N, M, grid).solve();
    }
}
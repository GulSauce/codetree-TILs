import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int gridIndex;
        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        final int MAX_NUMBER = 100;

        int[][] grid;
        int[][][] dp;

        public Solver(
            int N,
            int[][] grid
        ) {
            this.gridIndex = N;
            this.grid = grid;
            this.dp = new int[N + 1][N + 1][MAX_NUMBER + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = Integer.MAX_VALUE;
            for (int maxNumber = 1; maxNumber <= MAX_NUMBER; maxNumber++) {
                int minNumber = dp[gridIndex][gridIndex][maxNumber];
                if (minNumber == NOT_ALLOCATED) {
                    continue;
                }
                answer = Math.min(answer, maxNumber - minNumber);
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int row = 2; row <= gridIndex; row++) {
                for (int col = 2; col <= gridIndex; col++) {
                    for (int number = 1; number <= MAX_NUMBER; number++) {
                        if (dp[row][col - 1][number] == NOT_ALLOCATED) {
                            continue;
                        }
                        int currentMaxNumber = Math.max(grid[row][col], number);
                        int currentMinNumber = Math.min(grid[row][col], dp[row][col - 1][number]);
                        dp[row][col][currentMaxNumber] = Math.min(
                            dp[row][col][currentMaxNumber],
                            currentMinNumber
                        );
                    }

                    for (int number = 1; number <= MAX_NUMBER; number++) {
                        if (dp[row - 1][col][number] == NOT_ALLOCATED) {
                            continue;
                        }
                        int currentMaxNumber = Math.max(grid[row][col], number);
                        int currentMinNumber = Math.min(grid[row][col], dp[row - 1][col][number]);
                        dp[row][col][currentMaxNumber] = Math.min(
                            dp[row][col][currentMaxNumber],
                            currentMinNumber
                        );
                    }
                }
            }
        }

        private void initDP() {
            for (int[][] arrays : dp) {
                for (int[] array : arrays) {
                    Arrays.fill(array, NOT_ALLOCATED);
                }
            }

            dp[1][1][grid[1][1]] = grid[1][1];
            for (int row = 2; row <= gridIndex; row++) {
                for (int number = 1; number <= MAX_NUMBER; number++) {
                    if (dp[row - 1][1][number] == NOT_ALLOCATED) {
                        continue;
                    }
                    int currentMaxNumber = Math.max(grid[row][1], number);
                    int currentMinNumber = Math.min(grid[row][1], dp[row - 1][1][number]);
                    dp[row][1][currentMaxNumber] = Math.min(
                        dp[row][1][currentMaxNumber],
                        currentMinNumber
                    );
                }
            }
            for (int col = 2; col <= gridIndex; col++) {
                for (int number = 1; number <= MAX_NUMBER; number++) {
                    if (dp[1][col - 1][number] == NOT_ALLOCATED) {
                        continue;
                    }
                    int currentMaxNumber = Math.max(grid[1][col], number);
                    int currentMinNumber = Math.min(grid[1][col], dp[1][col - 1][number]);
                    dp[1][col][currentMaxNumber] = Math.min(
                        dp[1][col][currentMaxNumber],
                        currentMinNumber
                    );
                }
            }
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

        new Solver(N, grid).solve();
    }
}
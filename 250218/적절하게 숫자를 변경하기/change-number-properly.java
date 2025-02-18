import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int numbersIndex;
        int maxDiffCount;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<Integer> numbers;

        int[][][] dp;

        public Solver(
            int N,
            int M,
            List<Integer> numbers
        ) {
            this.numbersIndex = N - 1;
            this.maxDiffCount = M;
            this.numbers = numbers;
            this.dp = new int[N][M + 1][5];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int i = 0; i <= maxDiffCount; i++) {
                for (int k = 1; k <= 4; k++) {
                    answer = Math.max(answer, dp[numbersIndex][i][k]);
                }
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 1; i <= numbersIndex; i++) {
                int curNumber = numbers.get(i);
                for (int j = 0; j <= maxDiffCount; j++) {
                    for (int k = 1; k <= 4; k++) {
                        if (dp[i - 1][j][k] == NOT_ALLOCATED) {
                            continue;
                        }
                        for (int l = 1; l <= 4; l++) {
                            if (k == curNumber) {
                                if (k == l) {
                                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][l] + 1);
                                } else {
                                    if (j < maxDiffCount) {
                                        dp[i][j + 1][k] = Math.max(dp[i][j + 1][k],
                                            dp[i - 1][j][l] + 1);
                                    }
                                }
                            } else {
                                if (k == l) {
                                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][l]);
                                } else {
                                    if (j < maxDiffCount) {
                                        dp[i][j + 1][k] = Math.max(dp[i][j + 1][k],
                                            dp[i - 1][j][l]);
                                    }
                                }
                            }
                        }

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
            for (int i = 1; i <= 4; i++) {
                if (numbers.get(0) == i) {
                    dp[0][0][i] = 1;
                } else {
                    dp[0][0][i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }

        new Solver(N, M, numbers).solve();
    }
}
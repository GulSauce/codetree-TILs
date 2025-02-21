import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static class Solver {

        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        String N;
        String M;

        int[][] dp = new int[1000][1000];

        public Solver(
            String N,
            String M
        ) {
            this.N = N;
            this.M = M;
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(dp[N.length() - 1][M.length() - 1]);
        }

        private void calcDP() {
            for (int i = 1; i < N.length(); i++) {
                for (int j = 1; j < M.length(); j++) {
                    if (N.charAt(i) == M.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                        continue;
                    }
                    int insert = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    dp[i][j] = Math.min(dp[i][j], insert);
                    dp[i][j] = Math.min(dp[i][j], delete);
                }
            }
        }

        private void initDP() {
            for (int[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            if (N.charAt(0) == M.charAt(0)) {
                dp[0][0] = 0;
            } else {
                dp[0][0] = 1;
            }

            for (int i = 1; i < N.length(); i++) {
                if (N.charAt(i) == M.charAt(0)) {
                    dp[i][0] = i;
                    continue;
                }
                int insert = i + 1 + 1;
                int delete = dp[i - 1][0] + 1;
                dp[i][0] = Math.min(dp[i][0], insert);
                dp[i][0] = Math.min(dp[i][0], delete);
            }
            for (int i = 1; i < M.length(); i++) {
                if (M.charAt(i) == N.charAt(0)) {
                    dp[0][i] = i;
                    continue;
                }
                int insert = i + 1 + 1;
                int delete = i + 1 + 1;
                dp[0][i] = Math.min(dp[0][i], insert);
                dp[0][i] = Math.min(dp[0][i], delete);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.next();
        String M = sc.next();
        new Solver(N, M).solve();
    }
}
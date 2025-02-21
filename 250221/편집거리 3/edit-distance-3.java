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
                    dp[i][j] = Math.min(insert, delete);
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
                dp[i][0] = Math.min(insert, delete);
            }
            for (int i = 1; i < M.length(); i++) {
                if (N.charAt(0) == M.charAt(i)) {
                    dp[0][i] = i;
                    continue;
                }
                int insert = dp[0][i - 1] + 1;
                int delete = i + 1 + 1;
                dp[0][i] = Math.min(insert, delete);
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
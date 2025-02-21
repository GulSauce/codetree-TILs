import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static class Solver {

        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        String A;
        String B;

        int[][] dp = new int[1000][1000];

        public Solver(
            String A,
            String B
        ) {
            this.A = A;
            this.B = B;
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(dp[A.length() - 1][B.length() - 1]);
        }

        private void calcDP() {
            for (int i = 1; i < A.length(); i++) {
                for (int j = 1; j < B.length(); j++) {
                    if (A.charAt(i) == B.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                        continue;
                    }
                    int insert = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int change = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(dp[i][j], insert);
                    dp[i][j] = Math.min(dp[i][j], delete);
                    dp[i][j] = Math.min(dp[i][j], change);
                }
            }
        }

        private void initDP() {
            for (int[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }

            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) == B.charAt(0)) {
                    dp[i][0] = i;
                } else {
                    dp[i][0] = i + 1;
                }
            }

            for (int i = 0; i < B.length(); i++) {
                if (B.charAt(i) == A.charAt(0)) {
                    dp[0][i] = i;
                } else {
                    dp[0][i] = i + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        new Main.Solver(A, B).solve();
    }
}

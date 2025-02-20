import java.util.Scanner;

public class Main {

    private static class Solver {

        String A;
        String B;

        int[][] dp;

        public Solver(
            String A,
            String B
        ) {
            this.A = A;
            this.B = B;
            this.dp = new int[A.length()][B.length()];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(dp[A.length() - 1][B.length() - 1]);
        }

        private void printDP() {
            for (int[] array : dp) {
                for (int value : array) {
                    System.out.printf("%d ", value);
                }
                System.out.println();
            }
        }

        private void calcDP() {
            for (int i = 1; i < A.length(); i++) {
                for (int j = 1; j < B.length(); j++) {
                    if (A.charAt(i) == B.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
        }

        private void initDP() {
            if (A.charAt(0) == B.charAt(0)) {
                dp[0][0] = 1;
            }
            for (int i = 1; i < A.length(); i++) {
                if (B.charAt(0) == A.charAt(i)) {
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = dp[i - 1][0];

                }
            }
            for (int i = 1; i < B.length(); i++) {
                if (A.charAt(0) == B.charAt(i)) {
                    dp[0][i] = 1;
                } else {
                    dp[0][i] = dp[0][i - 1];

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
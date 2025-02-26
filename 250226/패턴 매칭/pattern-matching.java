import java.util.Scanner;

public class Main {

    private static class Solver {

        String target;
        String pattern;

        boolean[][] dp;

        public Solver(
            String S,
            String P
        ) {
            this.target = S;
            this.pattern = P;
            this.dp = new boolean[S.length()][P.length()];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(dp[target.length() - 1][pattern.length() - 1]);
        }


        private void calcDP() {
            for (int i = 1; i < target.length(); i++) {
                for (int j = 1; j < pattern.length(); j++) {
                    if (pattern.charAt(j) == '*') {
                        if (0 <= j - 2) {
                            if (isMatch(target.charAt(i), pattern.charAt(j - 1))) {
                                dp[i][j] = dp[i - 1][j];
                            }
                            if (!dp[i][j]) {
                                dp[i][j] = dp[i][j - 1];
                            }
                        } else {
                            if (isMatch(target.charAt(i), pattern.charAt(j - 1))) {
                                dp[i][j] = dp[i - 1][j];
                            }
                        }
                    } else if (isMatch(target.charAt(i), pattern.charAt(j))) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        private void initDP() {
            if (isMatch(target.charAt(0), pattern.charAt(0))) {
                dp[0][0] = true;
            }

            for (int i = 1; i < pattern.length(); i++) {
                if (pattern.charAt(i) == '*') {
                    if (isMatch(target.charAt(0), pattern.charAt(i - 1))) {
                        dp[0][i] = true;
                    } else if (0 <= i - 2) {
                        dp[0][i] = dp[0][i - 2];
                    }
                }

            }
        }

        private boolean isMatch(char target, char pattern) {
            return target == pattern || pattern == '.';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        String P = sc.next();
        new Main.Solver(S, P).solve();
    }
}
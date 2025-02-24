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

        private void printDP() {
            for (boolean[] array : dp) {
                for (boolean value : array) {
                    System.out.print(value);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }

        private void printAnswer() {
            System.out.println(dp[target.length() - 1][pattern.length() - 1]);
        }

        private void calcDP() {
            for (int i = 1; i < target.length(); i++) {
                for (int j = 1; j < pattern.length(); j++) {
                    if (pattern.charAt(j) == '*') {
                        if (dp[i][j]) {
                            continue;
                        }
                        for (int k = i; k < target.length(); k++) {
                            if (isMatch(target.charAt(k), pattern.charAt(j - 1))) {
                                dp[k][j] = dp[i - 1][j - 1];
                            }
                        }
                        continue;
                    }
                    if (isMatch(target.charAt(i), pattern.charAt(j))) {
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
                    dp[0][i] = dp[0][i - 1];
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
        new Solver(S, P).solve();
    }
}
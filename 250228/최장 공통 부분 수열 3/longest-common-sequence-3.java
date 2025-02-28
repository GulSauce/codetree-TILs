import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static class Solver {

        String A;
        String B;

        Stack<Character> answers = new Stack<>();
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
            backTrack();
            while (!answers.isEmpty()) {
                System.out.print(answers.pop());
            }
        }

        private void backTrack() {
            int i = A.length() - 1, j = B.length() - 1;
            while (i >= 0 && j >= 0) {
                if (A.charAt(i) == B.charAt(j)) {
                    answers.add(A.charAt(i));
                    i--;
                    j--;
                    continue;
                }
                if (i == 0) {
                    j--;
                    continue;
                }
                if (j == 0) {
                    i--;
                    continue;
                }
                if (dp[i - 1][j] >= dp[i][j - 1]) {
                    i--;
                    continue;
                }
                j--;
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
                if (A.charAt(i) == B.charAt(0)) {
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = dp[i - 1][0];
                }
            }
            for (int i = 1; i < B.length(); i++) {
                if (B.charAt(i) == A.charAt(0)) {
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
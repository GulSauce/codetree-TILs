import java.util.Scanner;

public class Main {


    private static class Solver {

        final int R = 1000000007;

        int stairLength;

        int[][] dp = new int[1001][11];

        public Solver(
            int N
        ) {
            this.stairLength = N;
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int i = 0; i <= 9; i++) {
                answer += dp[stairLength][i];
                answer %= R;
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 2; i <= stairLength; i++) {
                for (int j = 0; j <= 9; j++) {
                    if (j == 0) {
                        dp[i][j] = dp[i - 1][1];
                        continue;
                    }
                    if (j == 9) {
                        dp[i][j] = dp[i - 1][8];
                        continue;
                    }
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                    dp[i][j] %= R;
                }
            }
        }

        private void initDP() {
            for (int i = 1; i <= 9; i++) {
                dp[1][i] = 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        new Solver(N).solve();
    }
}
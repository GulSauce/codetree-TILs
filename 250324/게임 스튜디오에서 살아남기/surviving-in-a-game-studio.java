import java.util.Scanner;

public class Main {

    private static class Solver {

        int surviveDay;
        final int MAX_BAD = 2;
        final int MAX_TERRIBLE = 2;
        final int REMAINDER = 1_000_000_007;
        int[][][] dp;

        public Solver(
            int N
        ) {
            this.surviveDay = N;
            this.dp = new int[N + 1][MAX_BAD + 1][MAX_TERRIBLE + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int[] array : dp[surviveDay]) {
                for (int value : array) {
                    answer += value;
                    answer %= REMAINDER;
                }
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int day = 0; day < surviveDay; day++) {
                for (int bad = 0; bad <= MAX_BAD; bad++) {
                    for (int terrible = 0; terrible <= MAX_TERRIBLE; terrible++) {
                        if (bad + 1 <= MAX_BAD) {
                            dp[day + 1][bad + 1][terrible] += dp[day][bad][terrible];
                            dp[day + 1][bad + 1][terrible] %= REMAINDER;
                        }
                        if (terrible + 1 <= MAX_BAD) {
                            dp[day + 1][0][terrible + 1] += dp[day][bad][terrible];
                            dp[day + 1][0][terrible + 1] %= REMAINDER;
                        }
                        dp[day + 1][0][terrible] += dp[day][bad][terrible];
                        dp[day + 1][0][terrible] %= REMAINDER;
                    }
                }
            }
        }

        private void initDP() {
            dp[0][0][0] = 1;
        }
    }

    public static void main(String[] args) {
        int N;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();

        new Solver(N).solve();
    }
}
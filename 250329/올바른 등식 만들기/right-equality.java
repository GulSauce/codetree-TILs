import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int targetSum;
        int numbersIndex;
        final int OFFSET = 20;
        final long NOT_ALLOCATED = 0;

        long[][] dp;

        List<Integer> numbers;

        public Solver(
            int N,
            int M,
            List<Integer> numbers
        ) {
            this.numbersIndex = N;
            this.targetSum = M;
            this.numbers = numbers;
            this.dp = new long[N + 1][41];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(dp[numbersIndex][targetSum + OFFSET]);
        }

        private void calcDP() {
            for (int i = 0; i < numbersIndex; i++) {
                int nextNum = numbers.get(i + 1);
                for (int curSum = -20; curSum <= 20; curSum++) {
                    if (dp[i][curSum + OFFSET] == NOT_ALLOCATED) {
                        continue;
                    }
                    if (-20 <= curSum - nextNum) {
                        dp[i + 1][curSum - nextNum + OFFSET] += dp[i][curSum + OFFSET];
                    }
                    if (curSum + nextNum <= 20) {
                        dp[i + 1][curSum + nextNum + OFFSET] += dp[i][curSum + OFFSET];
                    }
                }
            }
        }

        private void initDP() {
            for (long[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            dp[0][OFFSET] = 1;
        }
    }

    public static void main(String[] args) {
        int N, M;
        List<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        numbers.add(-1);
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Main.Solver(N, M, numbers).solve();
    }
}
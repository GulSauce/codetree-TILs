import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int numbersIndex;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;
        final int OFFSET = 100_000;
        final int MAX_DIFF = 200_000;

        List<Integer> numbers;

        int[][] dp;

        public Solver(
            int N,
            List<Integer> numbers
        ) {
            this.numbersIndex = N - 1;
            this.numbers = numbers;
            this.dp = new int[N][MAX_DIFF + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(dp[numbersIndex][OFFSET]);
        }

        private void calcDP() {
            for (int i = 1; i <= numbersIndex; i++) {
                int currentValue = numbers.get(i);
                for (int targetDiff = MAX_DIFF; targetDiff >= 0; targetDiff--) {
                    if (targetDiff - currentValue < 0) {
                        continue;
                    }
                    if (dp[i - 1][targetDiff - currentValue] == NOT_ALLOCATED) {
                        continue;
                    }
                    dp[i][targetDiff] = Math.max(
                        dp[i][targetDiff],
                        dp[i - 1][targetDiff - currentValue] + currentValue);

                    dp[i][targetDiff - 2 * currentValue] = Math.max(
                        dp[i][targetDiff - 2 * currentValue],
                        dp[i - 1][targetDiff - currentValue]);
                }

                for (int targetDiff = MAX_DIFF; targetDiff >= 0; targetDiff--) {
                    if (dp[i - 1][targetDiff] == NOT_ALLOCATED) {
                        continue;
                    }

                    dp[i][targetDiff] = Math.max(
                        dp[i][targetDiff],
                        dp[i - 1][targetDiff]);
                }
            }
        }

        private void initDP() {
            for (int[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            int firstValue = numbers.get(0);
            dp[0][firstValue + OFFSET] = firstValue;
            dp[0][-firstValue + OFFSET] = 0;
            dp[0][0 + OFFSET] = 0;
        }
    }

    public static void main(String[] args) {
        int N;
        List<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(N, numbers).solve();
    }
}
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
            this.numbersIndex = N - 1;
            this.targetSum = M;
            this.numbers = numbers;
            this.dp = new long[N][41];
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
            for (int i = 1; i <= numbersIndex; i++) {
                int curNum = numbers.get(i);
                for (int prevSum = -20; prevSum <= 20; prevSum++) {
                    if (dp[i - 1][prevSum + OFFSET] == NOT_ALLOCATED) {
                        continue;
                    }
                    if (-20 <= prevSum - curNum) {
                        dp[i][prevSum - curNum + OFFSET] += dp[i - 1][prevSum + OFFSET];
                    }
                    if (prevSum + curNum <= 20) {
                        dp[i][prevSum + curNum + OFFSET] += dp[i - 1][prevSum + OFFSET];
                    }
                }
            }
        }

        private void initDP() {
            for (long[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            dp[0][-numbers.get(0) + OFFSET] = 1;
            dp[0][numbers.get(0) + OFFSET] = 1;
        }
    }

    public static void main(String[] args) {
        int N, M;
        List<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Main.Solver(N, M, numbers).solve();
    }
}
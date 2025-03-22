import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int numbersIndex;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;
        int[] dp;
        List<Integer> numbers;

        public Solver(
            int N,
            List<Integer> numbers
        ) {
            this.numbersIndex = N - 1;
            this.numbers = numbers;
            this.dp = new int[N];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = Integer.MIN_VALUE;
            for (int value : dp) {
                answer = Math.max(answer, value);
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 1; i <= numbersIndex; i++) {
                int prevSum = dp[i - 1];
                int curNum = numbers.get(i);
                if (prevSum + curNum < curNum) {
                    dp[i] = curNum;
                    continue;
                }
                dp[i] = dp[i - 1] + curNum;
            }
        }

        private void initDP() {
            Arrays.fill(dp, NOT_ALLOCATED);
            dp[0] = numbers.get(0);
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
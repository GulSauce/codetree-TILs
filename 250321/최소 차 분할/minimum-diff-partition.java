import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int numbersSum;
        int numbersIndex;
        final int MAX_SUM = 1_000_000;

        List<Integer> numbers;
        boolean[] dp;

        public Solver(
            int N,
            List<Integer> numbers
        ) {
            this.numbersIndex = N;
            this.numbers = numbers;
            this.dp = new boolean[MAX_SUM + 1];
        }

        public void solve() {
            setNumbersSum();
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = Integer.MAX_VALUE;
            for (int aSum = 0; aSum <= numbersSum; aSum++) {
                if (!dp[aSum]) {
                    continue;
                }
                int bSum = numbersSum - aSum;
                int diff = Math.abs(aSum - bSum);
                answer = Math.min(answer, diff);
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int number : numbers) {
                for (int sum = MAX_SUM; sum >= 1; sum--) {
                    if (sum - number < 0) {
                        continue;
                    }
                    if (!dp[sum - number]) {
                        continue;
                    }
                    dp[sum] = true;
                }
            }
        }

        private void initDP() {
            dp[0] = true;
        }

        private void setNumbersSum() {
            for (int number : numbers) {
                numbersSum += number;
            }
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
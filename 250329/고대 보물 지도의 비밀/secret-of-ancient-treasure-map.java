import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int maxConsecutiveNegativeCount;
        int numbersIndex;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;
        int[][] dp;

        List<Integer> numbers;

        public Solver(
            int N,
            int K,
            List<Integer> numbers
        ) {
            this.numbersIndex = N;
            this.maxConsecutiveNegativeCount = K;
            this.dp = new int[N + 1][K + 1];
            this.numbers = numbers;
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int i = 0; i <= numbersIndex; i++) {
                for (int count = 0; count <= maxConsecutiveNegativeCount; count++) {
                    answer = Math.max(answer, dp[i][count]);
                }
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 0; i < numbersIndex; i++) {
                int nextNum = numbers.get(i + 1);
                if (nextNum < 0) {
                    for (int count = 0; count < maxConsecutiveNegativeCount; count++) {
                        if (dp[i][count] == NOT_ALLOCATED) {
                            continue;
                        }
                        dp[i + 1][count + 1] = Math.max(dp[i + 1][count + 1],
                            dp[i][count] + nextNum);
                    }
                    continue;
                }
                for (int count = 0; count <= maxConsecutiveNegativeCount; count++) {
                    if (dp[i][count] == NOT_ALLOCATED) {
                        continue;
                    }
                    dp[i + 1][count] = Math.max(dp[i + 1][count], dp[i][count] + nextNum);
                }
            }
        }

        private void initDP() {
            for (int[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            for (int i = 1; i <= numbersIndex; i++) {
                int num = numbers.get(i);
                if (num < 0) {
                    dp[i][1] = num;
                    continue;
                }
                dp[i][0] = num;
            }
            dp[0][0] = 0;
        }
    }

    public static void main(String[] args) {
        int N, K;
        List<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        numbers.add(-1);
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Main.Solver(N, K, numbers).solve();
    }
}
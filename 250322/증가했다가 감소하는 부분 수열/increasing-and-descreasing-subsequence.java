import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int numbersIndex;
        final int INCREASE_FLAG = 0;
        final int DECREASE_FLAG = 1;
        int[][] dp;
        List<Integer> numbers;

        public Solver(
            int N,
            List<Integer> numbers
        ) {
            this.dp = new int[N][2];
            this.numbersIndex = N - 1;
            this.numbers = numbers;
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int[] array : dp) {
                for (int value : array) {
                    answer = Math.max(answer, value);
                }
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int cur = 1; cur <= numbersIndex; cur++) {
                int curNum = numbers.get(cur);
                for (int prev = 0; prev < cur; prev++) {
                    int prevNum = numbers.get(prev);
                    if (curNum == prevNum) {
                        continue;
                    }
                    if (prevNum < curNum) {
                        dp[cur][INCREASE_FLAG] = Math.max(dp[cur][INCREASE_FLAG],
                            dp[prev][INCREASE_FLAG] + 1);
                        dp[cur][DECREASE_FLAG] = Math.max(dp[cur][INCREASE_FLAG],
                            dp[prev][INCREASE_FLAG] + 1);
                        continue;
                    }
                    if (curNum < prevNum) {
                        dp[cur][DECREASE_FLAG] = Math.max(dp[cur][DECREASE_FLAG],
                            dp[prev][INCREASE_FLAG] + 1);
                        dp[cur][DECREASE_FLAG] = Math.max(dp[cur][DECREASE_FLAG],
                            dp[prev][DECREASE_FLAG] + 1);
                    }
                }
            }
        }

        private void initDP() {
            for (int i = 0; i <= numbersIndex; i++) {
                dp[i][INCREASE_FLAG] = 1;
                dp[i][DECREASE_FLAG] = 1;
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
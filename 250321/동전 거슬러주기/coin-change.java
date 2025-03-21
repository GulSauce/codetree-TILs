import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int targetSum;
        int coinsIndex;

        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        List<Integer> coins;

        int[] dp;

        public Solver(
            int N,
            int M,
            List<Integer> coins
        ) {
            this.coinsIndex = N;
            this.coins = coins;
            this.targetSum = M;
            this.dp = new int[M + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            if (dp[targetSum] == NOT_ALLOCATED) {
                System.out.println(-1);
                return;
            }
            System.out.println(dp[targetSum]);
        }

        private void calcDP() {
            for (int i = 1; i <= targetSum; i++) {
                for (int value : coins) {
                    if (i < value) {
                        continue;
                    }
                    if (dp[i - value] == NOT_ALLOCATED) {
                        continue;
                    }
                    dp[i] = Math.min(dp[i], dp[i - value] + 1);
                }
            }
        }

        private void initDP() {
            Arrays.fill(dp, NOT_ALLOCATED);
            dp[0] = 0;
        }
    }

    public static void main(String[] args) {
        int N, M;
        List<Integer> coins = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            coins.add(sc.nextInt());
        }
        sc.close();

        new Main.Solver(N, M, coins).solve();
    }
}
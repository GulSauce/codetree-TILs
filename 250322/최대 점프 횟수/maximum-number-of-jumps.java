import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int jumpCountsIndex;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        int[] dp;
        List<Integer> jumpCounts;

        public Solver(
            int N,
            List<Integer> jumpCounts
        ) {
            this.jumpCountsIndex = N - 1;
            this.jumpCounts = jumpCounts;
            this.dp = new int[N];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int value : dp) {
                answer = Math.max(answer, value);
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 1; i <= jumpCountsIndex; i++) {
                for (int prev = 0; prev < i; prev++) {
                    if (dp[prev] == NOT_ALLOCATED) {
                        continue;
                    }
                    int diff = i - prev;
                    if (jumpCounts.get(prev) < diff) {
                        continue;
                    }
                    dp[i] = Math.max(dp[i], dp[prev] + 1);
                }
            }
        }

        private void initDP() {
            Arrays.fill(dp, NOT_ALLOCATED);
            dp[0] = 0;
        }
    }

    public static void main(String[] args) {
        int N;
        List<Integer> jumpCounts = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            jumpCounts.add(sc.nextInt());
        }
        sc.close();

        new Solver(N, jumpCounts).solve();
    }
}
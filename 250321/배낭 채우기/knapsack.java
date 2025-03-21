import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int gemCount;
        int maxWeight;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<GemInfo> gemInfos;

        int[] dp;

        public Solver(
            int N,
            int M,
            List<GemInfo> gemInfos
        ) {
            this.gemCount = N;
            this.maxWeight = M;
            this.gemInfos = gemInfos;
            this.dp = new int[M + 1];
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
            for (int i = 1; i <= gemCount; i++) {
                for (int w = maxWeight; w >= 1; w--) {
                    int currentValue = gemInfos.get(i).value;
                    int currentWeight = gemInfos.get(i).weight;
                    if (w - currentWeight < 0) {
                        continue;
                    }
                    if (dp[w - currentWeight] == NOT_ALLOCATED) {
                        continue;
                    }
                    dp[w] = Math.max(dp[w], dp[w - currentWeight] + currentValue);
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
        List<GemInfo> gemInfos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        gemInfos.add(new GemInfo(-1, -1));
        for (int i = 0; i < N; i++) {
            gemInfos.add(new GemInfo(sc.nextInt(), sc.nextInt()));
        }
        sc.close();

        new Solver(N, M, gemInfos).solve();
    }

    private static class GemInfo {

        int weight;
        int value;

        public GemInfo(
            int w,
            int v
        ) {
            this.weight = w;
            this.value = v;
        }
    }
}
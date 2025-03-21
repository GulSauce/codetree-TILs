import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int maxLength;
        List<Integer> pricePerLengthInfos;

        int[] dp;

        public Solver(
            int N,
            List<Integer> pricePerLengthInfos
        ) {
            this.maxLength = N;
            this.pricePerLengthInfos = pricePerLengthInfos;
            this.dp = new int[N + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(dp[maxLength]);
        }

        private void calcDP() {
            for (int cur = 1; cur <= maxLength; cur++) {
                for (int prev = 1; prev <= cur; prev++) {
                    dp[cur] = Math.max(dp[cur],
                        dp[cur - prev] + pricePerLengthInfos.get(prev));
                }
            }
        }

        private void initDP() {
            for (int i = 1; i <= maxLength; i++) {
                dp[i] = pricePerLengthInfos.get(i);
            }
        }
    }

    public static void main(String[] args) {
        int N;
        List<Integer> pricePerLengthInfos;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        pricePerLengthInfos = new ArrayList<>();
        pricePerLengthInfos.add(-1);
        for (int i = 1; i <= N; i++) {
            pricePerLengthInfos.add(sc.nextInt());
        }
        sc.close();

        new Solver(N, pricePerLengthInfos).solve();
    }
}
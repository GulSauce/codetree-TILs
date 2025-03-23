import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int cardSetsIndex;
        int maxSelectCount;
        int[][] dp;

        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<CardSet> cardSets;

        public Solver(
            int N,
            List<CardSet> cardSets
        ) {
            this.cardSetsIndex = 2 * N - 1;
            this.maxSelectCount = N;
            this.cardSets = cardSets;
            this.dp = new int[2 * N][N + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(dp[cardSetsIndex][maxSelectCount]);
        }

        private void calcDP() {
            for (int i = 1; i <= cardSetsIndex; i++) {
                for (int red = 0; red <= maxSelectCount; red++) {
                    if (dp[i - 1][red] == NOT_ALLOCATED) {
                        continue;
                    }
                    if (red < maxSelectCount) {
                        dp[i][red + 1] = Math.max(dp[i][red + 1],
                            dp[i - 1][red] + cardSets.get(i).red);
                    }
                    dp[i][red] = Math.max(dp[i][red], dp[i - 1][red] + cardSets.get(i).blue);
                }
            }
        }

        private void initDP() {
            for (int[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            dp[0][1] = cardSets.get(0).red;
            dp[0][0] = cardSets.get(0).blue;
        }
    }

    public static void main(String[] args) {
        int N;
        List<CardSet> cardSets = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < 2 * N; i++) {
            int red = sc.nextInt();
            int blue = sc.nextInt();
            cardSets.add(new CardSet(red, blue));
        }
        sc.close();

        new Main.Solver(N, cardSets).solve();
    }

    private static class CardSet {

        int red;
        int blue;

        public CardSet(
            int red,
            int blue
        ) {
            this.red = red;
            this.blue = blue;
        }
    }
}

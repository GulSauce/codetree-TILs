import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        List<CardSet> cardSets = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        cardSets.add(new CardSet(-1, -1));
        for (int i = 0; i < 2 * N; i++) {
            int red = sc.nextInt();
            int blue = sc.nextInt();
            cardSets.add(new CardSet(red, blue));
        }
        sc.close();

        new Main.Solver(N, cardSets).solve();
    }

    private static class Solver {

        final int NOT_ALLOCATED = Integer.MIN_VALUE;
        int cardSetsIndex;
        int maxSelectCount;
        int[][] dp;
        List<CardSet> cardSets;

        public Solver(
                int N,
                List<CardSet> cardSets
        ) {
            this.cardSetsIndex = 2 * N;
            this.maxSelectCount = N;
            this.cardSets = cardSets;
            this.dp = new int[2 * N + 1][2 * N + 1];
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
            for (int i = 0; i < cardSetsIndex; i++) {
                for (int red = 0; red <= maxSelectCount; red++) {
                    if (dp[i][red] == NOT_ALLOCATED) {
                        continue;
                    }
                    dp[i + 1][red + 1] = Math.max(dp[i + 1][red + 1],
                            dp[i][red] + cardSets.get(i + 1).red);
                    dp[i + 1][red] = Math.max(dp[i + 1][red],
                            dp[i][red] + cardSets.get(i + 1).blue);
                }
            }
        }

        private void initDP() {
            for (int[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            dp[0][0] = 0;
        }
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

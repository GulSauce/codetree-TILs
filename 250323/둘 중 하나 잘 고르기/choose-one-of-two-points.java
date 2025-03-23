import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int cardSetsIndex;
        int maxSelectCount;
        int[][][] dp;

        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<CardSet> cardSets;

        public Solver(
            int N,
            List<CardSet> cardSets
        ) {
            this.cardSetsIndex = 2 * N;
            this.maxSelectCount = N;
            this.cardSets = cardSets;
            this.dp = new int[2 * N + 1][N + 1][N + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(dp[cardSetsIndex][maxSelectCount][maxSelectCount]);
        }

        private void calcDP() {
            for (int i = 1; i <= cardSetsIndex; i++) {
                for (int red = 0; red <= maxSelectCount; red++) {
                    for (int blue = 0; blue <= maxSelectCount; blue++) {
                        if (dp[i - 1][red][blue] == NOT_ALLOCATED) {
                            continue;
                        }
                        if (red < maxSelectCount) {
                            dp[i][red + 1][blue] = Math.max(dp[i][red + 1][blue],
                                dp[i - 1][red][blue] + cardSets.get(i).red);
                        }
                        if (blue < maxSelectCount) {
                            dp[i][red][blue + 1] = Math.max(dp[i][red][blue + 1],
                                dp[i - 1][red][blue] + cardSets.get(i).blue);
                        }
                    }
                }
            }
        }

        private void initDP() {
            for (int[][] arrays : dp) {
                for (int[] array : arrays) {
                    Arrays.fill(array, NOT_ALLOCATED);
                }
            }
            dp[0][0][0] = 0;
        }
    }

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

        new Solver(N, cardSets).solve();
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
import javax.smartcardio.Card;
import java.util.*;

public class Main {
    private static class Solver{
        int cardSetsIndex;
        int maxSelectCount;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        int[][] dp;
        List<CardSet> cardSets;

        public Solver(
                int n,
                List<CardSet> cardSets
        ){
            this.maxSelectCount = n;
            this.cardSetsIndex = 2*n-1;
            this.dp = new int[2*n][n+1];
            this.cardSets = cardSets;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dp[cardSetsIndex][maxSelectCount]);
        }

        private void calcDP() {
            for(int i = 1; i <= cardSetsIndex; i++){
                dp[i][0] = Math.max(dp[i-1][0]+cardSets.get(i).blue, dp[i][0]);
                for(int j = 1; j <= maxSelectCount; j++) {
                    dp[i][j] = Math.max(dp[i-1][j]+cardSets.get(i).blue, dp[i][j]);
                    dp[i][j] = Math.max(dp[i-1][j-1]+cardSets.get(i).red, dp[i][j]);
                }
            }
        }

        private void initDP(){
            for(int[] array: dp){
                Arrays.fill(array, NOT_ALLOCATED);
            }
            dp[0][0] = cardSets.get(0).blue;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<CardSet> cardSets = new ArrayList<>();
        for(int i = 0; i < 2*N; i++){
            cardSets.add(new CardSet(sc.nextInt(),sc.nextInt()));
        }
        new Solver(N, cardSets).solve();
    }

    private static class CardSet{
        int red;
        int blue;

        public CardSet(
                int red,
                int blue
        ){
            this.red = red;
            this.blue = blue;
        }
    }
}

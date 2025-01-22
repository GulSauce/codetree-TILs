import java.util.*;

public class Main {
    private static class Solver{
        int cardsIndex;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<Integer> firstCards;
        List<Integer> secondCards;

        int[][] dp;

        public Solver(
                int n,
                List<Integer> firstCards,
                List<Integer> secondCards
        ){
            this.cardsIndex = n-1;
            this.firstCards = firstCards;
            this.secondCards = secondCards;
            this.dp = new int[n+1][n+1];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printDP(){
            for(int[] array: dp){
                for(int value: array){
                    System.out.printf("%d ", value);
                }
                System.out.println();
            }
        }

        private void printAnswer(){
            int answer = 0;
            for(int i = 0; i <= cardsIndex+1; i++){
                answer = Math.max(answer, dp[i][cardsIndex+1]);
            }
            for(int i = 0; i <= cardsIndex+1; i++){
                answer = Math.max(answer, dp[cardsIndex+1][i]);
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(int i = 0; i <= cardsIndex; i++){
                for(int j = 0; j <= cardsIndex; j++){
                    if(dp[i][j] == NOT_ALLOCATED){
                        continue;
                    }
                    if(secondCards.get(j) < firstCards.get(i)){
                        dp[i][j+1] = Math.max(dp[i][j]+secondCards.get(j), dp[i][j+1]);
                    }
                    if(firstCards.get(i) < secondCards.get(j)){
                        dp[i+1][j] = Math.max(dp[i][j], dp[i+1][j]);
                    }
                    dp[i+1][j+1] = Math.max(dp[i][j], dp[i+1][j+1]);
                }
            }
        }

        private void initDP(){
            for(int[] array: dp){
                Arrays.fill(array, NOT_ALLOCATED);
            }
            dp[0][0] = 0;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> firstCards = new ArrayList<>();
        List<Integer> secondCards = new ArrayList<>();
        for(int i = 0; i < n; i++){
            firstCards.add(sc.nextInt());
        }
        for(int i = 0; i < n; i++){
            secondCards.add(sc.nextInt());
        }
        new Solver(n, firstCards, secondCards).solve();
    }
}
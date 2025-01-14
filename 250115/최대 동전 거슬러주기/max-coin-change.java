import javax.swing.text.Style;
import java.util.*;

public class Main {
    private static class Solver{
        int coinsIndex;
        int targetValue;

        int[] dp;
        List<Integer> coins;

        public Solver(
                int N,
                int M,
                List<Integer> coins
        ){
            this.coinsIndex = N;
            this.targetValue = M;
            this.dp = new int[M+1];
            this.coins = coins;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dp[targetValue]);
        }

        private void initDP(){
            Arrays.fill(dp, Integer.MIN_VALUE);
            dp[0] = 0;
        }

        private void calcDP() {
            for(int value = 1; value <= targetValue; value++) {
                for(int coin: coins){
                    if(value - coin < 0){
                        continue;
                    }
                    if(dp[value-coin] == Integer.MIN_VALUE){
                        continue;
                    }
                    dp[value] = Math.max(dp[value],dp[value-coin]+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Integer> coins = new ArrayList<>();
        for(int i = 0; i < N; i++){
            coins.add(sc.nextInt());
        }
        new Solver(N, M, coins).solve();
    }
}

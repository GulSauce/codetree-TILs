import java.util.*;

public class Main {
    private static class Solver{
        int coinsIndex;
        int targetValue;

        final int NOT_ALLOCATED = Integer.MAX_VALUE;

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
            if(dp[targetValue] == NOT_ALLOCATED){
                System.out.println(-1);
                return;
            }
            System.out.println(dp[targetValue]);
        }

        private void calcDP(){
            for(int curValue = 1; curValue <= targetValue; curValue++){
                for(int coin: coins){
                    int prevValue = curValue - coin;
                    if(isOutOfTarget(prevValue)){
                        continue;
                    }
                    if(dp[prevValue] == NOT_ALLOCATED){
                        continue;
                    }
                    dp[curValue] = Math.min(dp[curValue], dp[prevValue]+1);
                }
            }
        }

        private void initDP(){
            Arrays.fill(dp, NOT_ALLOCATED);
            for(int coin: coins){
                if(isOutOfTarget(coin)){
                    continue;
                }
                dp[coin] = 1;
            }
        }

        private boolean isOutOfTarget(int value){
            return targetValue < value || value <= 0;
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
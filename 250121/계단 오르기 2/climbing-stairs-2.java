import java.util.*;

public class Main {
    private static class Solver{
        int maxFloor;
        int coinsIndex;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<Integer> coins;

        int[][] dp;

        public Solver(
                int n,
                List<Integer> coins
        ){
            this.maxFloor = n;
            this.coinsIndex = n;
            this.dp = new int[n+1][4];
            this.coins = coins;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            int answer = 0;
            for(int i = 0; i <= 3; i++){
                answer = Math.max(answer, dp[maxFloor][i]);
            }
            System.out.println(answer);
        }

        private void calcDP(){
            dp[1][1] = dp[0][0]+coins.get(0);
            for(int floor = 2; floor <= maxFloor; floor++){
                for(int count = 0; count <= 3; count++){
                    if(dp[floor-2][count] == NOT_ALLOCATED){
                        continue;
                    }
                    dp[floor][count] = Math.max(dp[floor-2][count]+coins.get(floor), dp[floor][count]);
                }
                for(int count = 1; count <= 3; count++){
                    if(dp[floor-1][count-1] == NOT_ALLOCATED){
                        continue;
                    }
                    dp[floor][count] = Math.max(dp[floor-1][count-1]+coins.get(floor), dp[floor][count]);
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
        List<Integer> coins = new ArrayList<>();
        coins.add(0);
        for(int i = 0; i < n; i++){
            coins.add(sc.nextInt());
        }
        new Solver(n, coins).solve();
    }
}
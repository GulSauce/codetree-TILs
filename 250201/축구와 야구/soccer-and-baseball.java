import java.util.*;

public class Main {
    private static class Solver{
        int statusesIndex;
        final int SOCCER_PLAYER_COUNT = 11;
        final int BASEBALL_PLAYER_COUNT = 9;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<Status> statuses;

        int[][][] dp;

        public Solver(
                int n,
                List<Status> statuses
        ){
            this.statusesIndex = n-1;
            this.statuses = statuses;
            this.dp = new int[n][SOCCER_PLAYER_COUNT+1][BASEBALL_PLAYER_COUNT+1];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dp[statusesIndex][11][9]);
        }

        private void calcDP(){
            for(int i = 1; i <= statusesIndex; i++){
                for(int j = 0; j <= SOCCER_PLAYER_COUNT; j++){
                    for(int k = 0; k <= BASEBALL_PLAYER_COUNT; k++){
                        if(dp[i-1][j][k] == NOT_ALLOCATED){
                            continue;
                        }
                        if(j+1 <= SOCCER_PLAYER_COUNT) {
                            dp[i][j + 1][k] = Math.max(dp[i-1][j][k] + statuses.get(i).soccer, dp[i][j + 1][k]);
                        }
                        if(k+1 <= BASEBALL_PLAYER_COUNT){
                            dp[i][j][k+1] = Math.max(dp[i-1][j][k]+statuses.get(i).baseBall, dp[i][j][k+1]);
                        }
                    }
                }
            }
        }

        private void initDP(){
            for(int[][] arrays: dp){
                for(int[] array: arrays) {
                    Arrays.fill(array, NOT_ALLOCATED);
                }
            }
            for(int i = 0; i <= statusesIndex; i++){
                dp[i][0][0] = 0;
            }
            dp[0][1][0] = statuses.get(0).soccer;
            dp[0][0][1] = statuses.get(0).baseBall;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Status> statuses = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int s = sc.nextInt();
            int b = sc.nextInt();
            statuses.add(new Status(s, b));
        }
        new Solver(n, statuses).solve();
    }

    private static class Status{
        int soccer;
        int baseBall;

        public Status(
                int s,
                int b
        ){
            this.soccer = s;
            this.baseBall = b;
        }
    }
}
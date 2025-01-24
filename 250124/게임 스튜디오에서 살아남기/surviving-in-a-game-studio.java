import java.util.*;

public class Main {
    private static class Solver{
        int targetSurvivalDay;
        final int R = 1000000007;
        final int NOT_ALLOCATED = 0;

        int[][][] dp;
        public Solver(
                int n
        ){
            this.targetSurvivalDay = n;
            this.dp = new int[n+1][3][3];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            int answer = 0;
            for(int bad = 0; bad <= 2; bad++) {
                for(int terrible = 0; terrible <= 2; terrible++) {
                    answer += dp[targetSurvivalDay][bad][terrible];
                    answer %= R;
                }
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(int i = 2; i <= targetSurvivalDay; i++){
                for(int bad = 0; bad <= 2; bad++){
                    for(int terrible = 0; terrible <= 2; terrible++){
                        if(0 <= terrible-1) {
                            dp[i][0][terrible] += dp[i-1][bad][terrible-1];
                            dp[i][0][terrible] %= R;
                        }
                        if(0 <= bad-1) {
                            dp[i][bad][terrible] += dp[i - 1][bad - 1][terrible];
                            dp[i][bad][terrible] %= R;
                        }
                        dp[i][0][terrible] += dp[i-1][bad][terrible];
                        dp[i][0][terrible] %= R;
                    }
                }
            }
        }

        private void initDP(){
            for(int[][] arrays: dp){
                for(int[] array: arrays){
                    Arrays.fill(array, NOT_ALLOCATED);
                }
            }
            dp[1][0][0] = 1;
            dp[1][1][0] = 1;
            dp[1][0][1] = 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        new Solver(n).solve();
    }
}
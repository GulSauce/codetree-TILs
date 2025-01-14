import java.util.*;

public class Main {
    private static class Solver{
        int targetNumber;

        final int R = 10007;

        int[] numbers = {1, 2, 5};
        int[] dp;

        public Solver(
                int n
        ){
            this.targetNumber = n;
            this.dp = new int[n+1];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void initDP(){
            dp[0] = 1;
        }

        private void  printDP(){
            for(int number: dp){
                System.out.printf("%d ", number);
            }
            System.out.println();
        }

        private void printAnswer(){
            System.out.println(dp[targetNumber]);
        }

        private void calcDP(){
            for(int i = 1; i <= targetNumber; i++){
                for(int number: numbers){
                    if(i - number < 0) {
                        continue;
                    }
                    dp[i] += dp[i-number];
                    dp[i] %= R;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        new Solver(n).solve();
    }
}
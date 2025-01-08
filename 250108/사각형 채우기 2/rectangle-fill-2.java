import java.util.*;

public class Main {
    private static class Solver{
        int maxSize;

        final int R = 10007;

        int[] dp = new int[1001];

        public Solver(
                int n
        ){
            this.maxSize = n;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void initDP(){
            dp[1] = 1;
            dp[2] = 3;
        }

        private void calcDP(){
            for(int i = 3; i <= maxSize; i++){
                dp[i] = dp[i-1] + (dp[i-2]*2)%R;
                dp[i] %= R;
            }
        }

        private void printAnswer(){
            System.out.println(dp[maxSize]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        new Solver(n).solve();
    }
}
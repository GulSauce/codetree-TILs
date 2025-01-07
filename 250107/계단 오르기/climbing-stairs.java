import java.util.*;

public class Main {
    private static class Solver{
        int n;

        final int R = 10007;

        int[] dp = new int[1001];

        public Solver(
                int n
        ){
            this.n = n;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dp[n]);
        }

        private void calcDP(){
            for(int i = 4; i <= n; i++){
                dp[i] = dp[i-2] % R + dp[i-3] % R;
                dp[i] %= R;
            }
        }

        private void initDP(){
            dp[2] = 1;
            dp[3] = 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        new Solver(n).solve();
    }
}
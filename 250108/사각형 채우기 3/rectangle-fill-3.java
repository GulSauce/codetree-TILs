import java.util.*;

public class Main {
    private static class Solver{
        int width;

        final int R = 1000000007;

        int[] dp = new int[1001];

        public Solver(
                int n
        ){
            this.width = n;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dp[width]);
        }

        private void calcDP(){
            for(int i =3; i <= width; i++){
                dp[i] = dp[i-1] * 2 % R;
                dp[i] += dp[i-2] * 3 % R;
                dp[i] %= R;
                for(int j = 1; j <= i-3; j++){
                    dp[i] += dp[j] % R * 2 % R;
                    dp[i] %= R;
                }
                dp[i] += 2;
                dp[i] %= R;
            }
        }

        private void initDP(){
            dp[1] = 2;
            dp[2] = 7;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        new Solver(n).solve();
    }
}
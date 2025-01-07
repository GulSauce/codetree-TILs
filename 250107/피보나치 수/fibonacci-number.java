import java.util.*;

public class Main {
    private static class Solver{
        int N;

        int[] dp = new int[46];

        public Solver(
                int N
        ){
            this.N = N;
        }

        public void solve(){
            initDP();
            getNFibo();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dp[N]);
        }

        private void getNFibo(){
            for(int i = 3; i <= N; i++){
                dp[i] = dp[i-1] + dp[i-2];
            }
        }

        private void initDP(){
            dp[1] = 1;
            dp[2] = 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        new Solver(N).solve();
    }
}
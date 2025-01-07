import java.util.*;

public class Main {
    private static class Solver{
        int width;

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
                dp[i] = dp[i-1] + dp[i-2];
            }
        }

        private void initDP(){
            dp[1] = 1;
            dp[2] = 2;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        new Solver(n).solve();
    }
}
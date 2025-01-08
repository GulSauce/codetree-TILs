import java.util.*;

public class Main {
    private static class Solver {
        int maxNodeNumber;

        int[] dp = new int[20];

        public Solver(
                int N
        ){
            this.maxNodeNumber = N;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dp[maxNodeNumber]);
        }

        private void calcDP(){
            for(int i = 2; i <= maxNodeNumber; i++){
                int leftSize = i-1;
                int rightSize = 0;
                while(rightSize <= i-1){
                    dp[i] += dp[leftSize] * dp[rightSize];
                    leftSize--;
                    rightSize++;
                }
            }
        }

        private void initDP(){
            dp[0] = 1;
            dp[1] = 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        new Solver(N).solve();
    }
}
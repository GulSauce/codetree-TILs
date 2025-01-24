import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;
        int targetNumber;
        final int OFFSET = 20;
        final int NOT_ALLOCATED = 0;

        List<Integer> numbers;

        long[][] dp;

        public Solver(
                int N,
                int M,
                List<Integer> numbers
        ){
            this.numbersIndex = N-1;
            this.targetNumber = M;
            this.numbers = numbers;
            this.dp = new long[N][41];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dp[numbersIndex][targetNumber+OFFSET]);
        }

        private void calcDP(){
            for(int i = 1; i <= numbersIndex; i++){
                int cur = numbers.get(i);
                for(int j = -20; j <= 20; j++){
                    if(dp[i-1][j+OFFSET] == NOT_ALLOCATED){
                        continue;
                    }
                    if(-20 <= j-cur){
                        dp[i][j-cur+OFFSET] += dp[i-1][j+OFFSET];
                    }
                    if(j+cur <= 20){
                        dp[i][j+cur+OFFSET] += dp[i-1][j+OFFSET];
                    }
                }
            }
        }

        private void initDP(){
            for(long[] array: dp){
                Arrays.fill(array, NOT_ALLOCATED);
            }
            dp[0][numbers.get(0)+OFFSET]++;
            dp[0][-numbers.get(0)+OFFSET]++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < N; i++){
            numbers.add(sc.nextInt());
        }

        new Solver(N, M, numbers).solve();
    }
}
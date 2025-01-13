import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;

        int[][] dp;
        List<Integer> numbers;

        public Solver(
                int N,
                List<Integer> numbers
        ){
            this.numbersIndex = N-1;
            this.dp = new int[N][2];
            this.numbers = numbers;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void calcDP(){
            for(int cur = 1; cur <= numbersIndex; cur++){
                for(int prev = 0; prev < cur; prev++){
                    int prevNumber = numbers.get(prev);
                    int curNumber = numbers.get(cur);
                    if(prevNumber < curNumber){
                        dp[cur][0] = Math.max(dp[prev][0]+1, dp[cur][0]);
                    }
                    if(curNumber < prevNumber){
                        dp[cur][1] = Math.max(Math.max(dp[prev][0]+1, dp[prev][1]+1), dp[cur][1]);
                    }
                }
            }
        }

        private void printAnswer(){
            int answer = 0;
            for(int[] value: dp){
                answer = Math.max(answer, Math.max(value[0], value[1]));
            }
            System.out.println(answer);
        }

        private void initDP(){
            for(int i = 0; i <= numbersIndex; i++){
                dp[i][0] = 1;
                dp[i][1] = 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < N; i++){
            numbers.add(sc.nextInt());
        }
        new Solver(N, numbers).solve();
    }
}
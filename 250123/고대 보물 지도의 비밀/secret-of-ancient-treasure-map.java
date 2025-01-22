import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;
        int maxNegativeCount;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        int[][] dp;
        List<Integer> numbers;

        public Solver(
                int n,
                int k,
                List<Integer> numbers
        ){
            this.numbersIndex = n-1;
            this.maxNegativeCount = k;
            this.numbers = numbers;
            this.dp = new int[n][k+1];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            int answer = 0;
            for(int[] array: dp){
                for(int value: array){
                    answer = Math.max(answer, value);
                }
            }

            System.out.println(answer);
        }

        private void calcDP(){
            for(int i = 1; i <= numbersIndex; i++){
                for(int j = 0; j <= maxNegativeCount; j++){
                    if(numbers.get(i) < 0 && j == maxNegativeCount){
                        continue;
                    }
                    if(dp[i-1][j] == NOT_ALLOCATED){
                        continue;
                    }
                    if(numbers.get(i) < 0){
                        dp[i][j+1] = Math.max(dp[i-1][j]+numbers.get(i), dp[i][j+1]);
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i-1][j]+numbers.get(i), dp[i][j]);
                }
            }
        }

        private void initDP(){
            for(int[] array: dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            for(int i = 0; i <= numbersIndex; i++) {
                if (numbers.get(i) < 0) {
                    dp[i][1] = numbers.get(i);
                    continue;
                }
                dp[i][0] = numbers.get(i);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < n; i++){
            numbers.add(sc.nextInt());
        }
        new Solver(n, k, numbers).solve();
    }
}

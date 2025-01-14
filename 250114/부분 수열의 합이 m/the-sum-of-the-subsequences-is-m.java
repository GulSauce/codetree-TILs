import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;
        int targetValue;

        int[] dp;
        List<Integer> numbers;

        public Solver(
                int n,
                int m,
                List<Integer> numbers
        ){
            this.numbersIndex = n;
            this.targetValue = m;
            this.dp = new int[m+1];
            this.numbers = numbers;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void initDP(){
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
        }

        private void calcDP(){
            for(int number: numbers){
                for(int cur = targetValue; cur >= 1; cur--){
                    if(cur - number < 0){
                        break;
                    }
                    if(dp[cur-number] == Integer.MAX_VALUE){
                        continue;
                    }
                    dp[cur] = Math.min(dp[cur], dp[cur-number]+1);
                }
            }
        }

        private void printAnswer(){
            if(dp[targetValue] == Integer.MAX_VALUE){
                System.out.println(-1);
                return;
            }
            System.out.println(dp[targetValue]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < n; i++){
            numbers.add(sc.nextInt());
        }
        new Solver(n, m, numbers).solve();
    }
}
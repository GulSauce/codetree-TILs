import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;
        int targetSum;

        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        int[] dp;
        List<Integer> numbers;

        public Solver(
                int n,
                int m,
                List<Integer> numbers
        ){
            this.numbersIndex = n-1;
            this.targetSum = m;
            this.dp = new int[m+1];
            this.numbers = numbers;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            if(dp[targetSum] == NOT_ALLOCATED){
                System.out.println("No");
                return;
            }
            System.out.println("Yes");
        }

        private void initDP(){
            Arrays.fill(dp, NOT_ALLOCATED);
            dp[0] = 0;
        }

        private void calcDP(){
            for(int number: numbers){
                for(int i = targetSum; i >= 1; i--){
                    if(i - number < 0){
                        continue;
                    }
                    if(dp[i-number] == NOT_ALLOCATED){
                        continue;
                    }
                    dp[i] = Math.max(dp[i], dp[i-number]+1);
                }
            }
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
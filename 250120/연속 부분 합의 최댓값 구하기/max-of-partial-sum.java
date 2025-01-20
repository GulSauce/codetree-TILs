import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        int[] dp;
        List<Integer> numbers;

        public Solver(
                int n,
                List<Integer> numbers
        ){
            this.numbersIndex = n-1;
            this.dp = new int[n];
            this.numbers = numbers;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            int answer = Integer.MIN_VALUE;
            for(int value: dp){
                answer = Math.max(answer, value);
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(int i = 1; i <= numbersIndex; i++){
                dp[i] = Math.max(dp[i-1]+numbers.get(i), numbers.get(i));
            }
        }

        private void initDP(){
            Arrays.fill(dp, NOT_ALLOCATED);
            dp[0] = numbers.get(0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < n; i++){
            numbers.add(sc.nextInt());
        }
        new Solver(n, numbers).solve();
    }
}
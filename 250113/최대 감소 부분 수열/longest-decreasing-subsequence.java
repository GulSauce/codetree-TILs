import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;

        int[] dp;
        List<Integer> numbers;

        public Solver(
                int N,
                List<Integer> numbers
        ){
            this.numbers = numbers;
            this.numbersIndex = N-1;
            this.dp = new int[N];
        }

        public void solve(){
            initDP();
            calcDP();
            printResult();
        }

        private void printResult(){
            int answer = 0;
            for(int number: dp){
                answer = Math.max(answer, number);
            }
            System.out.println(answer);
        }

        private void initDP(){
            Arrays.fill(dp, 1);
        }

        private void calcDP(){
            for(int cur = 1; cur <= numbersIndex; cur++){
                for(int prev = 0; prev < cur; prev++){
                    if(numbers.get(prev) < numbers.get(cur)){
                        continue;
                    }
                    dp[cur] = Math.max(dp[cur], dp[prev]+1);
                }
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
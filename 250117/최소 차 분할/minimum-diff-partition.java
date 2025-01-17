import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;
        int numbersSum;

        final int MAX_SUM = 100000;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        int[] dp = new int[MAX_SUM+1];
        List<Integer> numbers;

        public Solver(
                int n,
                List<Integer> numbers
        ){
            this.numbersIndex = n;
            this.numbers = numbers;
        }

        public void solve(){
            numbersSum = getNumbersSum();
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            int answer = Integer.MAX_VALUE;
            for(int number = 0; number <= MAX_SUM; number++){
                if(dp[number] == 0 || dp[number] == NOT_ALLOCATED){
                    continue;
                }
                int diff = numbersSum-number;
                answer = Math.min(answer, Math.abs(number-diff));
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(int number: numbers){
                for(int curNumber = MAX_SUM; curNumber >= 0; curNumber--){
                    if(curNumber-number < 0){
                        continue;
                    }
                    if(dp[curNumber-number] == NOT_ALLOCATED){
                        continue;
                    }
                    dp[curNumber] = Math.max(dp[curNumber], dp[curNumber-number]+1);
                }
            }
        }

        private void initDP(){
            Arrays.fill(dp, NOT_ALLOCATED);
            dp[0] = 0;
        }

        private int getNumbersSum(){
            int sum = 0;
            for(int number: numbers){
                sum += number;
            }
            return sum;
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
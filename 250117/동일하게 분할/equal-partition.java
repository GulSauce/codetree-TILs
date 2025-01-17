import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;
        int numbersSum;

        boolean[] dp = new boolean[100001];
        List<Integer> numbers;

        public Solver(
                int n,
                List<Integer> numbers
        ){
            this.numbersIndex = n-1;
            this.numbers = numbers;
        }

        public void solve(){
            numbersSum = getNumbersSum();
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            if(numbersSum%2 != 0){
                System.out.println("No");
                return;
            }
            if(dp[numbersSum/2] == false){
                System.out.println("No");
                return;
            }
            System.out.println("Yes");
        }

        private void calcDP(){
            for(int number: numbers){
                for(int i = 100000; i >= 0; i--){
                    if(i - number < 0){
                        continue;
                    }
                    if(dp[i-number] == false){
                        continue;
                    }
                    dp[i] = true;
                }
            }
        }

        private void initDP(){
            Arrays.fill(dp, false);
            dp[0] = true;
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
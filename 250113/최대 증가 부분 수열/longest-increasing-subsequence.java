import java.util.*;

public class Main {
    private static class Solver{
        int[] dp;

        int numbersIndex;
        List<Integer> numbers;

        public Solver(
                List<Integer> numbers
        ){
            this.numbers = numbers;
            this.numbersIndex = numbers.size()-1;
            this.dp = new int[numbers.size()];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            int answer = 0;
            for(int i = 0; i <= numbersIndex; i++){
                answer = Math.max(dp[i], answer);
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(int currentIndex = 1; currentIndex <= numbersIndex; currentIndex++){
                for(int prevIndex = 0; prevIndex < currentIndex; prevIndex++){
                    if(numbers.get(prevIndex) < numbers.get(currentIndex)){
                        dp[currentIndex] = Math.max(dp[prevIndex]+1, dp[currentIndex]);
                    }
                }
            }
        }

        private void initDP(){
            dp[0] = 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < N; i++){
            numbers.add(sc.nextInt());
        }

        new Solver(numbers).solve();
    }
}
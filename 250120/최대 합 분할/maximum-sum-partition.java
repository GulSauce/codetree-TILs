import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;

        final int MAX_DIFF = 100000;
        final int MIN_DIFF = -100000;
        final int OFFSET = 100000;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<Integer> numbers;

        int[][] dp = new int[100][MAX_DIFF+OFFSET+1];

        public Solver(
                int n,
                List<Integer> numbers
        ){
            this.numbersIndex = n-1;
            this.numbers = numbers;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printDP(){
            for(int i = -10; i <= 10; i++){
                System.out.printf("%d ", i);
            }
            System.out.println();
            for(int[] array: dp){
                for(int i = -10; i <= 10; i++){
                    if(array[i+OFFSET] == NOT_ALLOCATED){
                        System.out.printf("%s ", "E ");
                        continue;
                    }
                    System.out.printf("%d ", array[i+OFFSET]);
                }
                System.out.println();
            }
        }

        private void printAnswer(){
            if(dp[numbersIndex][OFFSET] == NOT_ALLOCATED){
                System.out.println(0);
                return;
            }
            System.out.println(dp[numbersIndex][OFFSET]);
        }

        private void initDP(){
            for(int[] array: dp){
                Arrays.fill(array, NOT_ALLOCATED);
            }
            dp[0][OFFSET] = 0;
            dp[0][numbers.get(0)+OFFSET] = numbers.get(0);
            dp[0][-numbers.get(0)+OFFSET] = 0;
        }

        private void calcDP(){
            for(int i = 1; i < numbers.size(); i++){
                int curNumber = numbers.get(i);
                for(int curDiff = MAX_DIFF; curDiff >= MIN_DIFF; curDiff--){
                    if(curDiff-curNumber+OFFSET >= MIN_DIFF+OFFSET && dp[i-1][curDiff-curNumber+OFFSET] != NOT_ALLOCATED){
                    dp[i][curDiff+OFFSET] = Math.max(dp[i-1][curDiff-curNumber+OFFSET]+curNumber, dp[i][curDiff+OFFSET]);
                    }
                    if(curDiff+curNumber+OFFSET <= MAX_DIFF+OFFSET && dp[i-1][curDiff+curNumber+OFFSET] != NOT_ALLOCATED) {
                        dp[i][curDiff+OFFSET] = Math.max(dp[i - 1][curDiff + curNumber + OFFSET], dp[i][curDiff+OFFSET]);
                    }
                    if(dp[i-1][curDiff+OFFSET] != NOT_ALLOCATED) {
                        dp[i][curDiff + OFFSET] = Math.max(dp[i - 1][curDiff + OFFSET], dp[i][curDiff + OFFSET]);
                    }
                }
            }
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
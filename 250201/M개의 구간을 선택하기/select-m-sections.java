import java.util.*;

public class Main {
    private static class Solver{
        int numbersIndex;
        int selectSectionCount;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        int[][][] dp;
        List<Integer> numbers;

        public Solver(
                int N,
                int M,
                List<Integer> numbers
        ){
            this.numbersIndex = N-1;
            this.selectSectionCount = M;
            this.dp = new int[N][M+1][2];
            this.numbers = numbers;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(Math.max(dp[numbersIndex][selectSectionCount][1], dp[numbersIndex][selectSectionCount][0]));
        }

        private void printDP(){
            for(int[][] arrays: dp){
                for(int[] array: arrays){
                    System.out.printf("(%d|%d) ", array[0], array[1]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }

        private void calcDP(){
            for(int i = 1; i <= numbersIndex; i++){
                for(int j = 1; j <= selectSectionCount; j++){
                    if(dp[i-1][j][1] != NOT_ALLOCATED){
                        dp[i][j][1] = Math.max(dp[i-1][j][1] + numbers.get(i), dp[i][j][1]);
                    }
                    if(dp[i-1][j-1][0] != NOT_ALLOCATED){
                        dp[i][j][1] = Math.max(dp[i-1][j-1][0] + numbers.get(i), dp[i][j][1]);
                    }
                    if(dp[i-1][j][0] != NOT_ALLOCATED){
                        dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i][j][0]);
                    }
                    if(dp[i-1][j][1] != NOT_ALLOCATED){
                        dp[i][j][0] = Math.max(dp[i-1][j][1], dp[i][j][0]);
                    }
                }
            }
        }

        private void initDP(){
            for(int[][] arrays: dp){
                for(int[] array: arrays){
                    Arrays.fill(array, NOT_ALLOCATED);
                }
            }
            dp[0][0][0] = 0;
            dp[0][1][1] = numbers.get(0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < N; i++){
            numbers.add(sc.nextInt());
        }
        new Solver(N, M, numbers).solve();
    }
}
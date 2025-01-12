import java.util.*;

public class Main {
    private static class Solver{
        int gridIndex;

        final int MAX_NUMBER = 100;

        int[][] grid;

        int[][][] dp;

        public Solver(
                int n,
                int[][] grid
        ){
            this.gridIndex = n;
            this.grid = grid;
            this.dp = new int[n+1][n+1][MAX_NUMBER+1];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void initDP(){
            for(int row = 1; row <= gridIndex; row++){
                for(int col = 1; col <= gridIndex; col++){
                    for(int number = 1; number <= MAX_NUMBER; number++){
                        dp[row][col][number] = Integer.MAX_VALUE;
                    }
                }
            }
            dp[1][1][grid[1][1]] = grid[1][1];
            for(int row = 2; row <= gridIndex; row++){
                for(int number = 1; number <= MAX_NUMBER; number++) {
                    int prevValueFromRow = dp[row-1][1][number];
                    if(prevValueFromRow == Integer.MAX_VALUE){
                        continue;
                    }
                    dp[row][1][Math.min(number, grid[row][1])] = Math.max(dp[row-1][1][number], grid[row][1]);
                }
            }
            for(int col = 2; col <= gridIndex; col++){
                for(int number = 1; number <= MAX_NUMBER; number++) {
                    int prevValueFromCol = dp[1][col-1][number];
                    if(prevValueFromCol == Integer.MAX_VALUE){
                        continue;
                    }
                    dp[1][col][Math.min(number, grid[1][col])] = Math.max(dp[1][col-1][number], grid[1][col]);
                }
            }
        }

        private void calcDP(){
            for(int row = 2; row <= gridIndex; row++){
                for(int col = 2; col <= gridIndex; col++){
                    for(int number = 1; number <= MAX_NUMBER; number++){
                        dp[row][col][Math.min(number, grid[row][col])] = Math.min(dp[row][col][Math.min(number, grid[row][col])],
                                Math.max(Math.min(dp[row-1][col][number], dp[row][col-1][number]), grid[row][col]));
                    }
                }
            }
        }

        private void printAnswer(){
            int answer = Integer.MAX_VALUE;
            for(int number = 1; number <= MAX_NUMBER; number++){
                answer = Math.min(answer, dp[gridIndex][gridIndex][number]-number);
            }
            System.out.println(answer);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n+1][n+1];
        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= n; col++){
                grid[row][col] = sc.nextInt();
            }
        }

        new Solver(n, grid).solve();
    }

}
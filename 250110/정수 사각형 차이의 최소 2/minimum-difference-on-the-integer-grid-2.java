import java.util.*;

public class Main {
    private static class Solver{
        int gridIndex;


        MinMax[][] dp;
        int[][] grid;

        public Solver(
                int N,
                int[][] grid
        ){
            this.gridIndex = N;
            this.grid = grid;
            this.dp = new MinMax[N+1][N+1];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printDP(){
            for(int row = 1; row <= gridIndex; row++){
                for(int col = 1; col <= gridIndex; col++){
                    System.out.printf("max:%d, min:%d|", dp[row][col].max, dp[row][col].min);
                }
                System.out.println();
            }

        }

        private void printAnswer(){
            System.out.println(dp[gridIndex][gridIndex].max - dp[gridIndex][gridIndex].min);
        }

        private void calcDP(){
            for(int row = 2; row <= gridIndex; row++){
                for(int col = 2; col <= gridIndex; col++){
                    int minFromRow = Math.min(dp[row-1][col].min, grid[row][col]);
                    int maxFromRow = Math.max(dp[row-1][col].max, grid[row][col]);
                    int diffFromRow = maxFromRow-minFromRow;

                    int minFromCol = Math.min(dp[row][col-1].min, grid[row][col]);
                    int maxFromCol = Math.max(dp[row][col-1].max, grid[row][col]);
                    int diffFromCol = maxFromCol-minFromCol;

                    if(diffFromRow < diffFromCol){
                        dp[row][col] = new MinMax(minFromRow, maxFromRow);
                    }else{
                        dp[row][col] = new MinMax(minFromCol, maxFromCol);
                    }
                }
            }
        }

        private void initDP(){
            for(int row = 1; row <= gridIndex; row++){
                for(int col = 1; col <= gridIndex; col++){
                    dp[row][col] = new MinMax(Integer.MAX_VALUE, Integer.MIN_VALUE);
                }
            }
            dp[1][1] = new MinMax(grid[1][1], grid[1][1]);
            for(int col = 2; col <= gridIndex; col++){
                int min = Math.min(dp[1][col-1].min, grid[1][col]);
                int max = Math.max(dp[1][col-1].max, grid[1][col]);
                dp[1][col] = new MinMax(min, max);
            }
            for(int row = 2; row <= gridIndex; row++){
                int min = Math.min(dp[row-1][1].min, grid[row][1]);
                int max = Math.max(dp[row-1][1].max, grid[row][1]);
                dp[row][1] = new MinMax(min, max);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] grid = new int[N+1][N+1];
        for(int row = 1; row <= N; row++){
            for(int col = 1; col <= N; col++){
                grid[row][col] = sc.nextInt();
            }
        }

        new Solver(N, grid).solve();
    }

    private static class MinMax{
        int min;
        int max;

        private MinMax(
                int min,
                int max
        ){
            this.min = min;
            this.max = max;
        }
    }
}
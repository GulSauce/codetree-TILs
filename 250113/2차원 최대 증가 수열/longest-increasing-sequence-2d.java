import java.util.*;

public class Main {
    private static class Solver{
        int rowIndex;
        int colIndex;

        final int NOT_ALLOCATED = -1;

        int[][] dp;
        int[][] grid;

        public Solver(
                int n,
                int m,
                int[][] grid
        ){
            this.rowIndex = n-1;
            this.colIndex = m-1;
            this.dp = new int[n][m];
            this.grid = grid;
        }

        public void solve(){
            initDP();
            calcDPEachCoordinate();
            printAnswer();
        }

        private void initDP(){
            for(int[] array: dp){
                Arrays.fill(array, NOT_ALLOCATED);
            }
        }

        private void calcDPEachCoordinate(){
            for(int row = 0; row <= rowIndex; row++){
                for(int col = 0; col <= colIndex; col++){
                    calcDP(row, col);
                }
            }
        }

        private void calcDP(int prevRow, int prevCol){
            if(dp[prevRow][prevCol] != NOT_ALLOCATED){
                return;
            }

            for(int row = prevRow+1; row <= rowIndex; row++){
                for(int col = prevCol+1; col <= colIndex; col++){
                    if(grid[row][col] <= grid[prevRow][prevCol]){
                        continue;
                    }
                    calcDP(row, col);
                    dp[prevRow][prevCol] = Math.max(dp[prevRow][prevCol], dp[row][col]+1);
                }
            }

            if(dp[prevRow][prevCol] == NOT_ALLOCATED) {
                dp[prevRow][prevCol] = 1;
            }
        }

        private void printAnswer(){
            System.out.println(dp[0][0]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for(int row = 0; row < n; row++){
            for(int col = 0; col < m; col++){
                grid[row][col] = sc.nextInt();
            }
        }

        new Solver(n,m,grid).solve();
    }
}
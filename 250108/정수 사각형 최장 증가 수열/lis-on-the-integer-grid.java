import java.util.*;

public class Main {
    private static class Solver{
        int gridIndex;

        final int NOT_MEMORIZATION = -1;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        int[][] dp;
        int[][] grid;

        public Solver(
                int n,
                int[][] grid
        ){
            this.gridIndex = n-1;
            this.grid = grid;
            this.dp = new int[n][n];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void initDP(){
            for(int[] array: dp){
                Arrays.fill(array, NOT_MEMORIZATION);
            }
        }

        private void printAnswer(){
            int answer = 0;
            for(int row = 0; row <= gridIndex; row++){
                for(int col = 0; col <= gridIndex; col++){
                    answer = Math.max(answer, dp[row][col]);
                }
            }

            System.out.println(answer);
        }

        private void calcDP(){
            for(int row = 0; row <= gridIndex; row++){
                for(int col = 0; col <= gridIndex; col++){
                    dp(row,col);
                }
            }
        }

        private void dp(int prevRow, int prevCol){
            if(dp[prevRow][prevCol] != NOT_MEMORIZATION){
                return;
            }
            for(int i = 0; i < 4; i++){
                int curRow = prevRow+dRow[i];
                int curCol = prevCol+dCol[i];
                if(isOutOfGrid(curRow, curCol)){
                    continue;
                }
                if(grid[curRow][curCol] <= grid[prevRow][prevCol]){
                    continue;
                }
                dp(curRow, curCol);
                dp[prevRow][prevCol] = Math.max(dp[prevRow][prevCol], dp[curRow][curCol]+1);
            }
            if(dp[prevRow][prevCol] == NOT_MEMORIZATION){
                dp[prevRow][prevCol] = 1;
            }
        }

        private boolean isOutOfGrid(int row, int col){
            return  row < 0 || gridIndex < row || col < 0 || gridIndex < col;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for(int row = 0; row < n; row++){
            for(int col = 0; col < n; col++){
                grid[row][col] = sc.nextInt();
            }
        }
        new Solver(n, grid).solve();
    }
}
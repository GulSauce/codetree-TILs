import java.util.*;

public class Main {
    private static class Solver{
        int gridIndex;

        int[][] dp;
        int[][] grid;

        public Solver(
                int N,
                int[][] grid
        ){
            this.gridIndex = N;
            this.grid = grid;
            this.dp = new int[N+1][N+1];
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dp[gridIndex][gridIndex]);
        }

        private void calcDP(){
            for(int row = 2; row <= gridIndex; row++){
                dp[row][1] = Math.min(dp[row-1][1], grid[row][1]);
                for(int col = 2; col <= gridIndex; col++){
                    dp[row][col] = Math.min(Math.max(dp[row][col-1], dp[row-1][col]), grid[row][col]);
                }
            }
        }

        private void initDP(){
            dp[1][1] = grid[1][1];
            for(int col = 2; col<=gridIndex; col++){
                dp[1][col] = Math.min(grid[1][col], dp[1][col-1]);
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
}
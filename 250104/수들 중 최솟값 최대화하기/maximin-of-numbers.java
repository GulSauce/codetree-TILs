import java.util.*;

public class Main {
    private static class Solver{
        int answer = Integer.MIN_VALUE;
        int maxGridRow;
        int[][] grid;
        boolean[] isVisitedCol;

        public Solver(
                int[][] grid
        ){
            this.grid = grid;
            this.maxGridRow = grid.length-1;
            this.isVisitedCol = new boolean[maxGridRow+1];
        }

        public void solve(){
            Arrays.fill(isVisitedCol,false);
            getPermutation(-1, Integer.MAX_VALUE);
            System.out.println(answer);
        }

        private void getPermutation(int prevRow, int minNumber){
            if(prevRow == maxGridRow){
                answer = Math.max(answer, minNumber);
            }

            for(int col = 0; col <= maxGridRow; col++){
                if(isVisitedCol[col]) {
                    continue;
                }
                isVisitedCol[col] = true;
                getPermutation(prevRow+1, Math.min(minNumber, grid[prevRow+1][col]));
                isVisitedCol[col] = false;
            }
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

        new Solver(grid).solve();
    }
}
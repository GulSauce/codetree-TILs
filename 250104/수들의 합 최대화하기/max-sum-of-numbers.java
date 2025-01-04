import java.util.*;

public class Main {
    private static class Solver{
        int answer = 0;
        int maxGridIndex;

        int[][] grid;

        boolean[] isVisitedCol;

        public Solver(
                int[][] grid
        ){
            this.grid = grid;
            this.maxGridIndex = grid.length-1;
            this.isVisitedCol = new boolean[grid.length];
        }

        public void solve(){
            initIsVisitedCol();
            getCombination(0,  -1, 0);
            System.out.println(answer);
        }

        private void getCombination(int prevRepeat, int prevRow, int prevSum){
            if(prevRepeat == grid.length){
                answer = Math.max(answer, prevSum);
                return;
            }

            for(int col = 0; col <= maxGridIndex; col++) {
                if(isVisitedCol[col]) {
                    continue;
                }
                isVisitedCol[col] = true;
                getCombination(prevRepeat + 1, prevRow + 1, prevSum + grid[prevRow+1][col]);
                isVisitedCol[col] = false;
            }
        }

        private void initIsVisitedCol(){
            Arrays.fill(isVisitedCol, false);
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
import java.util.*;

public class Main {
    private static class Solver{
        int maxRow;
        int maxCol;

        int[][] grid;
        int maxGridIndex;

        boolean isEscaped = false;

        Coordinate startCoordinate;
        Coordinate endCoordinate;

        int[] deltaRow = {0, -1, 0, 1};
        int[] deltaCol = {1, 0, -1 ,0};

        boolean[][] isVisited;

        public Solver(
                int[][] grid
        ){
            this.grid = grid;
            this.maxGridIndex = grid.length-1;
            this.startCoordinate = new Coordinate(0,0);
            this.maxRow = grid.length-1;
            this.maxCol = grid[0].length-1;
            this.endCoordinate = new Coordinate(maxRow, maxCol);
            this.isVisited = new boolean[maxRow+1][maxCol+1];
        }

        public void solve(){
            initIsVisited();
            dfs(startCoordinate);
            printAnswer();
        }

        private void printAnswer(){
            if(isEscaped){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }

        private void initIsVisited(){
            for(boolean[] array: isVisited){
                Arrays.fill(array, false);
            }
        }

        private void dfs(Coordinate prevCoordinate){
            if(prevCoordinate.same(endCoordinate)){
                isEscaped = true;
                return;
            }

            for(int i = 0; i < 4; i++){
                Coordinate curCoordinate = new Coordinate(prevCoordinate.row+deltaRow[i], prevCoordinate.col+deltaCol[i]);
                if(isOutOfRange(curCoordinate)){
                    continue;
                }
                if(isAlreadyVisited(curCoordinate)){
                    continue;
                }
                if(isSnake(curCoordinate)){
                    continue;
                }
                isVisited[curCoordinate.row][curCoordinate.col] = true;
                dfs(curCoordinate);
            }
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 0 || maxRow < coordinate.row || coordinate.col < 0 || maxCol < coordinate.col;
        }

        private boolean isAlreadyVisited(Coordinate coordinate){
            return isVisited[coordinate.row][coordinate.col];
        }

        public boolean isSnake(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] == 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] grid = new int[n][m];

        for(int row =0; row < n; row++) {
            for(int col = 0; col < n; col++){
                grid[row][col] = sc.nextInt();
            }
        }

        new Solver(grid).solve();
    }

    private static class Coordinate{
        int row;
        int col;

        public Coordinate(
                int row,
                int col
        ){
            this.row = row;
            this.col = col;
        }

        public boolean same(Coordinate coordinate){
            return this.row == coordinate.row && this.col == coordinate.col;
        }
    }
}
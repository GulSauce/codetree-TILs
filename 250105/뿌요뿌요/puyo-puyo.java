import java.util.*;

public class Main {
    private static class Solver{
        int gridRow;
        int gridCol;
        int targetNumber;
        int targetBlockCount;
        int maxTargetBlockCount = 0;
        int explodeBlockCount = 0;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        boolean[][] isVisited;

        int[][] grid;

        public Solver(
                int[][] grid
        ){
            this.grid = grid;
            this.gridRow = grid.length-1;
            this.gridCol = grid.length-1;
            this.isVisited = new boolean[gridRow+1][gridCol+1];
        }

        public void solve(){
            initIsVisited();
            dfsEach();
            printAnswer();
        }

        private void printAnswer(){
            System.out.printf("%d %d", explodeBlockCount, maxTargetBlockCount);
        }

        private void initIsVisited(){
            for(boolean[] array: isVisited){
                Arrays.fill(array, false);
            }
        }

        private void dfsEach(){
            for(int row = 0; row <= gridRow; row++){
                for(int col = 0; col <= gridCol; col++){
                    Coordinate curCoordinate = new Coordinate(row, col);
                    if(isAlreadyVisited(curCoordinate)){
                        continue;
                    }
                    targetNumber = grid[row][col];
                    targetBlockCount = 0;
                    targetBlockCount++;
                    isVisited[row][col] = true;
                    dfs(curCoordinate);
                    if(4 <= targetBlockCount){
                        explodeBlockCount++;
                    }
                    maxTargetBlockCount = Math.max(maxTargetBlockCount, targetBlockCount);
                }
            }
        }

        private void dfs(Coordinate prevCoordinate){
            for(int i = 0; i < 4; i++){
                Coordinate curCoordinate = new Coordinate(prevCoordinate.row+dRow[i], prevCoordinate.col+dCol[i]);
                if(isOutOfRange(curCoordinate)){
                    continue;
                }
                if(isNotSameNumber(curCoordinate)){
                    continue;
                }
                if(isAlreadyVisited(curCoordinate)){
                    continue;
                }
                targetBlockCount++;
                isVisited[curCoordinate.row][curCoordinate.col] = true;
                dfs(curCoordinate);
            }
        }

        private boolean isNotSameNumber(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] != targetNumber;
        }
        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 0 || gridRow < coordinate.row || coordinate.col < 0 || gridCol < coordinate.col;
        }
        private boolean isAlreadyVisited(Coordinate coordinate){
            return isVisited[coordinate.row][coordinate.col];
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
    }
}

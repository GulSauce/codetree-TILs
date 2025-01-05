import java.nio.charset.CoderResult;
import java.util.*;

public class Main {
    private static class Solver{
        boolean isEscaped = false;

        int rowIndex;
        int colIndex;

        Coordinate source;
        Coordinate dest;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        boolean[][] isVisited;

        int[][] grid;

        public Solver(
                int[][] grid
        ){
            this.grid = grid;
            this.rowIndex = grid.length-1;
            this.colIndex = grid[0].length-1;
            this.isVisited = new boolean[rowIndex+1][colIndex+1];
            this.source = new Coordinate(0, 0);
            this.dest = new Coordinate(rowIndex, colIndex);
        }

        public void solve(){
            initIsVisited();
            bfs();
            printResult();
        }

        private void printResult(){
            if(isEscaped){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }

        private void bfs(){
            Queue<Coordinate> q = new LinkedList<>();
            isVisited[source.row][source.col] = true;
            q.add(source);
            while(!q.isEmpty()){
                Coordinate prevCoordinate = q.poll();
                if(dest.same(prevCoordinate)){
                    isEscaped = true;
                    break;
                }
                for(int i = 0; i < 4; i++){
                    Coordinate curCoordinate = new Coordinate(prevCoordinate.row+dRow[i], prevCoordinate.col+dCol[i]);
                    if(isOutOfRange(curCoordinate)){
                        continue;
                    }
                    if(isSnake(curCoordinate)){
                        continue;
                    }
                    if(isAlreadyVisited(curCoordinate)){
                        continue;
                    }
                    isVisited[curCoordinate.row][curCoordinate.col] = true;
                    q.add(curCoordinate);
                }
            }
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 0 || rowIndex < coordinate.row || coordinate.col< 0 || colIndex < coordinate.col;
        }

        private boolean isAlreadyVisited(Coordinate coordinate){
            return isVisited[coordinate.row][coordinate.col];
        }

        private boolean isSnake(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] == 0;
        }

        private void initIsVisited(){
            for(boolean[] array: isVisited){
                Arrays.fill(array, false);
            }
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

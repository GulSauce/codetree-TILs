import java.util.*;

public class Main {
    private static class Solver{
        int gridRow;
        int gridCol;

        Coordinate startCoordinate;
        Coordinate endCoordinate;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        boolean[][] visited;

        int[][] dist;
        int[][] grid;

        public Solver(
                int[][] grid
        ){
            this.grid = grid;
            this.gridRow = grid.length-1;
            this.gridCol = grid[0].length-1;
            this.visited = new boolean[gridRow+1][gridCol+1];
            this.dist = new int[gridRow+1][gridCol+1];
            this.startCoordinate = new Coordinate(0, 0);
            this.endCoordinate = new Coordinate(gridRow, gridCol);
        }

        public void solve(){
            initVisited();
            bfs();
            printAnswer();
        }

        private void printAnswer(){
            int result = dist[endCoordinate.row][endCoordinate.col];
            if(result == 0){
                System.out.println(-1);
            }else{
                System.out.println(result);
            }
        }

        private void bfs(){
            Queue<Coordinate> q = new LinkedList<>();
            dist[startCoordinate.row][startCoordinate.col] = 0;
            visited[startCoordinate.row][startCoordinate.col] = true;
            q.add(startCoordinate);

            while(!q.isEmpty()){
                Coordinate prev = q.poll();
                for(int i = 0; i < 4; i++){
                    Coordinate cur = new Coordinate(prev.row+dRow[i], prev.col+dCol[i]);
                    if(isOutOfRange(cur)){
                        continue;
                    }
                    if(isVisited(cur)){
                        continue;
                    }
                    if(isSnake(cur)){
                        continue;
                    }

                    dist[cur.row][cur.col] = dist[prev.row][prev.col] + 1;
                    visited[cur.row][cur.col] = true;
                    q.add(cur);
                }
            }
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 0 || gridRow < coordinate.row || coordinate.col < 0 || gridCol < coordinate.col;
        }

        private boolean isVisited(Coordinate coordinate){
            return visited[coordinate.row][coordinate.col];
        }

        private boolean isSnake(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] == 0;
        }

        private void initVisited(){
            for(boolean[] array: visited){
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
    }
}
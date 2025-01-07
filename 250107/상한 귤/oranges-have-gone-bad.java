import java.util.*;

public class Main {
    private static class Solver{
        int gridIndex;

        final int BAD = 2;
        final int NORMAL = 1;
        final int EMPTY = 0;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        List<Coordinate> bads = new ArrayList<>();

        boolean[][] visited;

        int[][] dist;
        int[][] grid;

        public Solver(
                int[][] grid
        ){
            this.grid = grid;
            this.gridIndex = grid.length-1;
            this.visited = new boolean[gridIndex+1][gridIndex+1];
            this.dist = new int[gridIndex+1][gridIndex+1];
        }

        public void solve(){
            initVisited();
            initDist();
            getBads();
            bfs();
            printAnswer();
        }

        private void printAnswer(){
            for(int row = 0; row <= gridIndex; row++){
                for(int col = 0; col <= gridIndex; col++){
                    if(grid[row][col] == EMPTY){
                        System.out.printf("%d ", -1);
                        continue;
                    }
                    if(dist[row][col] == -1){
                        System.out.printf("%d ", -2);
                        continue;
                    }
                    System.out.printf("%d ", dist[row][col]);
                }
                System.out.println();
            }
        }

        private void initVisited(){
            for(boolean[] array: visited){
                Arrays.fill(array, false);
            }
        }

        private void initDist(){
            for(int[] array: dist){
                Arrays.fill(array, -1);
            }
        }


        private void bfs(){
            Queue<Coordinate> q = new LinkedList<>();
            for(Coordinate bad: bads){
                visited[bad.row][bad.col] = true;
                dist[bad.row][bad.col] = 0;
                q.add(bad);
            }
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
                    if(isEmpty(cur)){
                        continue;
                    }

                    dist[cur.row][cur.col] = dist[prev.row][prev.col] + 1;
                    visited[cur.row][cur.col] = true;
                    q.add(cur);
                }
            }
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 0 || gridIndex < coordinate.row || coordinate.col < 0 || gridIndex < coordinate.col;
        }

        private boolean isVisited(Coordinate coordinate){
            return visited[coordinate.row][coordinate.col];
        }

        private boolean isEmpty(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] == EMPTY;
        }

        private void getBads(){
            for(int row = 0; row <= gridIndex; row++){
                for(int col = 0; col <= gridIndex; col++){
                    if(grid[row][col] == BAD){
                        bads.add(new Coordinate(row, col));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
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
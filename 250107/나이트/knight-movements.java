import java.util.*;

public class Main {
    private static class Solver{
        int gridIndex;

        Coordinate start;
        Coordinate end;

        int[] dRow = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dCol = {2, 1, -1, -2, -2, -1, 1, 2};

        boolean[][] visited;

        int[][] dist;
        int[][] grid;

        public Solver(
                int[][] grid,
                Coordinate start,
                Coordinate end
        ){
            this.start = start;
            this.end = end;
            this.grid = grid;
            this.gridIndex = grid.length-1;
            this.dist = new int[gridIndex+1][gridIndex+1];
            this.visited = new boolean[gridIndex+1][gridIndex+1];
        }

        public void solve(){
            initVisited();
            bfs();
            printResult();
        }

        private void printResult(){
            int result = dist[end.row][end.col];
            if(result == 0){
                System.out.println(-1);
            }else{
                System.out.println(result);
            }
        }

        private void bfs(){
            Queue<Coordinate> q = new LinkedList<>();
            dist[start.row][start.col] = 0;
            visited[start.row][start.col] = true;
            q.add(start);
            while(!q.isEmpty()){
                Coordinate prev = q.poll();
                for(int i = 0; i < 8; i++){
                    Coordinate cur = new Coordinate(prev.row+dRow[i], prev.col+dCol[i]);
                    if(isOutOfRange(cur)){
                        continue;
                    }
                    if(isVisited(cur)){
                        continue;
                    }

                    dist[cur.row][cur.col] = dist[prev.row][prev.col] + 1;
                    visited[cur.row][cur.col] = true;
                    q.add(cur);
                }
            }
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 1 || gridIndex < coordinate.row || coordinate.col < 1 || gridIndex < coordinate.col;
        }

        private boolean isVisited(Coordinate coordinate){
            return visited[coordinate.row][coordinate.col];
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
        int r1 = sc.nextInt();
        int c1 = sc.nextInt();
        int r2 = sc.nextInt();
        int c2 = sc.nextInt();

        int[][] grid = new int[n+1][n+1];
        new Solver(grid, new Coordinate(r1, c1), new Coordinate(r2, c2)).solve();
    }

    private static class Coordinate{
        int row;
        int col;

        public Coordinate(
                int r,
                int c
        ) {
            this.row = r;
            this.col = c;
        }
    }
}
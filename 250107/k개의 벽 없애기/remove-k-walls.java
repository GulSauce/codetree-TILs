import java.util.*;

public class Main {
    private static class Solver{
        int answer = Integer.MAX_VALUE;
        int gridIndex;
        int removeCount;

        final int NOT_REACHABLE = -1;
        final int MOVABLE = 0;
        final int WALL = 1;

        Coordinate start;
        Coordinate end;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        List<Coordinate> walls = new ArrayList<>();

        boolean[][] visited;

        int[][] dist;
        int[][] grid;

        public Solver(
                int[][] grid,
                int k,
                Coordinate start,
                Coordinate end
        ){
            this.grid = grid;
            this.gridIndex = grid.length-1;
            this.visited = new boolean[gridIndex+1][gridIndex+1];
            this.dist = new int[gridIndex+1][gridIndex+1];
            this.removeCount = k;
            this.start = start;
            this.end = end;
        }

        public void solve(){
            getWalls();
            combination(-1, 0);
            printAnswer();
        }

        private void printAnswer(){
            if(answer == Integer.MAX_VALUE){
                System.out.println(NOT_REACHABLE);
                return;
            }
            System.out.println(answer);
        }

        private void combination(int lastIndex, int curCount){
            if(curCount == removeCount){
                initVisited();
                initDist();
                bfs();
                return;
            }
            for(int i = lastIndex+1; i < walls.size(); i++){
                Coordinate cur = walls.get(i);
                grid[cur.row][cur.col] = MOVABLE;
                combination(i, curCount+1);
                grid[cur.row][cur.col] = WALL;
            }
        }

        private void bfs(){
            Queue<Coordinate> q = new LinkedList<>();
            visited[start.row][start.row] = true;
            dist[start.row][start.col] = 0;
            q.add(start);
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
                    if(isWall(cur)){
                        continue;
                    }

                    visited[cur.row][cur.col] = true;
                    dist[cur.row][cur.col] = dist[prev.row][prev.col] + 1;
                    q.add(cur);
                }
            }
            int result = dist[end.row][end.col];
            if(result == NOT_REACHABLE){
                return;
            }
            answer = Math.min(answer, result);
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 1 || gridIndex < coordinate.row || coordinate.col < 1 || gridIndex < coordinate.col;
        }

        private boolean isVisited(Coordinate coordinate){
            return visited[coordinate.row][coordinate.col];
        }

        private boolean isWall(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] == WALL;
        }

        private void initDist(){
            for(int[] array: dist){
                Arrays.fill(array, NOT_REACHABLE);
            }
        }

        private void initVisited(){
            for (boolean[] array: visited){
                Arrays.fill(array, false);
            }
        }

        private void getWalls(){
            for(int row = 1; row <= gridIndex; row++){
                for(int col = 1; col <= gridIndex; col++){
                    if(grid[row][col] == WALL){
                        walls.add(new Coordinate(row, col));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] grid = new int[n+1][n+1];
        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= n; col++){
                grid[row][col] = sc.nextInt();
            }
        }

        int r1 = sc.nextInt();
        int c1 = sc.nextInt();
        Coordinate start = new Coordinate(r1, c1);

        int r2 = sc.nextInt();
        int c2 = sc.nextInt();
        Coordinate end = new Coordinate(r2, c2);

        new Solver(grid, k, start, end).solve();
    }

    private static class Coordinate{
        int row;
        int col;

        public Coordinate(
                int r,
                int c
        ){
            this.row = r;
            this.col = c;
        }
    }
}
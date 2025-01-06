import java.util.*;

public class Main {
    private static class Solver{
        int elapsedTime= 0;
        int meltGlacierCount = 0;

        int gridRow;
        int gridCol;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        boolean[][] visited;

        int[][] grid;

        List<Coordinate> meltGlaciers = new ArrayList<>();

        public Solver(
                int[][] grid
        ){
            this.grid = grid;
            this.gridRow = grid.length-1;
            this.gridCol = grid[0].length -1;
            this.visited = new boolean[gridRow+1][gridCol+1];
        }

        public void solve(){
            do {
                meltGlaciers.clear();
                meltGlacierCount = 0;
                elapsedTime++;
                initIsVisited();
                makeGlacierWater();
            } while (isGlacierExist());
            System.out.printf("%d %d", elapsedTime, meltGlacierCount);
        }

        private void initIsVisited(){
            for(boolean[] array: visited){
                Arrays.fill(array, false);
            }
        }

        private boolean isGlacierExist(){
            for(int row =0; row < gridRow; row++){
                for(int col = 0; col < gridCol; col++){
                    if(grid[row][col] == 1){
                        return true;
                    }
                }
            }
            return false;
        }

        private void makeGlacierWater(){
            Queue<Coordinate> q = new LinkedList<>();
            visited[0][0] = true;
            q.add(new Coordinate(0,0));
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
                     if(isGlacier(cur)){
                         continue;
                     }
                     memorizeNearGlacier(cur);
                     visited[cur.row][cur.col] = true;
                     q.add(cur);
                }
            }
            for(Coordinate coordinate: meltGlaciers){
                meltGlacierCount++;
                grid[coordinate.row][coordinate.col] = 0;
            }
        }

        private void memorizeNearGlacier(Coordinate coordinate){
            for(int i = 0; i < 4; i++){
                Coordinate cur = new Coordinate(coordinate.row+dRow[i], coordinate.col+dCol[i]);
                if(isOutOfRange(cur)){
                    continue;
                }
                if(isVisited(cur)){
                    continue;
                }
                if(isGlacier(cur)){
                    visited[cur.row][cur.col] = true;
                    meltGlaciers.add(cur);
                }
            }
        }

        private boolean isVisited(Coordinate coordinate){
            return visited[coordinate.row][coordinate.col];
        }

        private boolean isGlacier(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] == 1;
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 0 || gridRow < coordinate.row || coordinate.col < 0 || gridCol < coordinate.col;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] grid = new int[N][M];
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
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
import java.util.*;

public class Main {
    private static class Solver{
        int maxSelectCount;
        int gridIndex;
        int gridSize;
        int maxVisitedCountry = 0;
        int curVisitedCountry = 0;

        int u;
        int d;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        List<Coordinate> selectedCountry = new ArrayList<>();

        boolean[][] visited;

        int[][] grid;

        public Solver(
                int[][] grid,
                int u,
                int d,
                int k
        ){
            this.grid = grid;
            this.gridIndex = grid.length-1;
            this.gridSize = grid.length*grid.length;
            this.visited = new boolean[grid.length][grid.length];
            this.u = u;
            this.d = d;
            this.maxSelectCount = k;
        }

        public void solve(){
            getCombination(-1, 0);
            System.out.println(maxVisitedCountry);
        }

        private void getCombination(int lastIndex, int selectCount){
            if(selectCount == maxSelectCount){
                curVisitedCountry = 0;
                initVisited();
                bfs();
                maxVisitedCountry = Math.max(maxVisitedCountry, curVisitedCountry);
            }

            for(int i = lastIndex+1; i < gridSize; i++){
                Coordinate cuCoordinate = getCurCoordinate(i);
                selectedCountry.add(cuCoordinate);
                getCombination(i, selectCount+1);
                selectedCountry.remove(selectedCountry.size()-1);
            }
        }

        private Coordinate getCurCoordinate(int index){
            int row = index/3;
            int col = index%3;
            return new Coordinate(row, col);
        }

        private void bfs(){
            Queue<Coordinate> q = new LinkedList<>();
            for(Coordinate coordinate: selectedCountry){
                curVisitedCountry++;
                visited[coordinate.row][coordinate.col] = true;
                q.add(coordinate);
            }
            while(!q.isEmpty()){
                Coordinate prevCoordinate = q.poll();
                for(int i =0; i < 4; i++){
                    Coordinate curCoordinate = new Coordinate(prevCoordinate.row+dRow[i], prevCoordinate.col+dCol[i]);
                    if(isOutOfRange(curCoordinate)){
                        continue;
                    }
                    if(isVisited(curCoordinate)){
                        continue;
                    }
                    int prevValue = grid[prevCoordinate.row][prevCoordinate.col];
                    int curValue = grid[curCoordinate.row][curCoordinate.col];
                    if(!isMovable(prevValue, curValue)){
                        continue;
                    }
                    curVisitedCountry++;
                    visited[curCoordinate.row][curCoordinate.col] = true;
                    q.add(curCoordinate);
                }
            }
        }

        public boolean isMovable(int height1, int height2){
            int diff = Math.abs(height1 - height2);
            return  u <= diff && diff <= d;
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 0 || gridIndex < coordinate.row || coordinate.col < 0 || gridIndex < coordinate.col;
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
        int k = sc.nextInt();
        int u = sc.nextInt();
        int d = sc.nextInt();

        int[][] grid = new int[n][n];
        for(int row = 0; row < n; row++){
            for(int col = 0; col < n; col++){
                grid[row][col] = sc.nextInt();
            }
        }

        new Solver(grid, u, d, k).solve();
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

    private static class MovableChecker{
        int u;
        int d;

        public MovableChecker(
                int u,
                int d
        ){
            this.u = u;
            this.d = d;
        }


    }
}
import java.util.*;

public class Main {
    private static class Solver{
        int visitedBlock = 0;
        int gridRow;
        int gridCol;

        Coordinate startPoint;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        List<Coordinate> startPoints;

        boolean[][] isVisited;

        int[][] grid;

        public Solver(
                int[][] grid,
                List<Coordinate> startPoints
        ){
            this.grid = grid;
            this.isVisited = new boolean[grid.length][grid.length];
            this.gridRow = grid.length-1;
            this.gridCol = gridRow;
            this.startPoints = startPoints;
        }

        public void solve(){
            initIsVisited();
            bfsEachStartPoint();
            printAnswer();
        }

        private void bfsEachStartPoint(){
            for(Coordinate startPoint: startPoints){
                if(isAlreadyVisited(startPoint)){
                    continue;
                }
                this.startPoint = startPoint;
                bfs();
            }
        }

        private void bfs(){
            Queue<Coordinate> q = new LinkedList<>();
            visitedBlock++;
            isVisited[startPoint.row][startPoint.col] = true;
            q.add(startPoint);
            while(!q.isEmpty()){
                Coordinate prevCoordinate = q.poll();
                for(int i = 0; i < 4; i++){
                    Coordinate curCoordinate = new Coordinate(prevCoordinate.row+dRow[i], prevCoordinate.col+dCol[i]);
                    if(isOutOfRange(curCoordinate)){
                        continue;
                    }
                    if(isAlreadyVisited(curCoordinate)){
                        continue;
                    }
                    if(isCannotGo(curCoordinate)){
                        continue;
                    }
                    visitedBlock++;
                    isVisited[curCoordinate.row][curCoordinate.col] = true;
                    q.add(curCoordinate);
                }
            }
        }

        private boolean isCannotGo(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] == 1;
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 1 || gridRow < coordinate.row || coordinate.col < 1 || gridCol < coordinate.col;
        }

        private boolean isAlreadyVisited(Coordinate coordinate){
            return isVisited[coordinate.row][coordinate.col];
        }

        private void printAnswer(){
            System.out.println(visitedBlock);
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
        int k = sc.nextInt();

        int[][] grid = new int[n+1][n+1];
        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= n; col++){
                grid[row][col] = sc.nextInt();
            }
        }
        List<Coordinate> startPoints = new ArrayList<>();

        for(int i = 0; i < k; i++){
            int r = sc.nextInt();
            int c = sc.nextInt();
            startPoints.add(new Coordinate(r, c));
        }

        new Solver(grid, startPoints).solve();
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
import java.util.*;

public class Main {
    private static class Solver{
        int gridIndex;
        int repeatCount;
        int targetNumber;

        Coordinate targetCoordinate;
        Coordinate startCoordinate;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        boolean[][] isVisited;

        int[][] grid;

        public Solver(
                int[][] grid,
                int k,
                Coordinate startCoordinate
        ){
            this.grid = grid;
            this.gridIndex = grid.length-1;
            this.isVisited = new boolean[grid.length][grid.length];
            this.repeatCount = k;
            this.startCoordinate = startCoordinate;
        }

        public void solve(){
            initIsVisited();
            repeatBFS();
            printAnswer();
        }

        private void initIsVisited(){
            for(boolean[] array: isVisited){
                Arrays.fill(array, false);
            }
        }

        private void repeatBFS(){
            targetNumber = grid[startCoordinate.row][startCoordinate.col];
            targetCoordinate = startCoordinate;
            for(int i = 0; i < repeatCount; i++) {
                initIsVisited();
                bfs();
            }
        }

        private void printAnswer(){
            System.out.printf("%d %d", targetCoordinate.row, targetCoordinate.col);
        }

        private void bfs(){
            int nextTargetNumber = -1;
            Coordinate nextTargetCoordinate = new Coordinate(1000, 1000);

            Queue<Coordinate> q = new LinkedList<>();
            q.add(targetCoordinate);
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

                    int curNumber = grid[curCoordinate.row][curCoordinate.col];
                    if(targetNumber <= curNumber){
                        continue;
                    }
                    if(nextTargetNumber < curNumber){
                        nextTargetNumber = curNumber;
                        nextTargetCoordinate = curCoordinate;
                    }
                    else if(nextTargetNumber == curNumber){
                        if(nextTargetCoordinate.lessThan(curCoordinate)){
                            continue;
                        }
                        nextTargetCoordinate = curCoordinate;
                    }

                    isVisited[curCoordinate.row][curCoordinate.col] = true;
                    q.add(curCoordinate);
                }
            }
            if(nextTargetNumber == -1){
                return;
            }

            targetNumber = nextTargetNumber;
            targetCoordinate = nextTargetCoordinate;
        }

        private boolean isAlreadyVisited(Coordinate coordinate){
            return isVisited[coordinate.row][coordinate.col];
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 1 || gridIndex < coordinate.row || coordinate.col < 1 || gridIndex < coordinate.col;
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
        int r = sc.nextInt();
        int c = sc.nextInt();
        Coordinate startCoordinate = new Coordinate(r, c);

        new Solver(grid, k, startCoordinate).solve();
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

        private boolean lessThan(Coordinate coordinate){
            if(this.row == coordinate.row){
                return this.col <= coordinate.col;
            }
            return this.row < coordinate.row;
        }
    }
}
import java.util.*;

public class Main {
    private static class Solver{
        int maxMoveCount = 0;
        int currentMoveCount = 0;

        int[][] grid;
        int[][] arrowInfos;
        Coordinate startCoordinate;

        public Solver(
                int[][] grid,
                int[][] arrowInfos,
                Coordinate startCoordinate
        ){
            this.grid = grid;
            this.arrowInfos = arrowInfos;
            this.startCoordinate = startCoordinate;
        }

        public void solve(){
            getCombination(startCoordinate);
            System.out.println(maxMoveCount);
        }

        private void getCombination(Coordinate currentCoordinate){
            maxMoveCount = Math.max(maxMoveCount, currentMoveCount);

            currentMoveCount++;
            if(arrowInfos[currentCoordinate.row][currentCoordinate.col] == 1){
                Coordinate nextCoordinate = new Coordinate(currentCoordinate.row, currentCoordinate.col);
                nextCoordinate.row = nextCoordinate.row-1;
                while(isInRange(nextCoordinate)){
                    if(grid[nextCoordinate.row][nextCoordinate.col] > grid[currentCoordinate.row][currentCoordinate.col]) {
                        getCombination(nextCoordinate);
                    }
                    nextCoordinate.row = nextCoordinate.row-1;
                }
            }
            if(arrowInfos[currentCoordinate.row][currentCoordinate.col] == 2){
                Coordinate nextCoordinate = new Coordinate(currentCoordinate.row, currentCoordinate.col);
                nextCoordinate.row = nextCoordinate.row-1;
                nextCoordinate.col = nextCoordinate.col+1;
                while(isInRange(nextCoordinate)){
                    if(grid[nextCoordinate.row][nextCoordinate.col] > grid[currentCoordinate.row][currentCoordinate.col]) {
                        getCombination(nextCoordinate);
                    }
                    nextCoordinate.row = nextCoordinate.row-1;
                    nextCoordinate.col = nextCoordinate.col+1;
                }
            }
            if(arrowInfos[currentCoordinate.row][currentCoordinate.col] == 3){
                Coordinate nextCoordinate = new Coordinate(currentCoordinate.row, currentCoordinate.col);
                nextCoordinate.col = nextCoordinate.col+1;
                while(isInRange(nextCoordinate)){
                    if(grid[nextCoordinate.row][nextCoordinate.col] > grid[currentCoordinate.row][currentCoordinate.col]) {
                        getCombination(nextCoordinate);
                    }
                    nextCoordinate.col = nextCoordinate.col+1;
                }
            }
            if(arrowInfos[currentCoordinate.row][currentCoordinate.col] == 4){
                Coordinate nextCoordinate = new Coordinate(currentCoordinate.row, currentCoordinate.col);
                nextCoordinate.row = nextCoordinate.row+1;
                nextCoordinate.col = nextCoordinate.col+1;
                while(isInRange(nextCoordinate)){
                    if(grid[nextCoordinate.row][nextCoordinate.col] > grid[currentCoordinate.row][currentCoordinate.col]) {
                        getCombination(nextCoordinate);
                    }
                    nextCoordinate.row = nextCoordinate.row+1;
                    nextCoordinate.col = nextCoordinate.col+1;
                }
            }
            if(arrowInfos[currentCoordinate.row][currentCoordinate.col] == 5){
                Coordinate nextCoordinate = new Coordinate(currentCoordinate.row, currentCoordinate.col);
                nextCoordinate.row = nextCoordinate.row+1;
                while(isInRange(nextCoordinate)){
                    if(grid[nextCoordinate.row][nextCoordinate.col] > grid[currentCoordinate.row][currentCoordinate.col]) {
                        getCombination(nextCoordinate);
                    }
                    nextCoordinate.row = nextCoordinate.row+1;
                }
            }
            if(arrowInfos[currentCoordinate.row][currentCoordinate.col] == 6){
                Coordinate nextCoordinate = new Coordinate(currentCoordinate.row, currentCoordinate.col);
                nextCoordinate.row = nextCoordinate.row+1;
                nextCoordinate.col = nextCoordinate.col-1;
                while(isInRange(nextCoordinate)){
                    if(grid[nextCoordinate.row][nextCoordinate.col] > grid[currentCoordinate.row][currentCoordinate.col]) {
                        getCombination(nextCoordinate);
                    }
                    nextCoordinate.row = nextCoordinate.row+1;
                    nextCoordinate.col = nextCoordinate.col-1;
                }
            }
            if(arrowInfos[currentCoordinate.row][currentCoordinate.col] == 7) {
                Coordinate nextCoordinate = new Coordinate(currentCoordinate.row, currentCoordinate.col);
                nextCoordinate.col = nextCoordinate.col-1;
                while(isInRange(nextCoordinate)){
                    if(grid[nextCoordinate.row][nextCoordinate.col] > grid[currentCoordinate.row][currentCoordinate.col]) {
                        getCombination(nextCoordinate);
                    }
                    nextCoordinate.col = nextCoordinate.col-1;
                }
            }
            if(arrowInfos[currentCoordinate.row][currentCoordinate.col] == 8) {
                Coordinate nextCoordinate = new Coordinate(currentCoordinate.row, currentCoordinate.col);
                nextCoordinate.row = nextCoordinate.row-1;
                nextCoordinate.col = nextCoordinate.col-1;
                while(isInRange(nextCoordinate)){
                    if(grid[nextCoordinate.row][nextCoordinate.col] > grid[currentCoordinate.row][currentCoordinate.col]) {
                        getCombination(nextCoordinate);
                    }
                    nextCoordinate.row = nextCoordinate.row-1;
                    nextCoordinate.col = nextCoordinate.col-1;
                }
            }
            currentMoveCount--;
        }

        private boolean isInRange(Coordinate nextCoordinate){
            return 1 <= nextCoordinate.row && nextCoordinate.row <= grid.length-1 && 1 <= nextCoordinate.col && nextCoordinate.col <= grid.length-1;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] grid = new int[n+1][n+1];
        int[][] arrowInfos = new int[n+1][n+1];
        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= n; col++){
                grid[row][col] = sc.nextInt();
            }
        }

        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= n; col++){
                arrowInfos[row][col] = sc.nextInt();
            }
        }

        Coordinate startCoordinate = new Coordinate(sc.nextInt(), sc.nextInt());

        new Solver(grid, arrowInfos, startCoordinate).solve();
    }

    private static class Coordinate{
        int row;
        int col;

        public Coordinate(
                int r,
                int c
        ){
            this.row = r;
            this.col =c;
        }
    }
}
import java.util.*;

public class Main {
    private static class Solver{
        int gridLength;
        int bombCount;
        int maxExplodeArea = 0;

        int[][] grid;
        int[][] explodeGrid;

        List<Coordinate> bomb;
        List<Integer> bombCombinations = new ArrayList<>();

        public Solver(
                int[][] grid,
                List<Coordinate> bomb,
                int bombCount
        ){
            this.grid = grid;
            this.bomb = bomb;
            this.bombCount = bombCount;

            this.gridLength = grid.length;
            this.explodeGrid = new int[grid.length][grid.length];
        }

        public void solve(){
            explodeWithCombinations(1);
            System.out.print(maxExplodeArea);
        }

        private void explodeWithCombinations(int curNumber){
            if(bombCount < curNumber){
                explodeArea();
                return;
            }
            for(int bombType = 1; bombType <= 3; bombType++){
                bombCombinations.add(bombType);
                explodeWithCombinations(curNumber+1);
                bombCombinations.remove(bombCombinations.size()-1);
            }
        }

        private void explodeArea(){
            initExplodeGrid();
            for(int i = 0; i < bomb.size(); i++){
                int bombType = bombCombinations.get(i);
                setBomb(bombType, bomb.get(i));
            }
            int explodeAreaCount = getExplodeAreaCount();
            maxExplodeArea = Math.max(explodeAreaCount, maxExplodeArea);
        }

        private void setBomb(int bombType, Coordinate startCoordinate){
            explodeGrid[startCoordinate.row][startCoordinate.col] = 1;

            if(bombType == 1){
                if(isInGrid(startCoordinate.row-2, startCoordinate.col)){
                    explodeGrid[startCoordinate.row-2][startCoordinate.col] = 1;
                }
                if(isInGrid(startCoordinate.row-1, startCoordinate.col)) {
                    explodeGrid[startCoordinate.row-1][startCoordinate.col] = 1;
                }
                if(isInGrid(startCoordinate.row+1, startCoordinate.col)) {
                    explodeGrid[startCoordinate.row+1][startCoordinate.col] = 1;
                }
                if(isInGrid(startCoordinate.row+2, startCoordinate.col)) {
                    explodeGrid[startCoordinate.row+2][startCoordinate.col] = 1;
                }
            }
            if(bombType == 2){
                if(isInGrid(startCoordinate.row, startCoordinate.col+1)){
                    explodeGrid[startCoordinate.row][startCoordinate.col+1] = 1;
                }
                if(isInGrid(startCoordinate.row-1, startCoordinate.col)) {
                    explodeGrid[startCoordinate.row-1][startCoordinate.col] = 1;
                }
                if(isInGrid(startCoordinate.row, startCoordinate.col-1)) {
                    explodeGrid[startCoordinate.row][startCoordinate.col-1] = 1;
                }
                if(isInGrid(startCoordinate.row+1, startCoordinate.col)) {
                    explodeGrid[startCoordinate.row+1][startCoordinate.col] = 1;
                }
            }
            if(bombType == 3){
                if(isInGrid(startCoordinate.row-1, startCoordinate.col+1)) {
                    explodeGrid[startCoordinate.row-1][startCoordinate.col+1] = 1;
                }
                if(isInGrid(startCoordinate.row-1, startCoordinate.col-1)) {
                    explodeGrid[startCoordinate.row-1][startCoordinate.col-1] = 1;
                }
                if(isInGrid(startCoordinate.row+1, startCoordinate.col-1)) {
                    explodeGrid[startCoordinate.row+1][startCoordinate.col-1] = 1;
                }
                if(isInGrid(startCoordinate.row+1, startCoordinate.col+1)) {
                    explodeGrid[startCoordinate.row+1][startCoordinate.col+1] = 1;
                }
            }
        }

        private boolean isInGrid(int row, int col){
            return 0 <= row && row < gridLength && 0 <= col && col < gridLength;
        }

        private int getExplodeAreaCount(){
            int explodeAreaCount = 0;
            for(int[] array: explodeGrid){
                for(int number: array){
                    if(number == 1){
                        explodeAreaCount++;
                    }
                }
            }
            return explodeAreaCount;
        }

        private void initExplodeGrid(){
            for(int[] array: explodeGrid){
                Arrays.fill(array, 0);
            }
        }

        private void printExplodeGrid(){
            for(int[] array: explodeGrid){
                for(int number: array){
                    System.out.printf("%d ", number);
                }
                System.out.println();
            }
            System.out.println("===========");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        List<Coordinate> bomb = new ArrayList<>();
        int bombCount = 0;
        for(int row = 0; row < n; row++){
            for(int col = 0; col < n; col++){
                grid[row][col] = sc.nextInt();
                if(grid[row][col] == 1){
                    bombCount++;
                    bomb.add(new Coordinate(row, col));
                }
            }
        }
        new Solver(grid, bomb, bombCount).solve();
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
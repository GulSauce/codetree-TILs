import java.util.*;

public class Main {
    private static class Solver{
        final int minConsecutiveToExplode;
        final int totalRotations;
        final int gridSize;
        final int[][] grid;

        public Solver(
                int[][] grid,
                int M,
                int K
        ){
            this.grid = grid;
            this.minConsecutiveToExplode = M;
            this.totalRotations = K;
            this.gridSize = grid.length-1;
        }

        public void solve(){
            for(int round = 0; round <= totalRotations; round++){
                performExplosions();
                rotateClockWise90();
            }
            printResult();
        }

        private void printResult(){
            int result = 0;

            for(int row = 1; row <= gridSize; row++){
                for(int col = 1; col <= gridSize; col++){
                    if(grid[row][col] != 0){
                        result++;
                    }
                }
            }

            System.out.println(result);
        }

        private void performExplosions(){
            boolean exploded;
            do{
                exploded = explodeBombs();
                applyGravity();
            } while (exploded);
        }

        private boolean explodeBombs(){
            boolean explodedThisRound = false;

            for(int col = 1; col <= gridSize; col++){
                // 초기
                int count = 1;

                // 처리
                for(int row = 2; row <= gridSize; row++){
                    if(grid[row][col] != 0 && grid[row][col] == grid[row-1][col]){
                        count++;
                    } else{
                        if(count >= minConsecutiveToExplode){
                            markForExplosion(col, row-1, count);
                            explodedThisRound = true;
                        }
                        count = 1;
                    }
                }

                // 종료
                if(grid[gridSize][col] != 0 && count >= minConsecutiveToExplode){
                    markForExplosion(col, gridSize, count);
                    explodedThisRound= true;
                }
            }

            return explodedThisRound;
        }

        private void markForExplosion(int col, int endRow, int count){
            for(int row = endRow; row > endRow - count; row--){
                grid[row][col] = 0;
            }
        }

        private void rotateClockWise90(){
            int[][] copiedNumbers = copyGrid();
            for(int row = 1; row <= gridSize; row++){
                for(int col = 1; col <= gridSize; col++){
                    int reversCol = gridSize - col + 1;
                    grid[row][col] = copiedNumbers[reversCol][row];
                }
            }
            applyGravity();
        }

        private int[][] copyGrid(){
            int[][] copiedGrid = new int[gridSize+1][gridSize+1];

            for(int row = 1; row <= gridSize; row++){
                for(int col = 1; col <= gridSize; col++){
                    copiedGrid[row][col] = grid[row][col];
                }
            }
            return copiedGrid;
        }

        private void printMatrix(){
            for(int row = 1; row <= gridSize; row++){
                for(int col = 1; col <= gridSize; col++){
                    System.out.printf("%d ", grid[row][col]);
                }
                System.out.println();
            }
            System.out.println("============");
        }

        private void applyGravity() {
            for(int col = 1; col <= gridSize; col++){
                int placementRow = gridSize;
                for(int row = gridSize; row >= 1; row--){
                    if(grid[row][col] == 0){
                        continue;
                    }
                    grid[placementRow][col] = grid[row][col];
                    if(placementRow != row){
                        grid[row][col] = 0;
                    }
                    placementRow--;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        int[][] numbers = new int[N+1][N+1];
        for(int row = 1; row <= N; row++){
            for(int col = 1; col <= N; col++) {
                numbers[row][col] = sc.nextInt();
            }
        }

        new Solver(numbers, M, K).solve();
    }
}
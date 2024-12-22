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
            this.gridSize = grid.length;
        }

        public void solve(){
            for(int i = 0; i <= totalRotations; i++){
                performExplosions();
                rotateClockWise90();
            }
            printResult();
        }

        private void printResult(){
            int result = 0;

            for(int row = 0; row < gridSize; row++){
                for(int col = 0; col < gridSize; col++){
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

            for(int col = 0; col < gridSize; col++){
                // 초기
                int count = 1;

                // 처리
                for(int row = 1; row < gridSize; row++){
                    if(grid[row][col] != 0 && grid[row][col] == grid[row-1][col]){
                        count++;
                    } else{
                        if(count >= minConsecutiveToExplode){
                            markForExplosion(col, row, count);
                            explodedThisRound = true;
                        }
                        count = 1;
                    }
                }

                // 종료
                if(count >= minConsecutiveToExplode){
                    markForExplosion(col, gridSize-1, count);
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
            for(int row = 0; row < gridSize; row++){
                for(int col = 0; col < gridSize; col++){
                    grid[row][col] = copiedNumbers[gridSize - 1 - col][row];
                }
            }
            applyGravity();
        }

        private int[][] copyGrid(){
            int[][] copiedGrid = new int[gridSize][gridSize];

            for(int row = 0; row < gridSize; row++){
                for(int col = 0; col < gridSize; col++){
                    copiedGrid[row][col] = grid[row][col];
                }
            }
            return copiedGrid;
        }

        private void applyGravity() {
            for(int col = 0; col < gridSize; col++){
                int placementRow = gridSize - 1;
                for(int row = gridSize-1; row >= 0; row--){
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

        int[][] numbers = new int[N][N];
        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++) {
                numbers[y][x] = sc.nextInt();
            }
        }

        new Solver(numbers, M, K).solve();
    }
}
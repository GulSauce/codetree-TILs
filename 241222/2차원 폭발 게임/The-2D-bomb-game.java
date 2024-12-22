import java.util.*;

public class Main {
    private static class Solver{
        int[][] bombGrid;
        int gridLength;
        int minConsecutiveToExplode;
        int totalRotation;

        final int EMPTY_MARK = 0;

        public Solver(
                int[][] bombGrid,
                int M,
                int K
        ){
            this.bombGrid = bombGrid;
            this.gridLength = bombGrid.length-1;
            this.minConsecutiveToExplode = M;
            this.totalRotation = K;
        }

        public void solve(){
            for(int rotation = 0; rotation <= totalRotation; rotation++){
                explodeBombPossible();
                rotateClockwise90();
                applyGravity();
            }
            printResult();
        }

        private void explodeBombPossible(){
            boolean isExplode;
            do{
                isExplode = explodeBomb();
                applyGravity();
            } while (isExplode);
        }

        private boolean explodeBomb(){
            boolean isExplode = false;

            for(int col = 1; col <= gridLength; col++){
                // 초기
                int currentConsecutive = -1;
                int currentNumber = -1;
                int startRow = -1;
                for(int row = 1; row <= gridLength; row++){
                    if(bombGrid[row][col] == 0){
                        continue;
                    }
                    currentConsecutive = 1;
                    currentNumber = bombGrid[row][col];
                    startRow = row;
                    break;
                }
                if(startRow == -1){
                    continue;
                }

                // 처리
                for(int row = startRow+1; row <= gridLength; row++){
                    if(currentNumber == bombGrid[row][col]){
                        currentConsecutive++;
                    } else{
                        if(minConsecutiveToExplode <= currentConsecutive){
                            isExplode = true;
                            markToExplode(row-1, col, currentConsecutive);
                        }
                        currentConsecutive = 1;
                        currentNumber = bombGrid[row][col];
                    }
                }

                // 종료
                int endRow = gridLength;
                if(minConsecutiveToExplode <= currentConsecutive){
                    markToExplode(endRow, col, currentConsecutive);
                }
            }
            return isExplode;
        }

        private void markToExplode(int endRow, int col, int count){
            for(int row = endRow; row > endRow-count; row--){
                bombGrid[row][col] = EMPTY_MARK;
            }
        }

        private void applyGravity(){
            for(int col = 1; col <= gridLength; col++){
                int placementRow = gridLength;
                for(int row = gridLength; row >= 1; row--){
                    if(bombGrid[row][col] == EMPTY_MARK){
                        continue;
                    }
                    if(placementRow == row){
                        placementRow--;
                        continue;
                    }
                    bombGrid[placementRow][col] = bombGrid[row][col];
                    bombGrid[row][col] = 0;
                    placementRow--;
                }
            }
        }

        private void rotateClockwise90(){
            int[][] copiedGrid = copyGrid();
            for(int row = 1; row <= gridLength; row++){
                for(int col = 1; col <= gridLength; col++){
                    int lastRow = gridLength;
                    bombGrid[row][col] = copiedGrid[lastRow-col+1][row];
                }
            }
        }

        private void printResult(){
            int bombCount = 0;

            for(int row = 1; row <= gridLength; row++){
                for(int col = 1; col <= gridLength; col++){
                    if(bombGrid[row][col] != EMPTY_MARK){
                        bombCount++;
                    }
                }
            }

            System.out.println(bombCount);
        }

        private int[][] copyGrid(){
            int[][] copiedGrid = new int[gridLength+1][gridLength+1];
            for(int row = 1; row <= gridLength; row++){
                for(int col = 1; col <= gridLength; col++){
                    copiedGrid[row][col] = bombGrid[row][col];
                }
            }
            return copiedGrid;
        }

        private void printMatrix(){
            for(int row = 1; row <= gridLength; row++){
                for(int col = 1; col <= gridLength; col++){
                    System.out.printf("%d ", bombGrid[row][col]);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        int[][] bombGrid = new int[N+1][N+1];
        for(int row = 1; row <= N; row++){
            for(int col = 1; col <= N; col++){
                bombGrid[row][col] = sc.nextInt();
            }
        }

        new Solver(bombGrid, M, K).solve();
    }
}
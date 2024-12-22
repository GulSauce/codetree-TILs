import java.util.*;

public class Main {
    private static class Solver{
        int[] bombGrid;
        int minConsecutive;
        int gridSize;
        final int EMPTY_MARK = 0;

        public Solver(
                int[] bombGrid,
                int M
        ){
            this.bombGrid =bombGrid;
            this.minConsecutive = M;
            this.gridSize = bombGrid.length-1;
        }

        public  void solve(){
            boolean isExploded;
            do{
                isExploded = explodeBombPossible();
                applyGravity();
            }while (isExploded);
            printResult();
        }

        private void printGrid(){
            for(int row = 1; row <= gridSize; row++){
                System.out.println(bombGrid[row]);
            }
            System.out.println("다운워드스파이럴호루스스테이션");
        }

        private void printResult(){
            int count = 0;
            for(int row = 1; row <= gridSize; row++){
                if(bombGrid[row] != EMPTY_MARK){
                    count++;
                }
            }

            System.out.println(count);

            for(int row = 1; row <= gridSize; row++){
                if(bombGrid[row] != EMPTY_MARK){
                    System.out.println(bombGrid[row]);
                }
            }
        }

        private boolean explodeBombPossible(){
            boolean isExploded = false;

            int consecutiveCount = -1;
            int startRow = -1;
            int currentNumber = -1;
            for(int row = 1; row <= gridSize; row++){
                if(bombGrid[row] == EMPTY_MARK) {
                    continue;
                }
                consecutiveCount = 1;
                startRow = row;
                currentNumber = bombGrid[row];
                break;
            }
            if(currentNumber == -1){
                return false;
            }

            for(int row = startRow+1; row <= gridSize; row++){
                if(currentNumber == bombGrid[row]){
                    consecutiveCount++;
                    continue;
                }
                if(minConsecutive <= consecutiveCount){
                    isExploded = true;
                    markToExplode(row-1, consecutiveCount);
                }
                consecutiveCount = 1;
                currentNumber = bombGrid[row];
            }

            if(minConsecutive <= consecutiveCount){
                markToExplode(gridSize, consecutiveCount);
            }

            return isExploded;
        }

        private void applyGravity(){
            int placementRow = gridSize;
            for(int row = gridSize; row >= 1; row--){
                if(bombGrid[row] == 0){
                    continue;
                }
                bombGrid[placementRow] = bombGrid[row];
                if(placementRow == row){
                    placementRow--;
                    continue;
                }
                placementRow--;
                bombGrid[row] = 0;
            }
        }

        private void markToExplode(int endRow, int count){
            for(int row = endRow; row > endRow-count; row--){
                bombGrid[row] = EMPTY_MARK;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] bombGrid = new int[N+1];

        for(int row = 1; row <= N; row++){
            bombGrid[row] = sc.nextInt();
        }

        new Solver(bombGrid, M).solve();
    }
}
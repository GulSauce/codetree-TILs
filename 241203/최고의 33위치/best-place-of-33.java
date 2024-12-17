import java.util.*;

public class Main {
    private static class Solver {
        int[][] matrix;
        final int MAX_LENGTH;
        final int SUB_MATRIX_LENGTH = 2;

        public Solver(
                int[][] matrix
        ){
            this.matrix = matrix;
            MAX_LENGTH = matrix.length;
        }

        public void solve(){
            int result = getResult();
            System.out.print(result);
        }

        private int getResult(){
            int result = 0;
            for(int y = 0; y < MAX_LENGTH; y++) {
                if (isOutOfMatrix(y)) {
                    continue;
                }
                for (int x = 0; x < MAX_LENGTH; x++) {
                    if (isOutOfMatrix(x)) {
                        continue;
                    }
                    int currentCount = getCurrentCount(new Coordinate(y, x));
                    result = Math.max(result, currentCount);
                }
            }
            return result;
        }

        private int getCurrentCount(Coordinate coordinate){
            int currentCount = 0;
            int y = coordinate.y;
            int x = coordinate.x;
            for(int i = y; i <= y+SUB_MATRIX_LENGTH; i++){
                for(int j = x; j <= x+SUB_MATRIX_LENGTH; j++) {
                    currentCount += matrix[i][j];
                }
            }
            return currentCount;
        }

        private boolean isOutOfMatrix(int index){
            return MAX_LENGTH <= index + SUB_MATRIX_LENGTH;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAX_LENGTH = sc.nextInt();
        int[][] matrix = new int[MAX_LENGTH][MAX_LENGTH];
        for(int y = 0; y < MAX_LENGTH; y++){
            for(int x = 0; x < MAX_LENGTH; x++){
                matrix[y][x] = sc.nextInt();
            }
        }

        new Solver(matrix).solve();
    }

    private static class Coordinate{
        int y;
        int x;

        public Coordinate(
                int y,
                int x
        ){
            this.y = y;
            this.x = x;
        }
    }
}
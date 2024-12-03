import java.util.*;

public class Main {
    private static class Solver {
        int[][] matrix;
        final int MAX_LENGTH;
        final int Y_LENGTH = 2;
        final int X_LENGTH = 2;

        public Solver(
                int[][] matrix
        ){
            this.matrix = matrix;
            MAX_LENGTH = matrix.length;
        }

        public void solve(){
            int result = 0;
            for(int y = 0; y < MAX_LENGTH; y++){
                if(isYOutOfMatrix(y)){
                    continue;
                }
                for(int x = 0; x < MAX_LENGTH; x++){
                    if(isXOutOfMatrix(x)){
                        continue;
                    }
                    int currentCount = getCurrentCount(y, x);
                    result = Math.max(result, currentCount);
                }
            }
            System.out.print(result);
        }

        private int getCurrentCount(int y, int x){
            int  currentCount = 0;
            for(int i = y; i <= y+Y_LENGTH; i++){
                for(int j = x; j <= x+X_LENGTH; j++) {
                    currentCount += matrix[i][j];
                }
            }
            return currentCount;
        }

        private boolean isYOutOfMatrix(int y){
            return MAX_LENGTH <= y+Y_LENGTH;
        }


        private boolean isXOutOfMatrix(int x){
            return MAX_LENGTH <= x+X_LENGTH;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAX_LENGTH = sc.nextInt();
        int[][] matrix = new int[MAX_LENGTH][MAX_LENGTH];
        for(int y = 0; y < MAX_LENGTH; y++){
            for(int x = 0; x < MAX_LENGTH; x++){
                int value = sc.nextInt();
                matrix[y][x] = value;
            }
        }

        new Solver(matrix).solve();
    }
}
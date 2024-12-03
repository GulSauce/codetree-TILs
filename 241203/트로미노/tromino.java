import java.util.*;

public class Main {
    private static class Solver {
        int[][] matrix;
        final int MAX_Y_INDEX;
        final int MAX_X_INDEX;

        public  Solver(
                int[][] matrix
        ){
            this.matrix = matrix;
            MAX_X_INDEX = matrix[0].length - 1;
            MAX_Y_INDEX = matrix.length - 1;
        }

        public  void solve(){
            int result = 0;
            for(int y = 0; y <= MAX_Y_INDEX; y++){
                for(int x= 0; x <= MAX_X_INDEX; x++){
                    if(is2x2Size(y, x)) {
                        result = Math.max(result, getBlock1Sum(y, x));
                        result = Math.max(result, getBlock2Sum(y, x));
                        result = Math.max(result, getBlock3Sum(y, x));
                        result = Math.max(result, getBlock4Sum(y, x));
                    }
                    if(is3x1Size(y)){
                        result = Math.max(result, getBlock5Sum(y, x));
                    }
                    if(is1x3Size(x)){
                        result = Math.max(result, getBlock6Sum(y, x));
                    }
                }
            }
            System.out.print(result);
        }

        int getBlock1Sum(int y, int x){
            return matrix[y][x] + matrix[y+1][x] + matrix[y+1][x+1];
        }

        int getBlock2Sum(int y, int x){
            return matrix[y][x+1] + matrix[y+1][x] + matrix[y+1][x+1];
        }

        int getBlock3Sum(int y, int x){
            return matrix[y][x] + matrix[y][x+1] + matrix[y+1][x+1];
        }

        int getBlock4Sum(int y, int x){
            return matrix[y][x] + matrix[y][x+1] + matrix[y+1][x];
        }

        int getBlock5Sum(int y, int x){
            return matrix[y][x] + matrix[y+1][x] + matrix[y+2][x];
        }

        int getBlock6Sum(int y, int x){
            return matrix[y][x] + matrix[y][x+1] + matrix[y][x+2];
        }

        private boolean is2x2Size(int y, int x){
            return  y + 1 <= MAX_Y_INDEX && x + 1 <= MAX_X_INDEX;
        }

        private boolean is3x1Size(int y) {
            return y + 2 <= MAX_Y_INDEX;
        }

        private boolean is1x3Size(int x) {
            return x + 2 <= MAX_X_INDEX;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int m = sc.nextInt();
        int[][] matrix = new int[n][m];
        for(int y = 0 ; y < n; y++){
            for(int x = 0; x < m; x++){
                matrix[y][x] = sc.nextInt();
            }
        }

        new Solver(matrix).solve();
    }
}
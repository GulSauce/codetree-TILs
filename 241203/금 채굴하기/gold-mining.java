import java.util.*;

public class Main {
    private static class Solver {
        int[][] matrix;
        final int GOLD_PRICE;
        final int MAX_INDEX;
        boolean[][] isVisited;

        public  Solver(
                int[][] matrix,
                int goldPrice
        ){
            this.matrix = matrix;
            this.GOLD_PRICE = goldPrice;
            MAX_INDEX = matrix[0].length - 1;
            isVisited = new boolean[MAX_INDEX+1][MAX_INDEX+1];
        }

        public  void solve(){
            int result = 0;
            for(int y = 0; y <= MAX_INDEX; y++){
                for(int x= 0; x <= MAX_INDEX; x++){
                    for(int k = 1; k <= MAX_INDEX; k++){
                        int goldCount = getGoldCount(x, y, k);
                        int kPrice = getKPrice(k);
                        if(kPrice <= goldCount*GOLD_PRICE){
                            result = Math.max(result, goldCount);
                        }
                    }
                }
            }
            System.out.print(result);
        }

        private int getGoldCount(int centerX, int centerY, int moveCount){
            for(boolean[] array: isVisited){
                Arrays.fill(array, false);
            }

            return  moveAndGetGoldCount(centerY, centerX, moveCount);
        }

        int moveAndGetGoldCount(int y, int x, int moveCount){
            if(moveCount == -1 || isOutOfIndex(y) || isOutOfIndex(x) || isVisited[y][x]){
                return 0;
            }

            isVisited[y][x] = true;
            return matrix[y][x] +
                    moveAndGetGoldCount(y-1, x, moveCount-1) +
                    moveAndGetGoldCount(y, x-1, moveCount-1) +
                    moveAndGetGoldCount(y+1, x, moveCount-1) +
                    moveAndGetGoldCount(y, x+1, moveCount-1);
        }

        private boolean isOutOfIndex(int i){
            return i < 0 || MAX_INDEX < i;
        }

        private int getKPrice(int k){
            return k*k + (k+1)*(k+1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int m = sc.nextInt();
        int[][] matrix = new int[n][n];
        for(int y = 0 ; y < n; y++){
            for(int x = 0; x < n; x++){
                matrix[y][x] = sc.nextInt();
            }
        }

        new Solver(matrix, m).solve();
    }
}
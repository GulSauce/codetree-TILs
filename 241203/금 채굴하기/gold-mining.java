import java.util.*;

public class Main {
    private static class Solver {
        int[][] goldMap;
        final int GOLD_PRICE;
        final int MAX_ARRAY_LENGTH;
        boolean[][] isVisited;

        public  Solver(
                int[][] goldMap,
                int goldPrice
        ){
            this.goldMap = goldMap;
            this.GOLD_PRICE = goldPrice;
            MAX_ARRAY_LENGTH = goldMap[0].length;
            isVisited = new boolean[MAX_ARRAY_LENGTH][MAX_ARRAY_LENGTH];
        }

        public  void solve(){
            int result = 0;
            for(int y = 0; y < MAX_ARRAY_LENGTH; y++){
                for(int x = 0; x < MAX_ARRAY_LENGTH; x++){
                    for(int k = 0; k < MAX_ARRAY_LENGTH; k++){
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
            initIsVisited();
            return moveAndGetGoldCount(centerY, centerX, moveCount);
        }

        int moveAndGetGoldCount(int y, int x, int moveCount){
            if(moveCount == -1 || isOutOfIndex(y) || isOutOfIndex(x) || isVisited[y][x]){
                return 0;
            }

            isVisited[y][x] = true;
            return goldMap[y][x] +
                    moveAndGetGoldCount(y-1, x, moveCount-1) +
                    moveAndGetGoldCount(y+1, x, moveCount-1) +
                    moveAndGetGoldCount(y, x-1, moveCount-1) +
                    moveAndGetGoldCount(y, x+1, moveCount-1);
        }

        private void initIsVisited(){
            for(boolean[] array: isVisited){
                Arrays.fill(array, false);
            }
        }

        private boolean isOutOfIndex(int i){
            return i < 0 || MAX_ARRAY_LENGTH <= i;
        }

        private int getKPrice(int k){
            return k*k + (k+1)*(k+1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int m = sc.nextInt();
        int[][] goldMap = new int[n][n];
        for(int y = 0 ; y < n; y++){
            for(int x = 0; x < n; x++){
                goldMap[y][x] = sc.nextInt();
            }
        }

        new Solver(goldMap, m).solve();
    }
}
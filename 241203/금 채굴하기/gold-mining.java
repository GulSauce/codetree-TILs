import java.util.*;

public class Main {
    private static class Solver {
        int[][] goldMap;
        boolean[][] isVisited;

        final int UNIT_GOLD_PRICE;
        final int MAX_ARRAY_LENGTH;

        public  Solver(
                int[][] goldMap,
                int unitGoldPrice
        ){
            this.goldMap = goldMap;
            this.UNIT_GOLD_PRICE = unitGoldPrice;
            MAX_ARRAY_LENGTH = goldMap[0].length;
            isVisited = new boolean[MAX_ARRAY_LENGTH][MAX_ARRAY_LENGTH];
        }

        public  void solve(){
            initIsVisited();
            int result = 0;
            for(int y = 0; y < MAX_ARRAY_LENGTH; y++){
                for(int x = 0; x < MAX_ARRAY_LENGTH; x++){
                    result = Math.max(result, getGoldPriceWithIncreasingMoving(x, y));
                }
            }
            System.out.print(result);
        }

        private int getGoldPriceWithIncreasingMoving(int centerX, int centerY){
            int result = 0;
            for(int moveCount = 0; moveCount < MAX_ARRAY_LENGTH; moveCount++){
                int goldCount = getGoldCount(centerX, centerY, moveCount);
                int movingPrice = getMovingPrice(moveCount);
                if(movingPrice <= goldCount * UNIT_GOLD_PRICE){
                    result = Math.max(result, goldCount);
                }
            }
            return result;
        }

        private int getGoldCount(int centerX, int centerY, int moveCount){
            initIsVisited();
            return moveAndGetGoldCount(centerX, centerY, moveCount);
        }

        int moveAndGetGoldCount(int x, int y, int moveCount){
            Queue<Coordinate> q = new LinkedList<>();
            isVisited[y][x] = true;
            q.add(new Coordinate(x, y));

            int goldCount = 0;

            while (!q.isEmpty()){
                Coordinate currentCoordinate = q.poll();
                int currentX = currentCoordinate.x;
                int currentY = currentCoordinate.y;
                goldCount += goldMap[currentY][currentX];

                if(moveCount <= getDistFromCenter(new Coordinate(x, y), new Coordinate(currentX, currentY))){
                    continue;
                }

                if(!isOutOfIndex(currentX + 1) && !isVisited[currentY][currentX+1]){
                    isVisited[currentY][currentX+1] = true;
                    q.add(new Coordinate(currentX+1, currentY));
                }

                if(!isOutOfIndex(currentX -1) && !isVisited[currentY][currentX-1]){
                    isVisited[currentY][currentX-1] = true;
                    q.add(new Coordinate(currentX-1, currentY));
                }

                if(!isOutOfIndex(currentY + 1) && !isVisited[currentY+1][currentX]){
                    isVisited[currentY+1][currentX] = true;
                    q.add(new Coordinate(currentX, currentY+1));
                }

                if(!isOutOfIndex(currentY - 1) && !isVisited[currentY-1][currentX]){
                    isVisited[currentY-1][currentX] = true;
                    q.add(new Coordinate(currentX, currentY-1));
                }
            }

            return goldCount;
        }

        int getDistFromCenter(Coordinate center, Coordinate current){
            return Math.abs(center.x - current.x) + Math.abs(center.y - current.y);
        }

        private void initIsVisited(){
            for(boolean[] array: isVisited){
                Arrays.fill(array, false);
            }
        }

        private boolean isOutOfIndex(int i){
            return i < 0 || MAX_ARRAY_LENGTH <= i;
        }

        private int getMovingPrice(int k){
            return k * k + (k+1) * (k+1);
        }

        private static class Coordinate{
            int x;
            int y;

            public  Coordinate(
                    int x,
                    int y
            ){
                this.x = x;
                this.y = y;
            }
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
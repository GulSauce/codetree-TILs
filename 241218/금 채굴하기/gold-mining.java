import java.util.*;

public class Main {
    private static class Solver {
        int[][] goldGrid;
        boolean[][] isVisited;

        final int UNIT_GOLD_PRICE;
        final int MAX_GRID_INDEX;

        public  Solver(
                int[][] goldGrid,
                int unitGoldPrice
        ){
            this.goldGrid = goldGrid;
            this.UNIT_GOLD_PRICE = unitGoldPrice;
            MAX_GRID_INDEX = goldGrid[0].length-1;
            isVisited = new boolean[MAX_GRID_INDEX][MAX_GRID_INDEX];
        }

        public  void solve(){
            int result = 0;
            for(int y = 0; y < MAX_GRID_INDEX; y++){
                for(int x = 0; x < MAX_GRID_INDEX; x++){
                    result = Math.max(result, getGoldPriceWithIncreasingMoveCount(x, y));
                }
            }
            System.out.print(result);
        }

        private int getGoldPriceWithIncreasingMoveCount(int centerX, int centerY){
            int result = 0;
            for(int moveCount = 0; moveCount <= MAX_GRID_INDEX; moveCount++){
                int goldCount = getGoldCount(centerX, centerY, moveCount);
                int movingPrice = getMovingPrice(moveCount);
                if(movingPrice <= goldCount * UNIT_GOLD_PRICE){
                    result = Math.max(result, goldCount);
                }
            }
            return result;
        }

        private int getGoldCount(int centerX, int centerY, int moveCount){
            int goldCount = 0;
            for(int y = centerY-moveCount; y <= centerY+moveCount; y++){
                for(int x = centerX-moveCount; x <= centerX+moveCount; x++) {
                    if(isOutOfGrid(new Coordinate(x, y))){
                        continue;
                    }
                    if(isInMoveCount(new Coordinate(x, y), new Coordinate(centerX, centerY), moveCount)){
                        goldCount += goldGrid[y][x];
                    }
                }
            }
            return goldCount;
        }

        private boolean isOutOfGrid(Coordinate coordinate){
            return coordinate.y < 0 || MAX_GRID_INDEX < coordinate.y ||  coordinate.x< 0 || MAX_GRID_INDEX < coordinate.x;
        }

        private boolean isInMoveCount(Coordinate coordinate, Coordinate centerCoordinate, int dist){
            int menhattanDist = Math.abs(coordinate.x - centerCoordinate.x) + Math.abs(coordinate.y - centerCoordinate.y);
            return menhattanDist <= dist;
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
import java.util.*;

public class Main {
    private static class Solver {
        int[][] grid;
        int gridLength;
        int maxPrice = 0;
        int stealColumnLength;
        int maxStealWeight;

        List<Coordinate> selectedCoordinates = new ArrayList<>();

        public Solver(
                int[][] grid,
                int M,
                int C
        ) {
            this.grid = grid;
            this.gridLength = grid.length;
            this.stealColumnLength = M;
            this.maxStealWeight = C;
        }

        public void solve(){
            for(int row = 0; row < gridLength; row++){
                for(int col = 0; col < gridLength; col++){
                    Coordinate currentCoordinate = new Coordinate(row, col);
                    selectedCoordinates.add(currentCoordinate);
                    selectCoordinate(new Coordinate(row, col));
                    selectedCoordinates.remove(selectedCoordinates.size()-1);
                }
            }
            System.out.println(maxPrice);
        }

        private void selectCoordinate(Coordinate coordinate){
            if(2 <= selectedCoordinates.size()){
                if(isOutOfRange()){
                    return;
                }
                if(isCollide()){
                    return;
                }
                maxPrice = Math.max(maxPrice, getPrice());
                return;
            }

            for(int row = coordinate.row; row < gridLength; row++){
                for(int col = 0; col < gridLength; col++){
                    Coordinate currentCoordinate = new Coordinate(row, col);
                    selectedCoordinates.add(currentCoordinate);
                    selectCoordinate(new Coordinate(row, col));
                    selectedCoordinates.remove(selectedCoordinates.size()-1);
                }
            }
        }

        private int getPrice(){
            Coordinate first = selectedCoordinates.get(0);
            Coordinate second = selectedCoordinates.get(1);

            int price = 0;
            price += getMaxPriceFrom(new Coordinate(first.row, first.col));
            price += getMaxPriceFrom(new Coordinate(second.row, second.col));

            return price;
        }

        private int getMaxPriceFrom(Coordinate start){
            int[][] dp = new int[stealColumnLength][maxStealWeight+1];

            // 초기화
            int firstWeight = grid[start.row][start.col];
            for(int weight = firstWeight; weight <= maxStealWeight; weight++) {
                dp[0][weight] = firstWeight * firstWeight;
            }

            // 처리
            for(int i = 1; i < stealColumnLength; i++){
                for(int weight = 0; weight <= maxStealWeight; weight++) {
                    int currentWeight = grid[start.row][i + start.col];
                    if(weight < currentWeight){
                        dp[i][weight] = dp[i-1][weight];
                        continue;
                    }
                    dp[i][weight] = Math.max(dp[i-1][weight], dp[i-1][weight-currentWeight] + currentWeight * currentWeight);
                }
            }

            int maxPrice = 0;
            for(int i = 0; i < stealColumnLength; i++){
                for(int weight = 0; weight <= maxStealWeight; weight++) {
                    maxPrice = Math.max(maxPrice, dp[i][weight]);
                }
            }
            return maxPrice;
        }

        private boolean isOutOfRange(){
            Coordinate first = selectedCoordinates.get(0);
            Coordinate second = selectedCoordinates.get(1);

            return gridLength <= first.col + stealColumnLength - 1 || gridLength <= second.col + stealColumnLength - 1;
        }

        private boolean isCollide(){
            int[][] checkGrid = new int[gridLength][gridLength];
            for(Coordinate coordinate: selectedCoordinates) {
                for (int col = coordinate.col; col < coordinate.col + stealColumnLength; col++) {
                    if(checkGrid[coordinate.row][col] == 1){
                        return true;
                    }
                    else {
                        checkGrid[coordinate.row][col]++;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int C = sc.nextInt();

        int[][] grid = new int[N][N];

        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                grid[r][c] = sc.nextInt();
            }
        }

        new Solver(grid, M, C).solve();
    }

    private static class Coordinate{
        int row;
        int col;

        public Coordinate(
                int row,
                int col
        ){
            this.row = row;
            this.col = col;
        }
    }
}
import java.util.*;

public class Main {
    private static class Solver{
        List<String> grid;

        Coordinate startCoordinate;
        Coordinate endCoordinate;

        int maxGridIndex;
        int prevCoinCount = 0;
        int prevMoveCount = 0;

        int answer = Integer.MAX_VALUE;

        int[] dRow = {0, -1 , 0, 1};
        int[] dCol = {1, 0, -1, 0};

        boolean[][][] isVisited;

        public Solver(
                List<String> grid
        ){
            this.grid = grid;
            this.maxGridIndex = grid.size()-1;
            this.startCoordinate = getStartCoordinate();
            this.endCoordinate = getEndCoordinate();
            this.isVisited = new boolean[maxGridIndex+1][maxGridIndex+1][4];
        }

        public void solve() {
            initIsVisited();
            getCombination(startCoordinate, 0, 0);
            printResult();
        }

        private void  printResult(){
            if(answer == Integer.MAX_VALUE){
                System.out.println(-1);
            }else{
                System.out.println(answer);
            }
        }

        private void initIsVisited(){
            for(boolean[][] arrays: isVisited){
                for(boolean[] array: arrays){
                    Arrays.fill(array, false);
                }
            }
        }

        private void getCombination(Coordinate prevCoordinate, int prevCoinValue, int prevCoinCount){
            if(prevCoordinate.row == endCoordinate.row && prevCoordinate.col == endCoordinate.col){
                if(3 <= prevCoinCount){
                    answer = Math.min(answer, prevMoveCount);
                }
                return;
            }

            for(int i = 0; i < 4; i++){
                int curRow = prevCoordinate.row + dRow[i];
                int curCol = prevCoordinate.col + dCol[i];

                Coordinate curCoordinate = new Coordinate(curRow, curCol);
                if(isOutOfGrid(curCoordinate)){
                    continue;
                }
                if(isVisited[curRow][curCol][i]){
                    continue;
                }

                int curCoinValue = prevCoinValue;
                int curCoinCount = prevCoinCount;
                if(isCoinAt(curCoordinate)) {
                    int coinValue = grid.get(curRow).charAt(curCol) - '0';
                    if (coinValue <= prevCoinValue) {
                        continue;
                    }
                    curCoinCount++;
                    curCoinValue = coinValue;
                }


                isVisited[curRow][curCol][i] = true;
                prevMoveCount++;

                getCombination(curCoordinate, curCoinValue, curCoinCount);

                isVisited[curRow][curCol][i] = false;
                prevMoveCount--;
            }
        }

        private boolean isCoinAt(Coordinate coordinate){
            return '0' <= grid.get(coordinate.row).charAt(coordinate.col) && grid.get(coordinate.row).charAt(coordinate.col) <= '9';
        }

        private boolean isOutOfGrid(Coordinate coordinate){
            return coordinate.row < 0 || maxGridIndex < coordinate.row || coordinate.col < 0 || maxGridIndex < coordinate.col;
        }

        private Coordinate getStartCoordinate(){
            for(int row = 0; row < grid.size(); row++){
                String str = grid.get(row);
                for(int col = 0; col < str.length(); col++){
                    if(str.charAt(col) == 'S'){
                        return new Coordinate(row, col);
                    }
                }
            }
            return new Coordinate(-1, -1);
        }

        private Coordinate getEndCoordinate(){
            for(int row = 0; row < grid.size(); row++){
                String str = grid.get(row);
                for(int col = 0; col < str.length(); col++){
                    if(str.charAt(col) == 'E'){
                        return new Coordinate(row, col);
                    }
                }
            }
            return new Coordinate(-1, -1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        List<String> grid = new ArrayList<>();
        for(int i = 0; i < N; i++){
            String str = sc.next();
            grid.add(str);
        }

        new Solver(grid).solve();
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
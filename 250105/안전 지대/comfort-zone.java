import java.util.*;

public class Main {
    private static class Solver{
        int maxRow;
        int maxCol;
        int maxK;
        int K = 1;

        int safeAreaCount = 0;
        int maxSafeAreaCount = 0;
        int maxSinkHeight = 0;

        boolean[][] isVisited;
        int[][] town;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        public Solver(
                int[][] town
        ){
            this.town = town;
            this.maxRow = town.length-1;
            this.maxCol = town[0].length-1;
            this.maxK = getMaxK();
            this.isVisited = new boolean[town.length][town[0].length];
        }

        public void solve(){
            for(int sinkLevel = 1; sinkLevel <= maxK; sinkLevel++){
                K = sinkLevel;
                initIsVisited();
                dfsEach();
            }
            System.out.printf("%d %d", maxSinkHeight, maxSafeAreaCount);
        }

        private void initIsVisited(){
            for(boolean[] array: isVisited){
                Arrays.fill(array, false);
            }
        }

        private void dfsEach(){
            safeAreaCount = 0;
            for(int row = 0; row <= maxRow; row++){
                for(int col = 0; col <= maxCol; col++){
                    Coordinate curCoordinate = new Coordinate(row, col);
                    if(isOutOfRange(curCoordinate)){
                        continue;
                    }
                    if(isSinkArea(curCoordinate)){
                        continue;
                    }
                    if(isAlreadyVisited(curCoordinate)){
                        continue;
                    }
                    safeAreaCount++;
                    isVisited[row][col] = true;
                    dfs(curCoordinate);
                }
            }
            if(maxSafeAreaCount < safeAreaCount){
                maxSafeAreaCount = safeAreaCount;
                maxSinkHeight = K;
            }
        }

        private void dfs(Coordinate prevCoordinate){
            for(int i = 0; i < 4; i++){
                Coordinate curCoordinate = new Coordinate(prevCoordinate.row + dRow[i], prevCoordinate.col+dCol[i]);
                if(isOutOfRange(curCoordinate)){
                    continue;
                }
                if(isSinkArea(curCoordinate)){
                    continue;
                }
                if(isAlreadyVisited(curCoordinate)){
                    continue;
                }
                isVisited[curCoordinate.row][curCoordinate.col] = true;
                dfs(curCoordinate);
            }
        }

        private boolean isAlreadyVisited(Coordinate coordinate){
            return isVisited[coordinate.row][coordinate.col];
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 0 || maxRow < coordinate.row || coordinate.col < 0 || maxCol < coordinate.col;
        }

        private boolean isSinkArea(Coordinate coordinate){
            return town[coordinate.row][coordinate.col] - K <= 0;
        }

        private int getMaxK(){
            int maxK = 0;
            for(int row = 0; row <= maxRow; row++){
                for(int col = 0; col <= maxCol; col++){
                    maxK = Math.max(maxK, town[row][col]);
                }
            }
            return maxK;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] town = new int[N][M];
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                town[row][col] = sc.nextInt();
            }
        }

        new Solver(town).solve();
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

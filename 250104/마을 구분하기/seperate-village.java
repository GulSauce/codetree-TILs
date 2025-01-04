import java.util.*;

public class Main {
    private static class Solver{
        List<Integer> civilCounts = new ArrayList<>();
        int maxTownIndex;
        int civilCount = 0;

        final int WALL_NUMBER = 0;

        int[] dRow = {0, 1, 0, -1};
        int[] dCol = {1, 0, -1, 0};

        int[][] town;

        boolean[][] isVisited;

        public Solver(
                int[][] town
        ){
            this.town = town;
            this.maxTownIndex = town.length-1;
            this.isVisited = new boolean[town.length][town.length];
        }

        public void solve(){
            initIsVisited();
            dfsEach();
            printResult();
        }

        private void printResult(){
            System.out.println(civilCounts.size());
            Collections.sort(civilCounts);
            for(int count: civilCounts){
                System.out.println(count);
            }
        }

        private void dfsEach(){
            for(int row = 0; row <= maxTownIndex; row++){
                for(int col = 0; col <= maxTownIndex; col++){
                    if(isVisited[row][col]){
                        continue;
                    }
                    civilCount = 0;
                    dfs(row, col);
                    if(civilCount == 0) {
                        continue;
                    }
                    civilCounts.add(civilCount);
                }
            }
        }

        private void initIsVisited(){
            for(boolean[] array: isVisited){
                Arrays.fill(array, false);
            }
        }

        private void dfs(int prevRow, int prevCol){
            for(int i = 0; i < 4; i++){
                int curRow = prevRow + dRow[i];
                int curCol = prevCol + dCol[i];
                if(isOutOfTown(curRow, curCol)){
                    continue;
                }
                if(isWall(curRow, curCol)){
                    continue;
                }
                if(isAlreadyVisited(curRow, curCol)){
                    continue;
                }
                civilCount++;
                isVisited[curRow][curCol] = true;
                dfs(curRow, curCol);
            }
        }

        private boolean isOutOfTown(int row, int col){
            return row < 0 || maxTownIndex < row || col < 0 || maxTownIndex < col;
        }

        private boolean isWall(int row, int col){
            return town[row][col] == WALL_NUMBER;
        }

        private boolean isAlreadyVisited(int row, int col){
            return isVisited[row][col];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] town = new int[n][n];
        for(int row =0; row < n; row++){
            for(int col = 0; col < n; col++){
                town[row][col] = sc.nextInt();
            }
        }

        new Solver(town).solve();
    }
}
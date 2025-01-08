import java.util.*;

public class Main {
    private static class Solver{
        int gridIndex;

        List<Element> elements = new ArrayList<>();

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        int[][] dp;
        int[][] grid;

        public Solver(
                int n,
                int[][] grid
        ){
            this.gridIndex = n-1;
            this.grid = grid;
            this.dp = new int[n][n];
        }

        public void solve(){
            getElement();
            Collections.sort(elements);
            initDP();
            calcDP();
            printAnswer();
        }

        private void initDP(){
            for(int[] array: dp){
                Arrays.fill(array, 1);
            }
        }

        private void printAnswer(){
            int answer = 0;
            for(int row = 0; row <= gridIndex; row++){
                for(int col = 0; col <= gridIndex; col++){
                    answer = Math.max(answer, dp[row][col]);
                }
            }

            System.out.println(answer);
        }

        private void calcDP(){
            for(Element element: elements){
                for(int i = 0; i < 4; i++){
                    int nearRow = element.row + dRow[i];
                    int nearCol = element.row + dCol[i];
                    if(isOutOfGrid(nearRow, nearCol)){
                        continue;
                    }
                    if(grid[nearRow][nearCol] <= grid[element.row][element.col]){
                        continue;
                    }
                    dp[element.row][element.col] = Math.max(dp[nearRow][nearCol] + 1, dp[element.row][element.col]);
                }
            }
        }

        private boolean isOutOfGrid(int row, int col){
            return  row < 0 || gridIndex < row || col < 0 || gridIndex < col;
        }

        private void getElement(){
            for(int row = 0; row <= gridIndex; row++){
                for(int col = 0; col <= gridIndex; col++){
                    elements.add(new Element(grid[row][col], row, col));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for(int row = 0; row < n; row++){
            for(int col = 0; col < n; col++){
                grid[row][col] = sc.nextInt();
            }
        }
        new Solver(n, grid).solve();
    }

    private static class Element implements Comparable<Element>{
        int value;
        int row;
        int col;

        public Element(
                int value,
                int row,
                int col
        ){
            this.value = value;
            this.row = row;
            this.col = col;
        }

        @Override public int compareTo(Element element){
            return element.value - this.value;
        }
    }
}
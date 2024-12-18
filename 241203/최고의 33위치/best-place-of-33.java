import java.util.*;

public class Main {
    private static class Solver {
        int[][] grid;
        int gridLength;
        final int NEED_TO_CONTINUE;

        public Solver(
                int m,
                int[][] grid
        ){
            this.grid = grid;
            NEED_TO_CONTINUE = m;
            gridLength = grid.length;
        }

        public void solve(){
            int result = 0;
            for(int y = 0; y < gridLength; y++){
                int[] currentSequence = grid[y];
                if(isHappySequence(currentSequence)) {
                    result++;
                }
            }

            for(int x = 0; x < gridLength; x++){
                int[] currentSequence = getCurrentSequence(x);
                if(isHappySequence(currentSequence)) {
                    result++;
                }
            }

            System.out.print(result);
        }

        private boolean isHappySequence(int[] sequence){
            int prevNumber = sequence[0];
            int maxContinueCount = 0;
            int currentContinueCount = 1;
            for(int number: sequence){
                if(number == prevNumber){
                    currentContinueCount++;
                    maxContinueCount = Math.max(maxContinueCount, currentContinueCount);
                    continue;
                }
                maxContinueCount = Math.max(maxContinueCount, currentContinueCount);
                currentContinueCount = 1;
                prevNumber = number;
            }
            return NEED_TO_CONTINUE <= maxContinueCount;
        }

        private int[] getCurrentSequence(int x){
            int[] sequence = new int[gridLength];
            for(int y = 0; y < gridLength; y++) {
                sequence[y] = grid[y][x];
            }
            return sequence;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int m = sc.nextInt();
        int[][] grid = new int[n][n];
        for(int y = 0; y < n; y++){
            for(int x = 0; x < n; x++){
                int value = sc.nextInt();
                grid[y][x] = value;
            }
        }

        new Solver(m, grid).solve();
    }
}
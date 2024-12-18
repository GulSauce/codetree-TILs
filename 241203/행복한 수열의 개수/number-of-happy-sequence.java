import java.util.*;

public class Main {
    private static class Solver {
        int[][] grid;
        final int gridLength;
        final int requiredStreakLength;

        public Solver(
                int m,
                int[][] grid
        ){
            this.grid = grid;
            requiredStreakLength = m;
            gridLength = grid.length;
        }

        public void solve(){
            int result = 0;
            for(int y = 0; y < gridLength; y++)
                if(isHappySequence(grid[y])) result++;

            for(int x = 0; x < gridLength; x++)
                if(isHappySequence(getColumnSequence(x))) result++;

            System.out.print(result);
        }

        private boolean isHappySequence(int[] sequence){
            // 초기화
            int prevNumber = sequence[0];
            int maxStreakLength = 1;
            int currentStreakLength = 1;

            // 처리
            for(int i = 1; i < sequence.length; i++){
                int curNumber = sequence[i];
                if(prevNumber == curNumber){
                    currentStreakLength++;
                }else{
                    maxStreakLength = Math.max(maxStreakLength, currentStreakLength);
                    currentStreakLength = 1;
                }
                prevNumber = curNumber;
            }

            // 종료
            maxStreakLength = Math.max(maxStreakLength, currentStreakLength);

            return requiredStreakLength <= maxStreakLength;
        }

        private int[] getColumnSequence(int x){
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
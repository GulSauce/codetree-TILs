import java.util.*;

public class Main {
    private static class Solver {
        int[][] matrix;
        final int MAX_LENGTH;
        final int NEED_TO_CONTINUE;

        public Solver(
                int m,
                int[][] matrix
        ){
            this.matrix = matrix;
            NEED_TO_CONTINUE = m;
            MAX_LENGTH = matrix.length;
        }

        public void solve(){
            int result = 0;
            for(int y = 0; y < MAX_LENGTH; y++){
                int[] currentSequence = matrix[y];
                if(isHappySequence(currentSequence)) {
                    result++;
                }
            }

            for(int x = 0; x < MAX_LENGTH; x++){
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
            int currentContinueCount = 0;
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
            int[] sequence = new int[MAX_LENGTH];
            for(int y = 0; y < MAX_LENGTH; y++) {
                sequence[y] = matrix[y][x];
            }
            return sequence;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int m = sc.nextInt();
        int[][] matrix = new int[n][n];
        for(int y = 0; y < n; y++){
            for(int x = 0; x < n; x++){
                int value = sc.nextInt();
                matrix[y][x] = value;
            }
        }

        new Solver(m, matrix).solve();
    }
}
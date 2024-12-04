import java.util.*;

public class Main {
    private static class Solver {
        int[][] matrix;
        boolean[][] isVisited;

        final int MAX_ARRAY_LENGTH;
        final int MAX_ARRAY_INDEX;

        public  Solver(
                int[][] matrix
        ){
            this.matrix = matrix;
            MAX_ARRAY_LENGTH = matrix[0].length;
            MAX_ARRAY_INDEX = MAX_ARRAY_LENGTH - 1;
            isVisited = new boolean[MAX_ARRAY_LENGTH][MAX_ARRAY_LENGTH];
        }

        public  void solve(){
            int result = 0;
            for(int y = 0; y <= MAX_ARRAY_INDEX; y++){
                for(int x = 0; x <= MAX_ARRAY_INDEX; x++){
                    result = Math.max(result, getSum(y, x));
                }
            }
            System.out.print(result);
        }

        private int getSum(int startY, int startX){
            int result = 0;
            for(int upBelowMove = 1; upBelowMove <= MAX_ARRAY_INDEX; upBelowMove++){
                for(int leftRightMove = 1; leftRightMove <= MAX_ARRAY_INDEX; leftRightMove++){
                    result = Math.max(result, getSumWithMoving(startY, startX, upBelowMove, leftRightMove));
                }
            }
            return result;
        }

        private int getSumWithMoving(int startY, int startX, int upBelowMove, int leftRightMove){
            Coordinate diagonal1 = new Coordinate(startX, startY);
            if(isOutOfRage(diagonal1)){
                return 0;
            }
            Coordinate diagonal2 = new Coordinate(diagonal1.x+upBelowMove, diagonal1.y-upBelowMove);
            if(isOutOfRage(diagonal2)){
                return 0;
            }
            Coordinate diagonal3 = new Coordinate(diagonal2.x-leftRightMove, diagonal2.y-leftRightMove);
            if(isOutOfRage(diagonal3)){
                return 0;
            }
            Coordinate diagonal4 = new Coordinate(diagonal3.x-upBelowMove, diagonal3.y+upBelowMove);
            if(isOutOfRage(diagonal4)){
                return 0;
            }

            int result = 0;
            for(int moveCount = 0; moveCount < upBelowMove; moveCount++){
                result += matrix[diagonal1.y-moveCount][diagonal1.x+moveCount];
            }
            for(int moveCount = 0; moveCount < leftRightMove; moveCount++){
                result += matrix[diagonal2.y-moveCount][diagonal2.x-moveCount];
            }
            for(int moveCount = 0; moveCount < upBelowMove; moveCount++){
                result += matrix[diagonal3.y+moveCount][diagonal3.x-moveCount];
            }
            for(int moveCount = 0; moveCount < leftRightMove; moveCount++){
                result += matrix[diagonal4.y+moveCount][diagonal4.x+moveCount];
            }

            return result;
        }

        private boolean isOutOfRage(Coordinate coordinate){
            return !(0 <= coordinate.x && coordinate.x <= MAX_ARRAY_INDEX
                    && 0 <= coordinate.y && coordinate.y <= MAX_ARRAY_INDEX);
        }

        private static class Coordinate{
            int x;
            int y;

            public Coordinate(
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
        int[][] matrix = new int[n][n];
        for(int y = 0 ; y < n; y++){
            for(int x = 0; x < n; x++){
                matrix[y][x] = sc.nextInt();
            }
        }

        new Solver(matrix).solve();
    }
}
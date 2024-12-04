import java.util.*;

public class Main {
    private static class Solver {
        int[][] matrix;

        final int MAX_Y_INDEX;
        final int MAX_X_INDEX;

        public  Solver(
                int[][] matrix
        ){
            this.matrix = matrix;
            MAX_Y_INDEX = matrix.length-1;
            MAX_X_INDEX = matrix[0].length-1;
        }

        public  void solve(){
            int result = -1;
            for(int y1 = 0; y1 <= MAX_Y_INDEX; y1++){
                for(int x1 = 0; x1 <= MAX_X_INDEX; x1++){
                    for(int y2 = y1; y2 <= MAX_Y_INDEX; y2++){
                        for(int x2 = x1; x2 <= MAX_X_INDEX; x2++) {
                            Coordinate leftUpperCoordinate = new Coordinate(x1, y1);
                            Coordinate rightLowerCoordinate = new Coordinate(x2, y2);
                            if(isNegativeNumberExists(leftUpperCoordinate, rightLowerCoordinate)){
                                continue;
                            }
                            result = Math.max(result, getSquareArea(leftUpperCoordinate, rightLowerCoordinate));
                        }
                    }
                }
            }
            System.out.print(result);
        }

        private  boolean isNegativeNumberExists(Coordinate leftUpperCoordinate, Coordinate rightLowerCoordinate){
            for(int y = leftUpperCoordinate.y; y <= rightLowerCoordinate.y; y++) {
                for(int x = leftUpperCoordinate.x; x <= rightLowerCoordinate.x; x++){
                    if(matrix[y][x] < 0){
                        return true;
                    }
                }
            }
            return false;
        }

        private  int getSquareArea(Coordinate leftUpperCoordinate, Coordinate rightLowerCoordinate){
            return (rightLowerCoordinate.y-leftUpperCoordinate.y+1) * (rightLowerCoordinate.x-leftUpperCoordinate.x+1);
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
        final int m = sc.nextInt();
        int[][] matrix = new int[n][m];
        for(int y = 0 ; y < n; y++){
            for(int x = 0; x < m; x++){
                matrix[y][x] = sc.nextInt();
            }
        }

        new Solver(matrix).solve();
    }
}
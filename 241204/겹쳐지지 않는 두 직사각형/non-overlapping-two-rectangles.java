import java.util.*;

public class Main {
    private static class Solver {
        int[][] matrix;
        boolean[][] isVisited;

        final int MAX_Y_INDEX;
        final int MAX_X_INDEX;

        public  Solver(
                int[][] matrix
        ){
            this.matrix = matrix;
            MAX_Y_INDEX = matrix.length-1;
            MAX_X_INDEX = matrix[0].length-1;
            isVisited = new boolean[MAX_Y_INDEX+1][MAX_X_INDEX+1];
        }

        public  void solve(){
            int result = Integer.MIN_VALUE;
            for(int y1 = 0; y1 <= MAX_Y_INDEX; y1++){
                for(int x1 = 0; x1 <= MAX_X_INDEX; x1++){
                    for(int y2 = y1; y2 <= MAX_Y_INDEX; y2++){
                        for(int x2 = x1; x2 <= MAX_X_INDEX; x2++) {
                            result = Math.max(result, getSumPassingSquare1Coordinate(new Coordinate(x1, y1), new Coordinate(x2, y2)));
                        }
                    }
                }
            }
            System.out.print(result);
        }

        private int getSumPassingSquare1Coordinate(Coordinate square1Start, Coordinate square1End){
            int result = Integer.MIN_VALUE;

            int square1Area = getSquareSum(square1Start, square1End);

            for(int y1 = 0; y1 <= MAX_Y_INDEX; y1++){
                for(int x1 = 0; x1 <= MAX_X_INDEX; x1++){
                    for(int y2 = y1; y2 <= MAX_Y_INDEX; y2++){
                        for(int x2 = x1; x2 <= MAX_X_INDEX; x2++) {
                            Coordinate square2Start = new Coordinate(x1,y1);
                            Coordinate square2End = new Coordinate(x2, y2);
                            if(isCollide(square1Start, square1End, square2Start, square2End)){
                                continue;
                            }
                            int square2Area = getSquareSum(square2Start, square2End);
                            result = Math.max(result, square1Area+square2Area);
                        }
                    }
                }
            }

            return result;
        }

        private boolean isCollide(Coordinate square1Start, Coordinate square1End, Coordinate square2Start, Coordinate square2End){
            for(boolean[] array: isVisited){
                Arrays.fill(array, false);
            }
            for(int y = square1Start.y; y <= square1End.y; y++){
                for(int x = square1Start.x; x <= square1End.x; x++){
                    isVisited[y][x] = true;
                }
            }
            for(int y = square2Start.y; y <= square2End.y; y++){
                for(int x = square2Start.x; x <= square2End.x; x++){
                    if(isVisited[y][x]){
                        return true;
                    }
                }
            }
            return false;
        }

        private int getSquareSum(Coordinate squareStart, Coordinate squareEnd){
            int sum = 0;

            for(int y = squareStart.y; y <= squareEnd.y; y++){
                for(int x = squareStart.x; x <= squareEnd.x; x++){
                    sum += matrix[y][x];
                }
            }

            return sum;
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
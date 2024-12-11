import java.util.*;

public class Main {
    private static class Solver{
        int[][] matrix;
        final int TIME_LIMIT;
        Coordinate startCoordinate;

        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};

        public Solver(
                int[][] matrix,
                int r,
                Coordinate startCoordinate
        ){
            this.matrix = matrix;
            this.TIME_LIMIT = r;
            this.startCoordinate = startCoordinate;
        }

        public void solve(){
            explode();
            printResult();
        }

        private void printResult(){
            int result = 0;
            for(int r = 1; r < matrix.length; r++){
                for(int c = 1; c < matrix.length; c++){
                    result += matrix[r][c];
                }
            }

            System.out.print(result);
        }

        private void explode() {
            matrix[startCoordinate.r][startCoordinate.c] = 1;

            ArrayList<Coordinate> coordinates = new ArrayList<>();
            coordinates.add(startCoordinate);

            for(int time = 1; time <= TIME_LIMIT; time++) {
                ArrayList<Coordinate> temp = new ArrayList<>();
                for (Coordinate coordinate : coordinates) {
                    for (int i = 0; i < 4; i++) {
                        int multiplier = powerOf2(time - 1);
                        int nextR = coordinate.r + dy[i] * multiplier;
                        int nextC = coordinate.c + dx[i] * multiplier;
                        if(isOutOfRange(new Coordinate(nextR, nextC))){
                            continue;
                        }
                        if (matrix[nextR][nextC] == 1) {
                            continue;
                        }
                        temp.add(new Coordinate(nextR, nextC));
                        matrix[nextR][nextC] = 1;
                    }
                }
                coordinates.addAll(temp);
            }
        }

        private int powerOf2(int p){
            int value = 1;
            for(int i = 0; i < p; i++){
                value *= 2;
            }
            return value;
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return !((1<=coordinate.r && coordinate.r <= matrix.length-1)&& (1<=coordinate.c && coordinate.c <= matrix.length-1));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] matrix = new int[n+1][n+1];
        for(int[] array: matrix){
            Arrays.fill(array, 0);
        }

        new Solver(matrix, m, new Coordinate(r, c)).solve();
    }

    private static class Coordinate{
        int r;
        int c;

        public Coordinate(
                int r,
                int c
        ){
            this.r = r;
            this.c = c;
        }
    }
}
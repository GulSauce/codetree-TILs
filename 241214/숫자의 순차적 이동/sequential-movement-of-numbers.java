import java.util.*;

public class Main {
    private static class Solver{
        int[][] matrix;
        int turn;

        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

        final int MAX_INDEX;

        public Solver(
                int[][] matrix,
                int m
        ){
            this.matrix = matrix;
            this.turn = m;
            this.MAX_INDEX = matrix.length-1;
        }

        public void solve(){
            for(int i = 0; i < turn; i++) {
                for (int targetNumber = 1; targetNumber <= matrix.length * matrix.length; targetNumber++) {
                    changeNearGreatestNumber(targetNumber);
                }
            }
            printResult();
        }

        private void printResult(){
            for(int y = 0; y <= MAX_INDEX; y++){
                for(int x = 0; x <= MAX_INDEX; x++){
                    System.out.printf("%d ", matrix[y][x]);
                }
                System.out.println();
            }
        }

        private void changeNearGreatestNumber(int targetNumber){
            Coordinate targetCoordinate = getTargetCoordinate(targetNumber);
            Coordinate nearGreatestCoordinate = new Coordinate(-1, -1);
            for(int i = 0; i < 8; i++){
                Coordinate nearCoordinate = new Coordinate(targetCoordinate.y + dy[i], targetCoordinate.x + dx[i]);
                if(isOutOfRange(nearCoordinate)){
                    continue;
                }
                if(nearGreatestCoordinate.y == -1 && nearGreatestCoordinate.x == -1){
                    nearGreatestCoordinate = nearCoordinate;
                    continue;
                }
                if(matrix[nearGreatestCoordinate.y][nearGreatestCoordinate.x] < matrix[nearCoordinate.y][nearCoordinate.x]){
                    nearGreatestCoordinate = nearCoordinate;
                }
            }
            matrix[targetCoordinate.y][targetCoordinate.x] = matrix[nearGreatestCoordinate.y][nearGreatestCoordinate.x];
            matrix[nearGreatestCoordinate.y][nearGreatestCoordinate.x] = targetNumber;
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.y < 0 || MAX_INDEX < coordinate.y || coordinate.x < 0 || MAX_INDEX < coordinate.x;
        }

        private Coordinate getTargetCoordinate(int targetNumber){
            Coordinate targetCoordinate = new Coordinate(-1, -1);
            for(int y = 0; y <= MAX_INDEX; y++){
                if(targetCoordinate.y != -1 && targetCoordinate.x != -1){
                    break;
                }
                for(int x = 0; x <= MAX_INDEX; x++){
                    if(matrix[y][x] != targetNumber){
                        continue;
                    }
                    targetCoordinate = new Coordinate(y, x);
                    break;
                }
            }

            return targetCoordinate;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = sc.nextInt();
            }
        }

        new Solver(matrix, m).solve();
    }

    private static  class  Coordinate{
        int y;
        int x;
        public  Coordinate(
                int y,
                int x
        ){
            this.y = y;
            this.x = x;
        }
    }
}
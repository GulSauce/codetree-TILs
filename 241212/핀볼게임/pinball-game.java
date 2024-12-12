import java.util.*;

public class Main {
    private static class Solver{
        int[][] matrix;
        final int RIGHT_UP_WALL_NUMBER = 1;
        final int RIGHT_DOWN_WALL_NUMBER = 2;
        final int MAX_INDEX;

        int dy[] = {0, -1, 0, 1};
        int dx[] = {1, 0, -1, 0};

        public Solver(
                int[][] matrix
        ){
            this.matrix = matrix;
            MAX_INDEX = matrix.length-1;
        }

        public void solve(){
            int elapsedTime = 0;
            elapsedTime = Math.max(elapsedTime, getLeftSideMaxElapsedTIme());
            elapsedTime = Math.max(elapsedTime, getRightSideMaxElapsedTIme());
            elapsedTime = Math.max(elapsedTime, getUpSideMaxElapsedTIme());
            elapsedTime = Math.max(elapsedTime, getDownSideMaxElapsedTIme());
            System.out.print(elapsedTime);
        }

        private int getLeftSideMaxElapsedTIme(){
            int elapsedTime = 0;
            for(int y = 0; y <= MAX_INDEX; y++){
                BallStatus ballStatus = new BallStatus(new Coordinate(y, 0), 0);
                elapsedTime = Math.max(elapsedTime, getElapsedTimeEscapeBall(ballStatus));
            }
            return elapsedTime;
        }

        private int getUpSideMaxElapsedTIme(){
            int elapsedTime = 0;
            for(int x = 0; x <= MAX_INDEX; x++){
                BallStatus ballStatus = new BallStatus(new Coordinate(0, x), 3);
                elapsedTime = Math.max(elapsedTime, getElapsedTimeEscapeBall(ballStatus));
            }
            return elapsedTime;
        }

        private int getRightSideMaxElapsedTIme(){
            int elapsedTime = 0;
            for(int y = 0; y <= MAX_INDEX; y++){
                BallStatus ballStatus = new BallStatus(new Coordinate(y, MAX_INDEX), 2);
                elapsedTime = Math.max(elapsedTime, getElapsedTimeEscapeBall(ballStatus));
            }
            return elapsedTime;
        }

        private int getDownSideMaxElapsedTIme(){
            int elapsedTime = 0;
            for(int x = 0; x <= MAX_INDEX; x++){
                BallStatus ballStatus = new BallStatus(new Coordinate(MAX_INDEX, x), 1);
                elapsedTime = Math.max(elapsedTime, getElapsedTimeEscapeBall(ballStatus));
            }
            return elapsedTime;
        }

        private int getElapsedTimeEscapeBall(BallStatus initBallStatus){
            int elapsedTime = 1;
            BallStatus ballStatus = initBallStatus;
            while(!isOutOfIndex(ballStatus.coordinate)){
                elapsedTime++;
                int currentY = ballStatus.coordinate.y;
                int currentX = ballStatus.coordinate.x;
                int currentDirection = ballStatus.direction;
                int nextDirection = ballStatus.direction;
                if(matrix[currentY][currentX] == RIGHT_UP_WALL_NUMBER){
                    if(currentDirection == 0 || currentDirection == 2){
                        nextDirection = (currentDirection + 1) % 4;
                    }
                    else{
                        nextDirection = (currentDirection - 1 + 4) % 4;
                    }
                }
                if(matrix[currentY][currentX] == RIGHT_DOWN_WALL_NUMBER){
                    if(currentDirection == 1 || currentDirection == 3){
                        nextDirection = (currentDirection + 1) % 4;
                    }
                    else{
                        nextDirection = (currentDirection - 1 + 4) % 4;
                    }
                }
                Coordinate nextCoordinate = new Coordinate(currentY+dy[nextDirection], currentX+dx[nextDirection]);
                ballStatus = new BallStatus(nextCoordinate, nextDirection);
            }
            return elapsedTime;
        }

        private boolean isOutOfIndex(Coordinate coordinate){
            return !((0 <= coordinate.y && coordinate.y <= MAX_INDEX) && (0 <= coordinate.x && coordinate.x <= MAX_INDEX));
        }

        private static class BallStatus{
            Coordinate coordinate;
            int direction;

            public BallStatus(
                    Coordinate coordinate,
                    int direction
            ){
                this.coordinate = coordinate;
                this.direction = direction;
            }
        }

        private static class Coordinate{
            int y;
            int x;

            public Coordinate(
                    int y,
                    int x
            ){
                this.y = y;
                this.x = x;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        for(int y = 0; y < n; y++){
            for(int x = 0; x < n; x++){
                matrix[y][x] = sc.nextInt();
            }
        }

        new Solver(matrix).solve();
    }
}
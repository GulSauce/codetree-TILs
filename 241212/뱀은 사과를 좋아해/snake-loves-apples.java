import java.util.*;

public class Main {
    private static class Solver{
        final int APPLE_INDEX = 100;

        boolean isGameOver = false;

        int[][] matrix;

        SnakeMoveInfo[] snakeMoveInfos;

        int elapsedTime = 0;

        Snake snake;

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, 1, -1};

        public Solver(
                int[][] matrix,
                Coordinate[] appleCoordinates,
                SnakeMoveInfo[] snakeMoveInfos
        ){
            this.matrix = matrix;
            this.snakeMoveInfos = snakeMoveInfos;
            this.snake = new Snake(new Coordinate(1, 1));
            for(Coordinate appleCoordinate: appleCoordinates){
                matrix[appleCoordinate.y][appleCoordinate.x] = APPLE_INDEX;
            }
        }

        public  void solve(){
            for(SnakeMoveInfo snakeMoveInfo: snakeMoveInfos){
                moveSnake(snakeMoveInfo);
                if(isGameOver){
                    break;
                }
            }
            System.out.print(elapsedTime);
        }

        private void moveSnake(SnakeMoveInfo snakeMoveInfo){
            if(snakeMoveInfo.moveDirection.equals('U')){
                for(int i = 0; i < snakeMoveInfo.moveCount; i++){
                    elapsedTime++;
                    Coordinate headCoordinate = snake.snake.peekFirst();
                    Coordinate nextCoordinate = new Coordinate(headCoordinate.y + dy[0], headCoordinate.x + dx[0]);
                    if(isOutOfRange(nextCoordinate)){
                        isGameOver = true;
                        break;
                    }
                    if(isAppleExistAt(nextCoordinate)){
                        snake.growSnakeTo(nextCoordinate);
                    }
                    else{
                        snake.moveSnakeTo(nextCoordinate);
                    }
                    if(snake.isCollide()){
                        isGameOver = true;
                        break;
                    }
                }
            }
            if(snakeMoveInfo.moveDirection.equals('D')){
                for(int i = 0; i < snakeMoveInfo.moveCount; i++){
                    elapsedTime++;
                    Coordinate headCoordinate = snake.snake.peekFirst();
                    Coordinate nextCoordinate = new Coordinate(headCoordinate.y + dy[1], headCoordinate.x + dx[1]);
                    if(isOutOfRange(nextCoordinate)){
                        isGameOver = true;
                        break;
                    }
                    if(isAppleExistAt(nextCoordinate)){
                        snake.growSnakeTo(nextCoordinate);
                    }
                    else{
                        snake.moveSnakeTo(nextCoordinate);
                    }
                    if(snake.isCollide()){
                        isGameOver = true;
                        break;
                    }
                }
            }
            if(snakeMoveInfo.moveDirection.equals('R')){
                for(int i = 0; i < snakeMoveInfo.moveCount; i++){
                    elapsedTime++;
                    Coordinate headCoordinate = snake.snake.peekFirst();
                    Coordinate nextCoordinate = new Coordinate(headCoordinate.y + dy[2], headCoordinate.x + dx[2]);
                    if(isOutOfRange(nextCoordinate)){
                        isGameOver = true;
                        break;
                    }
                    if(isAppleExistAt(nextCoordinate)){
                        snake.growSnakeTo(nextCoordinate);
                    }
                    else{
                        snake.moveSnakeTo(nextCoordinate);
                    }
                    if(snake.isCollide()){
                        isGameOver = true;
                        break;
                    }
                }
            }
            if(snakeMoveInfo.moveDirection.equals('L')){
                for(int i = 0; i < snakeMoveInfo.moveCount; i++){
                    elapsedTime++;
                    Coordinate headCoordinate = snake.snake.peekFirst();
                    Coordinate nextCoordinate = new Coordinate(headCoordinate.y + dy[3], headCoordinate.x + dx[3]);
                    if(isOutOfRange(nextCoordinate)){
                        isGameOver = true;
                        break;
                    }
                    if(isAppleExistAt(nextCoordinate)){
                        snake.growSnakeTo(nextCoordinate);
                    }
                    else{
                        snake.moveSnakeTo(nextCoordinate);
                    }
                    if(snake.isCollide()){
                        isGameOver = true;
                        break;
                    }
                }
            }
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return !((1 <= coordinate.y && coordinate.y < matrix.length) && (1 <= coordinate.x && coordinate.x < matrix.length));
        }

        private boolean isAppleExistAt(Coordinate coordinate){
            return matrix[coordinate.y][coordinate.x] == APPLE_INDEX;
        }
    }

    private static class Snake{
        public Deque<Coordinate> snake = new ArrayDeque<>();

        public Snake(
                Coordinate headCoordinate
        ){
            snake.addFirst(headCoordinate);
        }

        public void moveSnakeTo(Coordinate coordinate){
            snake.addFirst(coordinate);
            snake.pollLast();
        }

        public void growSnakeTo(Coordinate coordinate){
            snake.addFirst(coordinate);
        }

        private boolean isCollide(){
            Coordinate headCoordinate = snake.pollFirst();
            for(Coordinate snakeCoordinate: snake){
                if(snakeCoordinate.y == headCoordinate.y && snakeCoordinate.x == headCoordinate.x){
                    snake.addFirst(headCoordinate);
                    return true;
                }
            }
            snake.addFirst(headCoordinate);
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        Coordinate[] appleCoordinates = new Coordinate[M];
        for(int i = 0; i < M; i++){
            appleCoordinates[i] = new Coordinate(sc.nextInt(), sc.nextInt());
        }
        int[][] matrix = new int[N+1][N+1];
        for(int[] array: matrix){
            Arrays.fill(array, 0);
        }
        SnakeMoveInfo[] snakeMoveInfos = new SnakeMoveInfo[K];
        for(int i = 0; i < K; i++){
            snakeMoveInfos[i] = new SnakeMoveInfo(sc.next().charAt(0), sc.nextInt());
        }

         new Solver(matrix, appleCoordinates, snakeMoveInfos).solve();
    }

    private static class SnakeMoveInfo {
        Character moveDirection;
        int moveCount;

        public SnakeMoveInfo(
                Character d,
                int p
        ){
            this.moveDirection = d;
            this.moveCount = p;
        }
    }

    private static class Coordinate {
        int y;
        int x;
        public Coordinate(
                int y,
                int x
        ) {
                this.y =y;
                this.x =x;
        }
    }
}
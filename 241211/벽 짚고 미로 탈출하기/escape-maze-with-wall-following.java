import java.util.*;

public class Main {
    private static class Solver{
        String[] maze;
        final int MAX_MAZE_INDEX;
        Coordinate currentCoordinate;
        int time = 0;
        int headPosition = 0;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        boolean[][][] isVisited;

        public Solver(
                String[] maze,
                Coordinate startCoordinate
        ){
            this.maze = maze;
            this.currentCoordinate = startCoordinate;
            this.isVisited = new boolean[maze.length][maze.length][4];
            this.MAX_MAZE_INDEX = maze.length-1;
        }

        public void solve() {
            initIsVisited();
            while(true) {
                if(isCanNotEscape()){
                    time = -1;
                    break;
                }
                isVisited[currentCoordinate.y][currentCoordinate.x][headPosition] = true;
                int nextY = currentCoordinate.y + dy[headPosition];
                int nextX = currentCoordinate.x + dx[headPosition];
                if (isOutOfRange(nextY, nextX)) {
                    goFrom(currentCoordinate.y, currentCoordinate.x);
                    break;
                }
                if(isCanNotGo(nextY, nextX)){
                    turnCounterClockWiseHead();
                    continue;
                }
                if(isWallNotExistOnRightSide(nextY, nextX)) {
                    goFrom(currentCoordinate.y, currentCoordinate.x);
                    turnClockWiseHead();
                    goFrom(currentCoordinate.y, currentCoordinate.x);
                    continue;
                }
                goFrom(currentCoordinate.y, currentCoordinate.x);

            }
            System.out.println(time);
        }

        private boolean isCanNotEscape(){
            return isVisited[currentCoordinate.y][currentCoordinate.x][headPosition];
        }

        private void initIsVisited(){
            for(boolean[][] arrays: isVisited){
                for(boolean[] array: arrays) {
                    Arrays.fill(array, false);
                }
            }
        }

        private void goFrom(int y, int x){
            currentCoordinate = new Coordinate(currentCoordinate.y + dy[headPosition], currentCoordinate.x + dx[headPosition]);
            time++;
        }

        private boolean isWallNotExistOnRightSide(int y, int x){
            int nextY = y + dy[(headPosition - 1 + 4)%4];
            int nextX = x + dx[(headPosition - 1 + 4)%4];
            return maze[nextY].charAt(nextX) == '.';
        }

        private void turnClockWiseHead(){
            headPosition = (headPosition - 1 + 4) % 4;
        }

        private void turnCounterClockWiseHead(){
            headPosition = (headPosition + 1) % 4;
        }

        private boolean isCanNotGo(int y, int x){
            return maze[y].charAt(x) == '#';
        }

        private boolean isOutOfRange(int y, int x){
            return y <= 0 || MAX_MAZE_INDEX < y || x <= 0 || MAX_MAZE_INDEX < x;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int y = sc.nextInt();
        int x = sc.nextInt();

        String[] maze = new String[N+1];
        for(int r = 1; r <= N; r++){
            maze[r] = "?";
            maze[r] = maze[r] + sc.next();
        }
        new Solver(maze, new Coordinate(y ,x)).solve();
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
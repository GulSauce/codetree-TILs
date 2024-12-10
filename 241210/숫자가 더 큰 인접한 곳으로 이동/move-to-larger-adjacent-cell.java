import java.util.*;

public class Main {
    private static class Solver{
        int[][] numbers;
        final int MAX_INDEX;
        Coordinate startCoordinate;
        Queue<Integer> result = new LinkedList<>();

        int[] dc = {0, 0 ,-1 ,1};
        int[] dr = {1, -1, 0, 0};

        public  Solver(
                int[][] numbers,
                Coordinate startCoordinate
        ){
            this.numbers = numbers;
            this.startCoordinate = startCoordinate;
            this.MAX_INDEX = numbers.length-1;
        }

        public void solve(){
            moveToNearGreaterNumberWithMemory(startCoordinate.r, startCoordinate.c);
            printResult();
        }

        private void printResult(){
            while(!result.isEmpty()){
                System.out.printf("%d ", result.poll());
            }
        }

        private void moveToNearGreaterNumberWithMemory(int currentR, int currentC){
            memoryThisCoordinate(currentR, currentC);
            for(int i = 0; i < 4; i++){
                int nextR = currentR + dr[i];
                int nextC = currentC + dc[i];
                if(isOutOfRange(nextR, nextC)){
                    continue;
                }
                if(numbers[currentR][currentC] < numbers[nextR][nextC]){
                    moveToNearGreaterNumberWithMemory(nextR, nextC);
                    break;
                }
            }
        }

        private void memoryThisCoordinate(int r, int c){
            result.add(numbers[r][c]);

        }

        private boolean isOutOfRange(int r, int c){
            return !((1 <= r && r <= MAX_INDEX) && (1 <= c  && c <= MAX_INDEX));
        }
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] numbers = new int[n+1][n+1];

        for(int y = 1; y <= n; y++){
            for(int x = 1; x <= n; x++){
                numbers[y][x] = sc.nextInt();
            }
        }

        new Solver(numbers, new Coordinate(r, c)).solve();
    }
}
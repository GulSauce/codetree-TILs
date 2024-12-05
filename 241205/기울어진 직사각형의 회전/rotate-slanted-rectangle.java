import java.util.*;

public class Main{
    private static class Solver{
        int[][] matrix;
        final int MAX_INDEX;
        int directionFlag;
        CounterClockWiseRotator counterClockWiseRotator;
        ClockWiseRotator clockWiseRotator;

        public Solver(
                int[][] matrix,
                CounterClockWiseRotator counterClockWiseRotator
        ){
            this.matrix = matrix;
            MAX_INDEX = matrix.length-1;
            directionFlag = 0;
            this.counterClockWiseRotator = counterClockWiseRotator;
        }

        public Solver(
                int[][] matrix,
                ClockWiseRotator clockWiseRotator
        ){
            this.matrix = matrix;
            MAX_INDEX = matrix.length-1;
            directionFlag = 1;
            this.clockWiseRotator = clockWiseRotator;
        }

        public  void solve(){
            if(directionFlag == 0){
                counterClockWiseRotator.rotate(matrix);
            }
            if(directionFlag == 1){
                clockWiseRotator.rotate(matrix);
            }
            printMatrix();
        }

        private void printMatrix(){
            for(int r = 1; r <= MAX_INDEX; r++){
                for(int c = 1; c <= MAX_INDEX; c++){
                    System.out.printf("%d ", matrix[r][c]);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int n = sc.nextInt();
        int[][] matrix = new int[n+1][n+1];
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= n; c++) {
                matrix[r][c] = sc.nextInt();
            }
        }

        final int r = sc.nextInt();
        final int c = sc.nextInt();
        final int m1 = sc.nextInt();
        final int m2 = sc.nextInt();
        final int m3 = sc.nextInt();
        final int m4 = sc.nextInt();
        final int dir = sc.nextInt();

        if(dir == 0){
            new Solver(matrix, new CounterClockWiseRotator(new Coordinate(r, c), m1, m2, m3, m4)).solve();
        }
        else if(dir == 1){
            new Solver(matrix, new ClockWiseRotator(new Coordinate(r, c), m1, m2, m3, m4)).solve();
        }
        else{
            throw  new IllegalArgumentException();
        }
    }

    private static class ClockWiseRotator{
        Coordinate startCoordinate;
        int m1;
        int m2;
        int m3;
        int m4;

        public ClockWiseRotator(Coordinate startCoordinate, int m1 ,int m2, int m3, int m4) {
            this.startCoordinate = startCoordinate;
            this.m1 = m1;
            this.m2 = m2;
            this.m3 = m3;
            this.m4 = m4;
        }

        Deque<Integer> spreadArray;
        int[][] matrix;

        public void rotate(int[][] matrix) {
            this.matrix = matrix;
            spreadArray = getSpreadArray();
            spreadArray.addFirst(spreadArray.pollLast());
            applyAtArray(spreadArray);
        }

        private void applyAtArray(Deque<Integer> spreadArray){
            int currentR = startCoordinate.r;
            int currentC = startCoordinate.c;
            matrix[currentR][currentC] = spreadArray.pollFirst();

            for(int move = 0; move < m4; move++){
                currentR--;
                currentC--;
                matrix[currentR][currentC] = spreadArray.pollFirst();
            }

            for(int move = 0; move < m3; move++){
                currentR--;
                currentC++;
                matrix[currentR][currentC] = spreadArray.pollFirst();
            }

            for(int move = 0; move < m2; move++){
                currentR++;
                currentC++;
                matrix[currentR][currentC] = spreadArray.pollFirst();
            }

            for(int move = 0; move <= m1-2; move++){
                currentR++;
                currentC--;
                matrix[currentR][currentC] = spreadArray.pollFirst();
            }
        }

        private Deque<Integer> getSpreadArray(){
            Deque<Integer> spreadArray = new ArrayDeque<>();

            int currentR = startCoordinate.r;
            int currentC = startCoordinate.c;
            spreadArray.add(matrix[currentR][currentC]);

            for(int move = 0; move < m4; move++){
                currentR--;
                currentC--;
                spreadArray.add(matrix[currentR][currentC]);
            }

            for(int move = 0; move < m3; move++){
                currentR--;
                currentC++;
                spreadArray.add(matrix[currentR][currentC]);
            }

            for(int move = 0; move < m2; move++){
                currentR++;
                currentC++;
                spreadArray.add(matrix[currentR][currentC]);
            }

            for(int move = 0; move <= m1-2; move++){
                currentR++;
                currentC--;
                spreadArray.add(matrix[currentR][currentC]);
            }

            return spreadArray;
        }
    }

    private static class CounterClockWiseRotator{
        Coordinate startCoordinate;
        int m1;
        int m2;
        int m3;
        int m4;

        public CounterClockWiseRotator(Coordinate startCoordinate, int m1 ,int m2, int m3, int m4) {
            this.startCoordinate = startCoordinate;
            this.m1 = m1;
            this.m2 = m2;
            this.m3 = m3;
            this.m4 = m4;
        }

        Deque<Integer> spreadArray;
        int[][] matrix;

        public void rotate(int[][] matrix) {
            this.matrix = matrix;
            spreadArray = getSpreadArray();
            spreadArray.addFirst(spreadArray.pollLast());
            applyAtArray();
        }

        private void applyAtArray(){
            int currentR = startCoordinate.r;
            int currentC = startCoordinate.c;
            matrix[currentR][currentC] = spreadArray.pollFirst();

            for(int move = 0; move < m1; move++){
                currentR--;
                currentC++;
                matrix[currentR][currentC] = spreadArray.pollFirst();
            }

            for(int move = 0; move < m2; move++){
                currentR--;
                currentC--;
                matrix[currentR][currentC] = spreadArray.pollFirst();
            }

            for(int move = 0; move < m3; move++){
                currentR++;
                currentC--;
                matrix[currentR][currentC] = spreadArray.pollFirst();
            }

            for(int move = 0; move <= m4-2; move++){
                currentR++;
                currentC++;
                matrix[currentR][currentC] = spreadArray.pollFirst();
            }
        }

        private Deque<Integer> getSpreadArray(){
            Deque<Integer> spreadArray = new ArrayDeque<>();

            int currentR = startCoordinate.r;
            int currentC = startCoordinate.c;
            spreadArray.add(matrix[currentR][currentC]);

            for(int move = 0; move < m1; move++){
                currentR--;
                currentC++;
                spreadArray.add(matrix[currentR][currentC]);
            }

            for(int move = 0; move < m2; move++){
                currentR--;
                currentC--;
                spreadArray.add(matrix[currentR][currentC]);
            }

            for(int move = 0; move < m3; move++){
                currentR++;
                currentC--;
                spreadArray.add(matrix[currentR][currentC]);
            }

            for(int move = 0; move <= m4-2; move++){
                currentR++;
                currentC++;
                spreadArray.add(matrix[currentR][currentC]);
            }

            return spreadArray;
        }
    }

    public static class Coordinate{
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
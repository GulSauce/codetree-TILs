import java.util.*;

public class Main {
    private static class Solver{
        final int COUNT_FOR_EXPLODE;
        final int ROTATE_COUNT;
        final int MAX_INDEX;

        int[][] numbers;

        public Solver(
                int M,
                int K,
                int[][] numbers
        ){
            this.COUNT_FOR_EXPLODE = M;
            this.ROTATE_COUNT = K;
            this.MAX_INDEX = numbers.length-1;
            this.numbers = numbers;
        }

        public  void solve(){
            for(int i = 0; i <= ROTATE_COUNT; i++) {
                explodeASAP();
                rotateClockWise90();
                applyGravity();
            }
            printResult();
        }

        private void printResult(){
            int result= 0;
            for(int y= 0 ; y <= MAX_INDEX; y++){
                for(int x= 0; x <= MAX_INDEX; x++){
                    if(numbers[y][x] != 0){
                        result++;
                    }
                }
            }
            System.out.print(result);
        }

        private void explodeASAP() {
            while (true) {
                int[][] original = copyNumbers();
                explode();
                if (notExplode(original)) {
                    break;
                }
                applyGravity();
            }
        }

        private void rotateClockWise90(){
            int[][] original = copyNumbers();
            for(int y= 0; y <= MAX_INDEX; y++){
                for(int x= 0; x <= MAX_INDEX; x++){
                    numbers[y][x] = original[MAX_INDEX-x][y];
                }
            }
        }

        private boolean notExplode(int[][] original) {
            return isNumbersSameWith(original);
        }

        private boolean isNumbersSameWith(int[][] original){
            for(int y= 0; y <= MAX_INDEX; y++){
                for(int x= 0; x <= MAX_INDEX; x++){
                    if(original[y][x] != numbers[y][x]){
                        return false;
                    }
                }
            }
            return true;
        }

        private int[][] copyNumbers(){
            int[][] copiedNumbers = new int[MAX_INDEX+1][MAX_INDEX+1];
            for(int y= 0; y <= MAX_INDEX; y++){
                for(int x= 0; x <= MAX_INDEX; x++){
                    copiedNumbers[y][x] = numbers[y][x];
                }
            }

            return copiedNumbers;
        }

        private void explode() {
            for (int x = 0; x <= MAX_INDEX; x++) {
                explodeAtColumn(x);
            }
        }

        private void explodeAtColumn(int columnNumber){
            setConsecutiveNumberToZero(columnNumber);
        }

        private void applyGravity(){
            Queue<Integer> temp = new LinkedList<>();

            for(int x = 0; x <= MAX_INDEX; x++){
                for(int y = MAX_INDEX; y >= 0; y--){
                    if(numbers[y][x] != 0) {
                        temp.add(numbers[y][x]);
                    }
                }
                for(int y = MAX_INDEX; y >= 0; y--){
                    numbers[y][x] = 0;
                }

                int currentIndex = MAX_INDEX+1;
                while(!temp.isEmpty()){
                    currentIndex--;
                    numbers[currentIndex][x] = temp.poll();
                }
            }
        }

        private void setConsecutiveNumberToZero(int columnNumber){
            int currentNumber = numbers[0][columnNumber];
            int currentNumberCount = 0;
            for(int y = 0; y <= MAX_INDEX; y++){
                int nextNumber = numbers[y][columnNumber];
                if(currentNumber == nextNumber){
                    currentNumberCount++;
                    continue;
                }

                setZeroAt(currentNumberCount, y - 1, columnNumber);

                currentNumber = nextNumber;
                currentNumberCount = 1;
            }

            setZeroAt(currentNumberCount, MAX_INDEX, columnNumber);
        }

        private void setZeroAt(int count, int y, int x) {
            if (COUNT_FOR_EXPLODE <= count) {
                for (int i = y; i > y - count; i--) {
                    numbers[i][x] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        int[][] numbers = new int[N][N];
        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++){
                numbers[y][x] = sc.nextInt();
            }
        }

        new Solver(M, K, numbers).solve();
    }
}
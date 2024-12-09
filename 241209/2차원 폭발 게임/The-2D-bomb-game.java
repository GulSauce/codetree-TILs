import java.util.*;

public class Main {
    private static class Solver{
        int[][] numbers;
        int continueNumberToExplode;
        int rotateCount;
        final int MAX_INDEX;

        public Solver(
                int[][] numbers,
                int M,
                int K
        ){
            this.numbers = numbers;
            this.continueNumberToExplode = M;
            this.rotateCount = K;
            this.MAX_INDEX = numbers.length-1;
        }

        public void solve(){
            for(int i = 0; i <= rotateCount; i++){
                explodeASAP();
                rotateClockWise90();
            }
            printResult();
        }

        private void printResult(){
            int result = 0;
            for(int y = 0; y <= MAX_INDEX; y++){
                for(int x =0; x <= MAX_INDEX; x++){
                    if(numbers[y][x] != 0){
                        result++;
                    }
                }
            }

            System.out.println(result);
        }

        private void explodeASAP(){
            explode();
            applyGravity();
        }

        private void explode(){
            for(int x = 0; x <= MAX_INDEX; x++){
                while(true){
                    boolean isExplode =  explodeColumnAt(x);
                    if(!isExplode){
                        break;
                    }
                }
            }
        }

        private boolean explodeColumnAt(int column){
            boolean isExplode = false;

            int currentIndex = 0;
            while (currentIndex <= MAX_INDEX){
                Queue<Integer> consecutiveNumbers = getConsecutiveNumberStartFrom(column, currentIndex);
                int consecutiveCount = consecutiveNumbers.size();
                if(continueNumberToExplode <= consecutiveCount && consecutiveNumbers.peek() != 0){
                    isExplode = true;
                    for (int i = currentIndex; i < currentIndex + consecutiveCount; i++) {
                        numbers[i][column] = 0;
                    }
                }
                else {
                    for (int i = currentIndex; i < currentIndex + consecutiveCount; i++) {
                        numbers[i][column] = consecutiveNumbers.poll();
                    }
                }
                currentIndex += consecutiveCount;
            }

            return isExplode;
        }

        private Queue<Integer> getConsecutiveNumberStartFrom(int column, int index){
            Queue<Integer> consecutiveNumbers = new LinkedList<>();

            int targetNumber = numbers[index][column];

            for(int y = index; y <= MAX_INDEX; y++){
                if(targetNumber != numbers[y][column]){
                    break;
                }
                consecutiveNumbers.add(numbers[y][column]);
            }

            return consecutiveNumbers;
        }

        private void rotateClockWise90(){
            int[][] copiedNumbers = copyNumbers();
            for(int y = 0; y <= MAX_INDEX; y++){
                for(int x = 0; x <= MAX_INDEX; x++){
                    numbers[y][x] = copiedNumbers[MAX_INDEX-x][y];
                }
            }
            applyGravity();
        }

        private int[][] copyNumbers(){
            int[][] copiedNumbers = new int[MAX_INDEX+1][MAX_INDEX+1];

            for(int y= 0; y <= MAX_INDEX; y++){
                for(int x = 0; x <= MAX_INDEX; x++){
                    copiedNumbers[y][x] = numbers[y][x];
                }
            }
            return copiedNumbers;
        }

        private void applyGravity(){
            for(int x = 0; x <= MAX_INDEX; x++){
                Queue<Integer> numbersQueue = new LinkedList<>();
                for(int y = MAX_INDEX; y >= 0; y--){
                    if(numbers[y][x] == 0){
                        continue;
                    }
                    numbersQueue.add(numbers[y][x]);
                }
                for(int y = MAX_INDEX; y >= 0; y--) {
                    numbers[y][x] = 0;
                }
                int currentIndex = MAX_INDEX + 1;
                while(!numbersQueue.isEmpty()){
                    currentIndex--;
                    numbers[currentIndex][x] = numbersQueue.poll();
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
            for(int x = 0; x < N; x++) {
                numbers[y][x] = sc.nextInt();
            }
        }

        new Solver(numbers, M, K).solve();
    }
}
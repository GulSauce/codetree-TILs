import java.util.*;

public class Main {
    private static class Solver{
        int[][] numbers;
        final int MAX_INDEX;

        private Solver(
                int[][] numbers
        ){
            this.numbers = numbers;
            this.MAX_INDEX = numbers.length-1;
        }

        private void solve(){
            int result = 0;
            for(int y = 0; y <= MAX_INDEX; y++){
                for(int x = 0; x <= MAX_INDEX; x++){
                    int[][] originalNumbers = copyNumbers();
                    explodeAt(y,x);
                    applyGravity();
                    int pairCount = getPairCount();
                    result = Math.max(result, pairCount);
                    numbers = originalNumbers;
                }
            }
            System.out.print(result);
        }

        private int getPairCount(){
            int pairCount = 0;
            for(int y = 0; y <= MAX_INDEX; y++){
                for(int x = 0; x <= MAX_INDEX-1; x++){
                    if(numbers[y][x] == 0){
                        continue;
                    }
                    if(numbers[y][x] == numbers[y][x+1]){
                        pairCount++;
                    }
                }
            }

            for(int x = 0; x <= MAX_INDEX; x++){
                for(int y = 0; y <= MAX_INDEX-1; y++){
                    if(numbers[y][x] == 0){
                        continue;
                    }
                    if(numbers[y][x] == numbers[y+1][x]){
                        pairCount++;
                    }
                }
            }

            return pairCount;
        }

        private void explodeAt(int centerY, int centerX){
            int explosionScale = numbers[centerY][centerX];
            for(int y = centerY; y > centerY-explosionScale; y--){
                if(isOutOfRange(y)){
                    break;
                }
                numbers[y][centerX] = 0;
            }
            for(int y = centerY; y < centerY+explosionScale; y++){
                if(isOutOfRange(y)){
                    break;
                }
                numbers[y][centerX] = 0;
            }
            for(int x = centerX; x > centerX-explosionScale; x--){
                if(isOutOfRange(x)){
                    break;
                }
                numbers[centerY][x] = 0;
            }
            for(int x = centerX; x < centerX+explosionScale; x++){
                if(isOutOfRange(x)){
                    break;
                }
                numbers[centerY][x] = 0;
            }
        }

        private int[][] copyNumbers(){
            int[][] copiedNumbers = new int[MAX_INDEX+1][MAX_INDEX+1];
            for(int y = 0; y <= MAX_INDEX; y++){
                for(int x = 0; x <= MAX_INDEX; x++){
                    copiedNumbers[y][x] = numbers[y][x];
                }
            }
            return copiedNumbers;
        }

        private void applyGravity(){
            for(int x = 0; x <= MAX_INDEX; x++){
                Queue<Integer> temp = new LinkedList<>();
                for(int y = MAX_INDEX; y >= 0; y--){
                    if(numbers[y][x] != 0){
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

        private boolean isOutOfRange(int index){
            return !(0 <= index && index <= MAX_INDEX);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        int[][] numbers = new int[n][n];
        for(int y = 0; y < n; y++){
            for(int x = 0; x < n; x++){
                numbers[y][x] = sc.nextInt();
            }
        }
        new Solver(numbers).solve();
    }
}
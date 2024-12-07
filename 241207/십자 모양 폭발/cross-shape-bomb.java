import java.util.*;

public class Main {
    private static class Solver{
        int[][] numbers;
        BombExplodeInfo bombExplodeInfo;
        final int VALUE_TO_REMOVAL = -1;

        public Solver(
                int[][] numbers,
                BombExplodeInfo bombExplodeInfo
        ){
            this.numbers = numbers;
            this.bombExplodeInfo = bombExplodeInfo;
        }

        public void solve(){
            setValueToRemoval();
            applyGravity();
            printResult();
        }

        private void printResult(){
            for(int r = 1; r < numbers.length; r++){
                for(int c = 1; c < numbers.length; c++){
                    System.out.printf("%d ", numbers[r][c]);
                }
                System.out.println();
            }
        }

        private void applyGravity(){
            for(int c = 1; c < numbers.length;  c++){
                ArrayList<Integer> temp = new ArrayList<>();
                for(int r = numbers.length-1; r > 0; r--){
                    if(numbers[r][c] == VALUE_TO_REMOVAL){
                        continue;
                    }
                    temp.add(numbers[r][c]);
                }
                for(int r = temp.size()+1; r <= numbers.length-1; r++){
                    temp.add(0);
                }
                for(int r = numbers.length-1; r > 0; r--){
                    numbers[r][c] = temp.get(numbers.length-1-(r));
                }
            }
        }

        private void setValueToRemoval(){
            int startR = bombExplodeInfo.r;
            int startC = bombExplodeInfo.c;

            int bombScale = numbers[startR][startC];
            numbers[startR][startC] = VALUE_TO_REMOVAL;
            for(int r = startR-1; r > startR-bombScale; r--){
                if(isOutOfRange(r)){
                    break;
                }
                numbers[r][startC] = VALUE_TO_REMOVAL;
            }
            for(int r = startR+1; r < startR+bombScale; r++){
                if(isOutOfRange(r)){
                    break;
                }
                numbers[r][startC] = VALUE_TO_REMOVAL;
            }
            for(int c = startC-1; c > startC-bombScale; c--){
                if(isOutOfRange(c)){
                    break;
                }
                numbers[startR][c] = VALUE_TO_REMOVAL;
            }
            for(int c = startC+1; c < startC+bombScale; c++){
                if(isOutOfRange(c)){
                    break;
                }
                numbers[startR][c] = VALUE_TO_REMOVAL;
            }
        }

        private boolean isOutOfRange(int index){
            return index <= 0 || numbers.length <= index;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] numbers = new int[n+1][n+1];
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <=n; c++){
                numbers[r][c] = sc.nextInt();
            }
        }

        new Solver(numbers, new BombExplodeInfo(sc.nextInt(), sc.nextInt())).solve();
    }

    private static class BombExplodeInfo{
        int r;
        int c;

        public BombExplodeInfo(
                int r,
                int c
        ){
            this.r = r;
            this.c = c;
        }
    }
}
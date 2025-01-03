import java.util.*;

public class Main {
    private static class Solver{
        List<Integer> numbers;
        int endIndex;
        int answer = Integer.MAX_VALUE;
        int currentMoveCount = 0;

        public Solver(
                List<Integer> numbers
        ){
            this.numbers = numbers;
            this.endIndex = numbers.size()-1;
        }

        public void solve(){
            getCombination(0);
            printAnswer();
        }

        private void printAnswer(){
            if(answer == Integer.MAX_VALUE){
                System.out.println(-1);
            }
            else{
                System.out.println(answer);
            }
        }

        private void getCombination(int currentIndex){
            if(endIndex <= currentIndex){
                answer = Math.min(answer, currentMoveCount);
                return;
            }

            int maxPossibleMoveCount = numbers.get(currentIndex);
            for(int move = 1; move <= maxPossibleMoveCount; move++){
                currentMoveCount++;
                getCombination(currentIndex + move);
                currentMoveCount--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < n; i++){
            numbers.add(sc.nextInt());
        }

        new Solver(numbers).solve();
    }
}
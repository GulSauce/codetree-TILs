import java.util.*;

public class Main {
    private static class Solver{
        ArrayList<Integer> numbers;
        ArrayList<Integer> getNumbersToChange;
        int consecutiveCount;

        public Solver(
                ArrayList<Integer> numbers,
                int consecutiveCount
        ){
            this.numbers = numbers;
            this.consecutiveCount = consecutiveCount;
        }

        public void solve(){
            while(true) {
                ArrayList<Integer> numberToChange = removeAboveConsecutiveCount();
                if(isNumbersNotChanged(numberToChange)){
                    break;
                }
                numbers = numberToChange;
                if(numberToChange.isEmpty()){
                    break;
                }
            }
            printResult();
        }

        private boolean isNumbersNotChanged(ArrayList<Integer> target){
            return numbers.size() == target.size();
        }

        private void printResult(){
            System.out.println(numbers.size());
            for(int number: numbers){
                System.out.println(number);
            }
        }

        private ArrayList<Integer> removeAboveConsecutiveCount() {
            ArrayList<Integer> numbersAfterExplosion = new ArrayList<>();
            int currentNumber = numbers.get(0);
            int currentNumberCount = 1;
            for (int i = 1; i < numbers.size(); i++) {
                if (currentNumber != numbers.get(i)) {
                     if (consecutiveCount <= currentNumberCount) {
                        currentNumber = numbers.get(i);
                        currentNumberCount = 1;
                        continue;
                    }
                    for (int j = 0; j < currentNumberCount; j++) {
                        numbersAfterExplosion.add(currentNumber);
                        currentNumber = numbers.get(i);
                        currentNumberCount = 1;
                    }
                }
                else {
                    currentNumberCount++;
                }
            }

            if (currentNumberCount < consecutiveCount) {
                for (int j = 0; j < currentNumberCount; j++) {
                    numbersAfterExplosion.add(currentNumber);
                }
            }
            return numbersAfterExplosion;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < N; i++){
            numbers.add(sc.nextInt());
        }

        new Solver(numbers, M).solve();
    }
}
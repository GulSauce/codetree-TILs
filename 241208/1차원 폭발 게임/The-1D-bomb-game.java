import java.util.*;

public class Main {
    private static class Solver{
        ArrayList<Integer> numbers;
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

            int currentIndex = 0;
            while(currentIndex < numbers.size()) {
                ArrayList<Integer> consecutiveNumbers = getConsecutiveNumbersStartFromIndex(currentIndex);
                currentIndex += consecutiveNumbers.size();
                if (consecutiveCount <= consecutiveNumbers.size()) {
                    continue;
                }
                numbersAfterExplosion.addAll(consecutiveNumbers);
            }

            return numbersAfterExplosion;
        }

        private ArrayList<Integer> getConsecutiveNumbersStartFromIndex(int index){
            ArrayList<Integer> result = new ArrayList<>();
            int currentNumber = numbers.get(index);
            for(int i = index; i < numbers.size(); i++){
                if(numbers.get(i) != currentNumber){
                    break;
                }
                result.add(numbers.get(i));
            }
            return result;
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
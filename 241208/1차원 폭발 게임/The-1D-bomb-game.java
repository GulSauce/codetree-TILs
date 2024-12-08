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
            removeAboveConsecutiveCount();
            printResult();
        }

        private void printResult(){
            System.out.println(numbers.size());
            for(int number: numbers){
                System.out.println(number);
            }
        }

        private void removeAboveConsecutiveCount() {
            while (true) {
                ArrayList<Integer> numbersAfterExplosion = new ArrayList<>();
                int currentNumber = numbers.get(0);
                int currentNumberCount = 1;
                boolean isChanged = false;
                for (int i = 1; i < numbers.size(); i++) {
                    if (currentNumber != numbers.get(i)) {
                         if (consecutiveCount <= currentNumberCount) {
                            isChanged = true;
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

                numbers = numbersAfterExplosion;
                if(!isChanged){
                    break;
                }
            }
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
import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    private static class Solver{
        int digit;
        int result = 0;
        List<Integer> numbers = new ArrayList<>();

        public Solver(
                int n
        ){
            this.digit = n;
        }

        private void solve(){
            countBeautifulNumber(1);
            System.out.print(result);
        }

        private void countBeautifulNumber(int repeatCount){
            if(digit < repeatCount){
                if(isBeautifulNumber()){
                    result++;
                }
                return;
            }
            for(int number = 1; number <= 4; number++){
                numbers.add(number);
                countBeautifulNumber(repeatCount+1);
                numbers.remove(numbers.size()-1);
            }
        }

        private boolean isBeautifulNumber(){
            int targetNumber = numbers.get(0);
            int targetNumberCount = 1;

            for(int i = 1; i < numbers.size(); i++){
                if(targetNumber == targetNumberCount){
                    targetNumber = numbers.get(i);
                    targetNumberCount = 1;
                    continue;
                }

                int currentNumber = numbers.get(i);
                if(targetNumber == currentNumber){
                    targetNumberCount++;
                    continue;
                }

                if(targetNumberCount < targetNumber){
                    return false;
                }
                targetNumber = currentNumber;
                targetNumberCount = 1;
            }
            if(targetNumberCount != targetNumber){
                return false;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        new Solver(n).solve();
    }
}
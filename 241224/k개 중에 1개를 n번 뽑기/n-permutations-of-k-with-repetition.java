import java.util.*;

public class Main {
    public static class Solver{
        int maxNumber;
        int totalRepeat;

        List<Integer> combinations = new ArrayList<>();

        public Solver(
                int K,
                int N
        ){
            this.maxNumber = K;
            this.totalRepeat = N;
        }

        public void solve(){
            printCombinations(1);
        }

        public void printCombinations(int repeatCount){
            if(totalRepeat < repeatCount){
                printCombination();
                return;
            }
            for(int number = 1; number <= maxNumber; number++){
                combinations.add(number);
                printCombinations(repeatCount+1);
                combinations.remove(combinations.size()-1);
            }
        }

        private void printCombination(){
            for(int number: combinations){
                System.out.print(number);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        new Solver(K, N).solve();
    }
}
import java.util.*;

public class Main {
    private static class Solver{
        int maxNumber;
        int choiceCount;

        List<Integer> combination = new ArrayList<>();

        public Solver(
                int N,
                int M
        ){
            this.maxNumber = N;
            this.choiceCount = M;
        }

        public void solve(){
            getCombination(0);
        }

        private void getCombination(int prevNumber){
            if(combination.size() == choiceCount){
                printCombination();
            }

            for(int number = prevNumber+1; number <= maxNumber; number++){
                combination.add(number);
                getCombination(number);
                combination.remove(combination.size()-1);
            }
        }

        private void printCombination(){
            for(int number: combination){
                System.out.printf("%d ", number);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        new Solver(N, M).solve();
    }
}
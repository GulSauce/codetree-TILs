import java.util.*;

public class Main {
    private static class Solver{
        int maxNumber;
        int choiceCount;
        int answer = 0;

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
            System.out.print(answer);
        }

        private void getCombination(int prevNumber){
            if(combination.size() == choiceCount){
                answer = Math.max(answer, getXorResult());
            }

            for(int number = prevNumber+1; number <= maxNumber; number++){
                combination.add(number);
                getCombination(number);
                combination.remove(combination.size()-1);
            }
        }

        private int getXorResult(){
            int number = combination.get(0);
            for(int i = 1; i < combination.size(); i++){
                number ^= combination.get(i);
            }
            return number;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        new Solver(N, M).solve();
    }
}
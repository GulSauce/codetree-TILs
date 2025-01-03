import java.util.*;

public class Main {
    private static class Solver{
        List<Integer> numbers;
        int choiceCount;
        int answer = 0;

        List<Integer> combination = new ArrayList<>();

        public Solver(
                List<Integer> numbers,
                int M
        ){
            this.numbers = numbers;
            this.choiceCount = M;
        }

        public void solve(){
            getCombination(-1);
            System.out.print(answer);
        }

        private void getCombination(int prevIndex){
            if(combination.size() == choiceCount){
                answer = Math.max(answer, getXorResult());
            }

            for(int i = prevIndex+1; i < numbers.size(); i++){
                combination.add(numbers.get(i));
                getCombination(i);
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
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < N; i++){
            numbers.add(sc.nextInt());
        }
        int M = sc.nextInt();

        new Solver(numbers, M).solve();
    }
}
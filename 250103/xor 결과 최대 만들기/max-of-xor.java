import java.util.*;

public class Main {
    private static class Solver{
        List<Integer> numbers;
        int choiceCount;
        int answer = 0;


        public Solver(
                List<Integer> numbers,
                int M
        ){
            this.numbers = numbers;
            this.choiceCount = M;
        }

        public void solve(){
            getCombination(0, -1, 0);
            System.out.print(answer);
        }

        private void getCombination(int prevCount, int prevIndex, int prevXorValue){
            if(prevCount == choiceCount){
                answer = Math.max(answer, prevXorValue);
            }

            for(int i = prevIndex+1; i < numbers.size(); i++){
                getCombination(prevCount+1, i, prevXorValue ^ numbers.get(i));
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < N; i++){
            numbers.add(sc.nextInt());
        }

        new Solver(numbers, M).solve();
    }
}
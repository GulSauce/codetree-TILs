import java.util.*;

public class Main {
    private static class Solver{
        int groupSize;
        int answer = Integer.MAX_VALUE;

        List<Integer> numbers;
        List<Integer> aGroup = new ArrayList<>();
        List<Integer> bGroup = new ArrayList<>();

        public Solver(
                List<Integer>numbers
        ){
            this.numbers = numbers;
            this.groupSize = numbers.size()/2;
        }

        public void solve(){
            getCombination(-1, 0,0);
            System.out.print(answer);
        }

        private void getCombination(int prevIndex, int aGroupSize, int bGroupSize){
            if(aGroupSize == groupSize && bGroupSize == groupSize){
                answer = Math.min(answer, getDiff());
                return;
            }

            for(int i = prevIndex+1; i < numbers.size(); i++){
                if(aGroupSize < groupSize){
                    aGroup.add(numbers.get(i));
                    getCombination(i, aGroupSize+1, bGroupSize);
                    aGroup.remove(aGroup.size()-1);
                }
                if(bGroupSize < groupSize){
                    bGroup.add(numbers.get(i));
                    getCombination(i, aGroupSize, bGroupSize+1);
                    bGroup.remove(bGroup.size()-1);
                }
            }
        }

        private int getDiff(){
            int aSum = 0;
            for(int number: aGroup){
                aSum+=number;
            }

            int bSum = 0;
            for(int number: bGroup){
                bSum += number;
            }
            return Math.abs(aSum-bSum);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 2*n; i++){
            numbers.add(sc.nextInt());
        }
        new Solver(numbers).solve();
    }
}
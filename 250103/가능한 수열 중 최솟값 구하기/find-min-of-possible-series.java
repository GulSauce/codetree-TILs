import java.util.*;

public class Main {
    private static class Solver{
        int sequenceSize;
        List<Integer> result = new ArrayList<>();
        List<Integer> currentSequence = new ArrayList<>();

        public Solver(
                int n
        ){
            this.sequenceSize = n;
        }

        public void solve(){
            initResult();
            getPermutation(0);
            printResult();
        }

        private void initResult(){
            for(int i = 0; i < sequenceSize; i++){
                result.add(6);
            }
        }

        private void printResult(){
            for(int number: result){
                System.out.print(number);
            }
        }

        private void getPermutation(int curSize){
            if(curSize == sequenceSize){
                if(isPrecede()){
                    result.clear();
                    result.addAll(currentSequence);
                }
                return;
            }

            for(int i = 4; i <= 6; i++){
                if(isDuplicateWhenAdd(i)){
                    continue;
                }
                currentSequence.add(i);
                getPermutation(curSize+1);
                currentSequence.remove(currentSequence.size()-1);
            }
        }

        private boolean isPrecede(){
            for(int i = 0; i < sequenceSize; i++){
                if(currentSequence.get(i) < result.get(i)){
                    return true;
                }
                else if(result.get(i) < currentSequence.get(i)){
                    return false;
                }
            }
            return false;
        }

        private boolean isDuplicateWhenAdd(int addNumber) {
            currentSequence.add(addNumber);
            boolean isDuplicated = false;
            int end = currentSequence.size()-1;
            for(int start = 0; start < currentSequence.size()-1; start++){
                int numberCount = end - start + 1;
                if(numberCount % 2 == 1){
                    continue;
                }
                int mid = (start+end)/2;
                for(int i = start; i <= mid; i++){
                    isDuplicated = true;
                    if(currentSequence.get(i) != currentSequence.get(i-start+mid+1)){
                        isDuplicated = false;
                        break;
                    }
                }
                if(isDuplicated){
                    break;
                }
            }
            currentSequence.remove(currentSequence.size()-1);
            return isDuplicated;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        new Solver(n).solve();
    }
}
import java.util.*;

public class Main {
    private static class Solver{
        int sequenceSize;
        List<Integer> currentSequence = new ArrayList<>();

        public Solver(
                int n
        ){
            this.sequenceSize = n;
        }

        public void solve(){
            getPermutation(0);
        }

        private void getPermutation(int curSize){
            if(curSize == sequenceSize){
                if(isPrecede()){
                    for(int number: currentSequence){
                        System.out.print(number);
                    }
                    System.exit(0);
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
            for(int start = 0; start < end; start++){
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

            currentSequence.remove(end);
            return isDuplicated;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        new Solver(n).solve();
    }
}
import java.util.*;

public class Main {
    private static class Solver{
        int maxNumber;
        int maxRepeatCount;

        boolean[] isVisited;
        List<Integer> numbers = new ArrayList<>();


        public Solver(
                int n
        ){
            this.maxNumber = n;
            this.maxRepeatCount = n;
            this.isVisited = new boolean[n+1];
        }

        public void solve(){
            initIsVisited();
            getPermutation(0);
        }

        private void getPermutation(int repeatCount){
            if(repeatCount == maxRepeatCount){
                for(int number: numbers){
                    System.out.print(number);
                    System.out.print(" ");
                }
                System.out.println();
            }

            for(int i = maxNumber; i >= 1; i--){
                if(isVisited[i]){
                    continue;
                }
                numbers.add(i);
                isVisited[i] = true;
                getPermutation(repeatCount+1);
                numbers.remove(numbers.size()-1);
                isVisited[i] = false;
            }
        }

        private void initIsVisited(){
            Arrays.fill(isVisited, false);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        new Solver(n).solve();
    }
}
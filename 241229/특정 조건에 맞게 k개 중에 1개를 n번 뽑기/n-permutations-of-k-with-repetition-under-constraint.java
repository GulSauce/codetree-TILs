import java.util.*;

public class Main {
    private static class Solver{
        int maxNumber;
        int repeatCount;

        List<Integer> permutation = new ArrayList<>();
        public Solver(
                int K,
                int N
        ){
            this.maxNumber = K;
            this.repeatCount = N;
        }

        public void solve(){
            getPermutation(0);
        }

        private void getPermutation(int currentSize){
            if(currentSize == repeatCount){
                printPermutation();
                return;
            }

            for(int i = 1; i <= maxNumber; i++){
                if(2 <= currentSize){
                    if(i == permutation.get(currentSize-1) && permutation.get(currentSize-1) ==  permutation.get(currentSize-2)){
                        continue;
                    }
                }
                permutation.add(i);
                getPermutation(currentSize+1);
                permutation.remove(permutation.size()-1);
            }
        }

        private void printPermutation(){
            for(int number: permutation){
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
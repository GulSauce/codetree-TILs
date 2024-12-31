import java.util.*;

public class Main {
    private static class Solver{
        int turnCount;
        int arrivalNumber;
        int unitCount;
        int[] goCounts;

        int arrivedUnitCount = 0;

        List<Integer> permutation = new ArrayList<>();

        public Solver(
                int n,
                int m,
                int k,
                int[] goCounts
        ){
            this.turnCount = n;
            this.arrivalNumber = m;
            this.unitCount = k;
            this.goCounts = goCounts;
        }

        public void solve(){
            getPermutation(0);
            System.out.println(arrivedUnitCount);
        }

        private void getPermutation(int currentSize){
            if(currentSize == turnCount){
                arrivedUnitCount = Math.max(arrivedUnitCount, getArrivedUnitCount());
                return;
            }

            for(int unit = 1; unit <= unitCount; unit++){
                if(isArrived(unit)){
                    permutation.add(0);
                    getPermutation(currentSize+1);
                    permutation.remove(0);
                    continue;
                }
                permutation.add(unit);
                getPermutation(currentSize+1);
                permutation.remove(permutation.size()-1);
            }
        }

        private int getArrivedUnitCount(){
            int arrivedUnitCount = 0;
            for(int unit = 1; unit <= unitCount; unit++){
                if(isArrived(unit)){
                    arrivedUnitCount++;
                }
            }

            return arrivedUnitCount;
        }

        private boolean isArrived(int unit){
            int goCount = 0;
            for(int i = 0; i < permutation.size(); i++){
                if(permutation.get(i) == unit){
                    goCount += goCounts[i];
                }
            }
            if(arrivalNumber -1 <= goCount) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[] goCounts = new int[n];
        for(int i = 0; i < n; i++){
            goCounts[i] = sc.nextInt();
        }

        new Solver(n, m, k, goCounts).solve();
    }
}
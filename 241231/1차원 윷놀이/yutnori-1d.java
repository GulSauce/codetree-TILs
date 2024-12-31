import java.util.*;

public class Main {
    private static class Solver{
        int maxTurn;
        int goalNumber;
        int unitCount;
        int arrivedUnitCount = 0;
        int[] unitMoveCounts;
        int[] turnMoveCounts;

        public Solver(
                int n,
                int m,
                int k,
                int[] turnMoveCounts
        ){
            this.maxTurn = n;
            this.goalNumber = m;
            this.unitCount = k;
            this.turnMoveCounts = turnMoveCounts;
            this.unitMoveCounts = new int[unitCount+1];
        }

        public void solve(){
            getPermutation(0);
            System.out.println(arrivedUnitCount);
        }

        private void getPermutation(int currentTurn){
            arrivedUnitCount = Math.max(arrivedUnitCount, getArrivedUnitCount());

            if(currentTurn == maxTurn){
                return;
            }

            for(int unit = 1; unit <= unitCount; unit++){
                if(goalNumber -1 <= unitMoveCounts[unit]){
                    continue;
                }
                unitMoveCounts[unit] += turnMoveCounts[currentTurn];
                getPermutation(currentTurn+1);
                unitMoveCounts[unit] -= turnMoveCounts[currentTurn];
            }
        }

        private int getArrivedUnitCount(){
            int arrivedUnitCount = 0;
            for (int unit = 1; unit <= unitCount; unit++) {
                if(goalNumber -1 <= unitMoveCounts[unit]){
                    arrivedUnitCount++;
                }
            }
            return arrivedUnitCount;
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
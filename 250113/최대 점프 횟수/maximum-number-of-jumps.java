import java.util.*;

public class Main {
    private static class Solver{
        int n;
        int jumpDistancesIndex;

        final int CAN_NOT_JUMP = -1;

        int[] dp;

        List<Integer> jumpDistances;

        public Solver(
                int n,
                List<Integer> jumpDistances
        ){
            this.n = n;
            this.dp = new int[n];
            this.jumpDistances = jumpDistances;
            this.jumpDistancesIndex = jumpDistances.size()-1;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            int answer = 0;
            for(int number: dp){
                answer = Math.max(answer, number);
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(int cur = 1; cur <= jumpDistancesIndex; cur++){
                for(int prev = 0; prev < cur; prev++){
                    if(prev + jumpDistances.get(prev) < cur){
                        continue;
                    }
                    if(dp[prev] == CAN_NOT_JUMP){
                        continue;
                    }
                    dp[cur] = Math.max(dp[cur], dp[prev]+1);
                }
            }
        }

        private void initDP(){
            Arrays.fill(dp, CAN_NOT_JUMP);
            dp[0] = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> jumpDistances = new ArrayList<>();
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            jumpDistances.add(sc.nextInt());
        }
        new Solver(n, jumpDistances).solve();
    }
}
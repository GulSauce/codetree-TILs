import java.time.Year;
import java.util.*;

public class Main {
    private static class Solver{
        int gemsIndex;
        int maxWeight;

        final int NOT_ALLOCATED = Integer.MIN_VALUE;
        final int MAX_WEIGHT = 10000;

        int[][] dp;
        List<Gem> gems;

        public Solver(
                int N,
                int M,
                List<Gem> gems
        ){
            this.gemsIndex = N-1;
            this.maxWeight = M;
            this.dp = new int[N][MAX_WEIGHT+1];
            this.gems = gems;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printDP(){
            for(int[] array: dp){
                for(int value: array){
                    System.out.printf("%d ", value);
                }
                System.out.println();
            }
        }

        private void printAnswer(){
            int answer = 0;
            for(int[] array: dp){
                for(int i = 0; i <= maxWeight; i++){
                    answer = Math.max(answer, array[i]);
                }
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(int i = 1; i <= gemsIndex; i++){
                for(int weight = 0; weight <= maxWeight; weight++){
                    Gem curGem = gems.get(i);
                    if(weight - curGem.weight < 0){
                        dp[i][weight] = Math.max(dp[i-1][weight], dp[i][weight]);
                        continue;
                    }
                    dp[i][weight] = Math.max(Math.max(dp[i-1][weight], dp[i][weight]), dp[i-1][weight-curGem.weight]+curGem.value);
                }
            }
        }

        private void initDP(){
            for(int[] array: dp){
                Arrays.fill(array, NOT_ALLOCATED);
            }
            for(int i = 0; i <= gemsIndex; i++) {
                dp[i][0] = 0;
            }
            dp[0][gems.get(0).weight] = gems.get(0).value;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Gem> gems = new ArrayList<>();
        for(int i = 0; i < N; i++){
            gems.add(new Gem(sc.nextInt(), sc.nextInt()));
        }
        new Solver(N, M, gems).solve();
    }

    private static class Gem{
        int weight;
        int value;

        public Gem(
                int w,
                int v
        ){
            this.weight = w;
            this.value = v;
        }
    }
}
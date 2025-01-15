import java.util.*;

public class Main {
    private static class Solver{
        int gemsIndex;
        int maxWeight;

        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        int[] dp;
        List<Gem> gems;

        public Solver(
                int N,
                int M,
                List<Gem> gems
        ){
            this.gemsIndex = N;
            this.maxWeight = M;
            this.dp = new int[M+1];
            this.gems = gems;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            int answer = 0;
            for(int value: dp){
                answer = Math.max(value, answer);
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(Gem gem: gems){
                for(int cur = maxWeight; cur >= 0; cur--){
                    if(cur - gem.weight < 0){
                        continue;
                    }
                    if(dp[cur-gem.weight] == NOT_ALLOCATED){
                        continue;
                    }
                    dp[cur] = Math.max(dp[cur-gem.weight]+gem.value, dp[cur]);
                }
            }
        }

        private void initDP(){
            Arrays.fill(dp, NOT_ALLOCATED);
            dp[0] = 0;
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
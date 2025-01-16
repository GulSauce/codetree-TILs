import java.util.*;

public class Main {
    private static class Solver{
        int gemsIndex;
        int maxWeight;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<Gem> gems;

        int[] dp;

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
                answer = Math.max(answer, value);
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(int weight = 1; weight <= maxWeight; weight++){
                for(Gem gem: gems){
                    int prev = weight - gem.weight;
                    if(prev < 0){
                        continue;
                    }
                    if(dp[prev] == NOT_ALLOCATED){
                        continue;
                    }
                    dp[weight] = Math.max(dp[weight], dp[prev]+gem.value);
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
            int w = sc.nextInt();
            int v = sc.nextInt();
            gems.add(new Gem(w, v));
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
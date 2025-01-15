import java.util.*;

public class Main {
    private static class Solver{
        int maxLength;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        int[] dp;

        List<Integer> pricePerLengthInfos;

        public Solver(
                int n,
                List<Integer> pricePerLengthInfos
        ){
            this.maxLength = n;
            this.dp = new int[n+1];
            this.pricePerLengthInfos = pricePerLengthInfos;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dp[maxLength]);
        }

        private void calcDP(){
            for(int curLength = 1; curLength <= maxLength; curLength++){
                for(int stickLength = 1; stickLength <= curLength; stickLength++){
                    if(curLength - stickLength < 0){
                        continue;
                    }
                    if(dp[curLength-stickLength] == NOT_ALLOCATED){
                        continue;
                    }
                    int stickValue = pricePerLengthInfos.get(stickLength);
                    dp[curLength] = Math.max(dp[curLength], dp[curLength-stickLength]+stickValue);
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
        int n = sc.nextInt();
        List<Integer> pricePerLengthInfos = new ArrayList<>();
        pricePerLengthInfos.add(0);
        for(int length = 0; length < n; length++){
            int price = sc.nextInt();
            pricePerLengthInfos.add(price);
        }
        new Solver(n, pricePerLengthInfos).solve();
    }
}
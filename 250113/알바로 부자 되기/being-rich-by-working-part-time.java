import java.util.*;

public class Main {
    private static class Solver{
        int workInfosIndex;

        int[] dp;

        List<WorKInfo> worKInfos;

        public Solver(
                int N,
                List<WorKInfo> worKInfos
        ){
            this.workInfosIndex = N-1;
            this.dp = new int[N];
            this.worKInfos = worKInfos;
        }

        public void solve(){
            Collections.sort(worKInfos);
            initDP();
            calcDP();
            printAnswer();
        }

        private void calcDP(){
            for(int cur = 1; cur <= workInfosIndex; cur++){
                for(int prev = 0; prev < cur; prev++){
                    WorKInfo prevWorkInfo = worKInfos.get(prev);
                    WorKInfo curWorkInfo = worKInfos.get(cur);
                    if(curWorkInfo.startDate <= prevWorkInfo.endDate){
                        continue;
                    }
                    dp[cur] = Math.max(dp[cur], dp[prev]+curWorkInfo.pay);
                }
            }
        }

        private void printAnswer(){
            int answer = 0;
            for(int value: dp){
                answer = Math.max(value, answer);
            }
            System.out.println(answer);
        }

        private void initDP(){
            for(int i = 0; i <= workInfosIndex; i++){
                dp[i] = worKInfos.get(i).pay;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<WorKInfo> worKInfos = new ArrayList<>();
        for(int i = 0; i < N; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int p = sc.nextInt();
            worKInfos.add(new WorKInfo(s, e, p));
        }
        new Solver(N, worKInfos).solve();
    }

    private static class WorKInfo implements Comparable<WorKInfo>{
        int startDate;
        int endDate;
        int pay;

        public WorKInfo(
                int s,
                int e,
                int p
        ){
            this.startDate = s;
            this.endDate = e;
            this.pay = p;
        }

        @Override
        public int compareTo(WorKInfo worKInfo){
            return this.startDate - worKInfo.startDate;
        }
    }
}
import java.util.*;

public class Main {
    private static class Solver{
        int questsIndex;
        int targetExp;
        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        int[] dp;
        List<Quest> quests;

        public Solver(
                int n,
                int m,
                List<Quest> quests
        ){
            this.questsIndex = n;
            this.targetExp = m;
            this.dp = new int[targetExp+1];
            this.quests = quests;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printDP(){
            for(int i = 0; i <= targetExp; i++){
                System.out.printf("%d ", dp[i]);
            }
            System.out.println();
        }

        private void printAnswer(){
            int answer = dp[targetExp];
            if(answer == Integer.MAX_VALUE){
                System.out.println(-1);
                return;
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(Quest quest: quests){
                for(int startExp = targetExp; startExp >= 0; startExp--){
                    if(dp[startExp] == NOT_ALLOCATED){
                        continue;
                    }
                    if(targetExp <= startExp + quest.exp){
                        dp[targetExp] = Math.min(dp[targetExp], dp[startExp]+quest.time);
                        continue;
                    }
                    dp[startExp+quest.exp] = Math.min(dp[startExp]+quest.time, dp[startExp+quest.exp]);
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
        int m = sc.nextInt();

        List<Quest>  quests = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int e = sc.nextInt();
            int t = sc.nextInt();
            quests.add(new Quest(e, t));
        }

        new Solver(n, m, quests).solve();
    }

    private static class Quest{
        int exp;
        int time;

        public Quest(
                int e,
                int t
        ){
            this.exp = e;
            this.time = t;
        }
    }
}
import java.util.*;

public class Main {
    private static class Solver{
        int questsIndex;
        int targetExp;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;
        final int MAX_TIME = 10000;

        int[] dp;
        List<Quest> quests;

        public Solver(
                int n,
                int m,
                List<Quest> quests
        ){
            this.questsIndex = n;
            this.targetExp = m;
            this.dp = new int[MAX_TIME+1];
            this.quests = quests;
        }

        public void solve(){
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer(){
            int answer = -1;
            for(int time = 0; time <= MAX_TIME; time++){
                if(targetExp <= dp[time]){
                    answer = time;
                    break;
                }
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(Quest quest: quests){
                for(int time = MAX_TIME; time >= 0; time--){
                    if(time - quest.time < 0){
                        continue;
                    }
                    if(dp[time-quest.time] == NOT_ALLOCATED){
                        continue;
                    }
                    dp[time] = Math.max(dp[time- quest.time]+quest.exp, dp[time]);
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int questInfosIndex;
        int targetExp;
        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        List<QuestInfo> questInfos;

        int[] dp;

        public Solver(
            int N,
            int M,
            List<QuestInfo> questInfos
        ) {
            this.questInfosIndex = N;
            this.targetExp = M;
            this.questInfos = questInfos;
            this.dp = new int[M + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            if (dp[targetExp] == NOT_ALLOCATED) {
                System.out.println(-1);
                return;
            }
            System.out.println(dp[targetExp]);
        }

        private void calcDP() {
            for (QuestInfo questInfo : questInfos) {
                for (int curExp = targetExp; curExp >= 1; curExp--) {
                    if (curExp - questInfo.exp < 0) {
                        dp[curExp] = Math.min(dp[curExp], questInfo.requiredTime);
                        continue;
                    }
                    if (dp[curExp - questInfo.exp] == NOT_ALLOCATED) {
                        continue;
                    }
                    dp[curExp] = Math.min(dp[curExp],
                        dp[curExp - questInfo.exp] + questInfo.requiredTime);
                }
            }
        }

        private void initDP() {
            Arrays.fill(dp, NOT_ALLOCATED);
            dp[0] = 0;
        }
    }

    public static void main(String[] args) {
        int N;
        int M;
        List<QuestInfo> questInfos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            questInfos.add(new QuestInfo(sc.nextInt(), sc.nextInt()));
        }
        sc.close();

        new Main.Solver(N, M, questInfos).solve();
    }

    private static class QuestInfo {

        int exp;
        int requiredTime;

        public QuestInfo(
            int e,
            int t
        ) {
            this.exp = e;
            this.requiredTime = t;
        }
    }
}
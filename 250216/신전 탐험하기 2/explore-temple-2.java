import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int templeInfosIndex;
        List<TempleInfo> templeInfos;

        int[][][] dp;

        public Solver(
            int N,
            List<TempleInfo> templeInfos
        ) {
            this.templeInfosIndex = N;
            this.templeInfos = templeInfos;
            this.dp = new int[N + 1][3][3];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int i = 0; i <= 2; i++) {
                for (int k = 0; k <= 2; k++) {
                    if (i == k) {
                        continue;
                    }
                    answer = Math.max(dp[templeInfosIndex][i][k], answer);
                }
            }

            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 2; i <= templeInfosIndex; i++) {
                for (int j = 0; j <= 2; j++) {
                    for (int k = 0; k <= 2; k++) {
                        if (j == 0) {
                            dp[i][j][k] = Math.max(
                                dp[i][j][k], dp[i - 1][1][k] + templeInfos.get(i).leftScore);
                            dp[i][j][k] = Math.max(
                                dp[i][j][k], dp[i - 1][2][k] + templeInfos.get(i).leftScore);
                        }
                        if (j == 1) {
                            dp[i][j][k] = Math.max(
                                dp[i][j][k], dp[i - 1][0][k] + templeInfos.get(i).midScore);
                            dp[i][j][k] = Math.max(
                                dp[i][j][k], dp[i - 1][2][k] + templeInfos.get(i).midScore);
                        }
                        if (j == 2) {
                            dp[i][j][k] = Math.max(
                                dp[i][j][k], dp[i - 1][0][k] + templeInfos.get(i).rightScore);
                            dp[i][j][k] = Math.max(
                                dp[i][j][k], dp[i - 1][1][k] + templeInfos.get(i).rightScore);
                        }
                    }
                }
            }
        }

        private void initDP() {
            dp[1][0][0] = templeInfos.get(1).leftScore;
            dp[1][1][1] = templeInfos.get(1).midScore;
            dp[1][2][2] = templeInfos.get(1).rightScore;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<TempleInfo> templeInfos = new ArrayList<>();
        templeInfos.add(new TempleInfo(-1, -1, -1));
        for (int i = 0; i < N; i++) {
            templeInfos.add(new TempleInfo(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        new Solver(N, templeInfos).solve();
    }

    private static class TempleInfo {

        int leftScore;
        int midScore;
        int rightScore;

        public TempleInfo(
            int l,
            int m,
            int r
        ) {
            this.leftScore = l;
            this.midScore = m;
            this.rightScore = r;
        }
    }
}
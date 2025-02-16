import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int templeMaxFloor;
        List<TempleInfo> templeInfos;

        int[][] dp;


        public Solver(
            int N,
            List<TempleInfo> templeInfos
        ) {
            this.templeMaxFloor = N;
            this.templeInfos = templeInfos;
            this.dp = new int[N + 1][3];
        }

        public void solve() {
            initDP();
            calcDP();
            printDP();
        }

        private void printDP() {
            int answer = 0;
            for (int i = 0; i <= 2; i++) {
                answer = Math.max(dp[templeMaxFloor][i], answer);
            }

            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 2; i <= templeMaxFloor; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (j == 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][1] + templeInfos.get(i).leftScore);
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][2] + templeInfos.get(i).leftScore);
                    }
                    if (j == 1) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][0] + templeInfos.get(i).midScore);
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][2] + templeInfos.get(i).midScore);
                    }
                    if (j == 2) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][0] + templeInfos.get(i).rightScore);
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][1] + templeInfos.get(i).rightScore);
                    }
                }
            }
        }

        private void initDP() {
            TempleInfo firstTemple = templeInfos.get(1);
            dp[1][0] = firstTemple.leftScore;
            dp[1][1] = firstTemple.midScore;
            dp[1][2] = firstTemple.rightScore;
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
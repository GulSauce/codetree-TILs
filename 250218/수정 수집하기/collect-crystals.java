import java.util.Scanner;

public class Main {

    public static class Solver {

        int crystalDropInfoIndex;
        int maxMoveCount;
        final int LEFT_INDEX = 0;
        final int RIGHT_INDEX = 1;

        String crystalInfo;

        int[][][] dp;

        public Solver(
            int N,
            int K,
            String crystalInfo
        ) {
            this.crystalDropInfoIndex = N - 1;
            this.maxMoveCount = K;
            this.crystalInfo = crystalInfo;
            this.dp = new int[N + 1][K + 1][2];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int j = 0; j <= maxMoveCount; j++) {
                for (int k = 0; k <= 1; k++) {
                    answer = Math.max(answer, dp[crystalDropInfoIndex][j][k]);
                }
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 1; i <= crystalDropInfoIndex; i++) {
                char currentCrystalDrop = crystalInfo.charAt(i);
                for (int j = 0; j <= maxMoveCount; j++) {
                    for (int k = 0; k <= 1; k++) {
                        if (k == LEFT_INDEX) {
                            if (currentCrystalDrop == 'L') {
                                dp[i][j][LEFT_INDEX] = Math.max(dp[i][j][LEFT_INDEX],
                                    dp[i - 1][j][LEFT_INDEX] + 1);
                            }
                            if (currentCrystalDrop == 'R') {
                                if (j < maxMoveCount) {
                                    dp[i][j + 1][RIGHT_INDEX] = Math.max(dp[i][j + 1][RIGHT_INDEX],
                                        dp[i - 1][j][LEFT_INDEX] + 1);
                                }
                                dp[i][j][LEFT_INDEX] = Math.max(dp[i][j][LEFT_INDEX],
                                    dp[i - 1][j][LEFT_INDEX]);
                            }
                        }
                        if (k == RIGHT_INDEX) {
                            if (currentCrystalDrop == 'L') {
                                if (j < maxMoveCount) {
                                    dp[i][j + 1][LEFT_INDEX] = Math.max(dp[i][j + 1][LEFT_INDEX],
                                        dp[i - 1][j][RIGHT_INDEX] + 1);
                                }
                                dp[i][j][RIGHT_INDEX] = Math.max(dp[i][j][RIGHT_INDEX],
                                    dp[i - 1][j][RIGHT_INDEX]);
                            }
                            if (currentCrystalDrop == 'R') {
                                dp[i][j][RIGHT_INDEX] = Math.max(dp[i][j][RIGHT_INDEX],
                                    dp[i - 1][j][RIGHT_INDEX] + 1);
                            }
                        }
                    }
                }
            }
        }

        private void initDP() {
            if (crystalInfo.charAt(0) == 'L') {
                dp[0][0][0]++;
            } else {
                dp[0][1][1]++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        String crystalDropInfo = sc.next();
        new Main.Solver(N, K, crystalDropInfo).solve();
    }
}
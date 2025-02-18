import java.util.Scanner;

public class Main {

    private static class Solver {

        int templeScoresIndex;
        int roomCount;

        int[][] dp;
        int[][] templeScores;

        public Solver(
            int N,
            int M,
            int[][] templeScores
        ) {
            this.templeScoresIndex = N;
            this.roomCount = M;
            this.templeScores = templeScores;
            this.dp = new int[N + 1][M + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int i = 1; i <= roomCount; i++) {
                answer = Math.max(answer, dp[templeScoresIndex][i]);
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 2; i <= templeScoresIndex; i++) {
                for (int j = 1; j <= roomCount; j++) {
                    for (int k = 1; k <= roomCount; k++) {
                        if (j == k) {
                            continue;
                        }
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + templeScores[i][j]);
                    }
                }
            }
        }

        private void initDP() {
            for (int i = 1; i <= roomCount; i++) {
                dp[1][i] = templeScores[1][i];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] templeScores = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                templeScores[i][j] = sc.nextInt();
            }
        }
        new Solver(N, M, templeScores).solve();
    }
}
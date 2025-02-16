import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int clothInfosIndex;
        int endDay;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<ClothInfo> clothInfos;

        int[][] dp;


        public Solver(
            int N,
            int M,
            List<ClothInfo> clothInfos
        ) {
            this.clothInfosIndex = N - 1;
            this.endDay = M;
            this.clothInfos = clothInfos;
            this.dp = new int[M + 1][N];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printDP() {
            for (int[] array : dp) {
                for (int value : array) {
                    System.out.printf("%d ", value);
                }
                System.out.println();
            }
        }

        private void printAnswer() {
            int answer = 0;
            for (int i = 0; i <= clothInfosIndex; i++) {
                answer = Math.max(answer, dp[endDay][i]);
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 2; i <= endDay; i++) {
                for (int j = 0; j <= clothInfosIndex; j++) {
                    ClothInfo clothInfo = clothInfos.get(j);
                    if (i < clothInfo.start || clothInfo.end < i) {
                        continue;
                    }
                    for (int k = 0; k <= clothInfosIndex; k++) {
                        if (dp[i - 1][k] == NOT_ALLOCATED) {
                            continue;
                        }
                        dp[i][j] = Math.max(
                            dp[i - 1][k] + Math.abs(clothInfos.get(k).score - clothInfo.score),
                            dp[i][j]);
                    }
                }
            }
        }

        private void initDP() {
            for (int[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            for (int i = 0; i <= clothInfosIndex; i++) {
                ClothInfo clothInfo = clothInfos.get(i);
                if (1 < clothInfo.start) {
                    continue;
                }
                dp[1][i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<ClothInfo> clothInfos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            clothInfos.add(new ClothInfo(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        new Main.Solver(N, M, clothInfos).solve();
    }

    private static class ClothInfo {

        int start;
        int end;
        int score;

        public ClothInfo(
            int s,
            int e,
            int v
        ) {
            this.start = s;
            this.end = e;
            this.score = v;
        }
    }
}
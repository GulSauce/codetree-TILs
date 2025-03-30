import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int AIndex;
        int BIndex;

        List<Integer> A;
        List<Integer> B;

        int[][] dp;
        int[][] firstValueOfLCS;
        Coordinate[][] sourceOfDP;

        public Solver(
            int N,
            int M,
            List<Integer> A,
            List<Integer> B
        ) {
            this.AIndex = N - 1;
            this.BIndex = M - 1;
            this.A = A;
            this.B = B;
            this.dp = new int[N][M];
            this.firstValueOfLCS = new int[N][M];
            this.sourceOfDP = new Coordinate[N][M];
        }

        public void solve() {
            reverseList();
            initSourceOfDP();
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int y = AIndex;
            int x = BIndex;
            while (true) {
                if (y == 0 && x == 0) {
                    if (isEqual(A.get(0), B.get(0))) {
                        System.out.printf("%d ", A.get(0));
                    }
                    break;
                }
                if (y == -1 || x == -1) {
                    break;
                }
                if (isEqual(A.get(y), B.get(x))) {
                    System.out.printf("%d ", A.get(y));
                }
                int nextY = sourceOfDP[y][x].y;
                int nextX = sourceOfDP[y][x].x;
                y = nextY;
                x = nextX;
            }
        }

        private void calcDP() {
            for (int i = 1; i <= AIndex; i++) {
                for (int j = 1; j <= BIndex; j++) {
                    if (isEqual(A.get(i), B.get(j))) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        firstValueOfLCS[i][j] = A.get(i);
                        sourceOfDP[i][j] = new Coordinate(i - 1, j - 1);
                        continue;
                    }
                    if (dp[i - 1][j] < dp[i][j - 1]) {
                        dp[i][j] = dp[i][j - 1];
                        firstValueOfLCS[i][j] = firstValueOfLCS[i][j - 1];
                        sourceOfDP[i][j] = new Coordinate(i, j - 1);
                        continue;
                    }
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        firstValueOfLCS[i][j] = firstValueOfLCS[i - 1][j];
                        sourceOfDP[i][j] = new Coordinate(i - 1, j);
                        continue;
                    }
                    if (firstValueOfLCS[i - 1][j] < firstValueOfLCS[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        firstValueOfLCS[i][j] = firstValueOfLCS[i - 1][j];
                        sourceOfDP[i][j] = new Coordinate(i - 1, j);
                        continue;
                    }
                    if (firstValueOfLCS[i][j - 1] <= firstValueOfLCS[i - 1][j]) {
                        dp[i][j] = dp[i][j - 1];
                        firstValueOfLCS[i][j] = firstValueOfLCS[i][j - 1];
                        sourceOfDP[i][j] = new Coordinate(i, j - 1);
                    }
                }
            }
        }

        private void initDP() {
            if (isEqual(A.get(0), B.get(0))) {
                dp[0][0] = 1;
                firstValueOfLCS[0][0] = A.get(0);
            }

            for (int i = 1; i <= AIndex; i++) {
                if (isEqual(A.get(i), B.get(0))) {
                    dp[i][0] = 1;
                    firstValueOfLCS[i][0] = A.get(i);
                    continue;
                }
                dp[i][0] = dp[i - 1][0];
                firstValueOfLCS[i][0] = firstValueOfLCS[i - 1][0];
                sourceOfDP[i][0] = new Coordinate(i - 1, 0);
            }

            for (int i = 1; i <= BIndex; i++) {
                if (isEqual(A.get(0), B.get(i))) {
                    dp[0][i] = 1;
                    firstValueOfLCS[0][i] = B.get(i);
                    continue;
                }
                dp[0][i] = dp[0][i - 1];
                firstValueOfLCS[0][i] = firstValueOfLCS[0][i - 1];
                sourceOfDP[0][i] = new Coordinate(0, i - 1);
            }
        }

        private void initSourceOfDP() {
            for (Coordinate[] array : sourceOfDP) {
                Arrays.fill(array, new Coordinate(-1, -1));
            }
        }

        private void reverseList() {
            Collections.reverse(A);
            Collections.reverse(B);
        }

        private boolean isEqual(Integer A, Integer B) {
            return A.equals(B);
        }
    }

    private static class Coordinate {

        int y;
        int x;

        public Coordinate(
            int y,
            int x
        ) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Integer> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }
        List<Integer> B = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            B.add(sc.nextInt());
        }
        new Main.Solver(N, M, A, B).solve();
    }
}

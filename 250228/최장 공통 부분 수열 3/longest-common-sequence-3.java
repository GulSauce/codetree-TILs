import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static class Solver {

        int AIndex;
        int BIndex;
        List<Integer> A;
        List<Integer> B;

        int[][] dp;

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
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int i = A.size() - 1;
            int j = B.size() - 1;
            Stack<Integer> numbers = new Stack<>();
            while (i >= 0 && j >= 0) {
                if (A.get(i) == B.get(j)) {
                    numbers.add(A.get(i));
                    i--;
                    j--;
                    continue;
                }
                if (i == 0) {
                    j--;
                    continue;
                }
                if (j == 0) {
                    i--;
                    continue;
                }
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                    continue;
                }
                j--;
            }
            while (!numbers.isEmpty()) {
                System.out.printf("%d ", numbers.pop());
            }
        }

        private void calcDP() {
            for (int i = 1; i <= AIndex; i++) {
                for (int j = 1; j <= BIndex; j++) {
                    if (A.get(i) == B.get(j)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        private void initDP() {
            if (A.get(0) == B.get(0)) {
                dp[0][0] = 1;
            }

            for (int i = 1; i <= AIndex; i++) {
                if (A.get(i) == B.get(0)) {
                    dp[i][0] = 1;
                    continue;
                }
                dp[i][0] = dp[i - 1][0];
            }

            for (int i = 1; i <= BIndex; i++) {
                if (A.get(0) == B.get(i)) {
                    dp[0][i] = 1;
                    continue;
                }
                dp[0][i] = dp[0][i - 1];
            }
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
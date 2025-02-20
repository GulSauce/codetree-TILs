import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int stoneIndex;
        int destStoneSum;
        int destSequenceCount;

        List<Integer> answer = new ArrayList<>();

        int[][][] dp = new int[11][201][201];

        public Solver(
            int N,
            int M,
            int K
        ) {
            this.stoneIndex = N - 1;
            this.destStoneSum = M;
            this.destSequenceCount = K;
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int count = 0;
            for (int i = 200; i >= 1; i--) {
                int currentCount = dp[stoneIndex][destStoneSum][i];
                if (currentCount == 0) {
                    continue;
                }
                if (destSequenceCount <= count + currentCount) {
                    answer.add(i);
                    addAnswerNumbers(stoneIndex, destStoneSum - i, destSequenceCount - count, i);
                    break;
                }
                count += currentCount;
            }
            for (int i = answer.size() - 1; i >= 0; i--) {
                System.out.printf("%d ", answer.get(i));
            }
        }

        private void addAnswerNumbers(int prevIndex, int destStoneSum, int destCountSum,
            int prevNumber) {
            if (prevIndex == 0) {
                return;
            }
            int count = 0;
            for (int i = prevNumber; i >= 1; i--) {
                int currentCount = dp[prevIndex - 1][destStoneSum][i];
                if (currentCount == 0) {
                    continue;
                }
                if (destCountSum <= count + currentCount) {
                    answer.add(i);
                    destCountSum = destCountSum - count;
                    addAnswerNumbers(prevIndex - 1, destStoneSum - i, destCountSum - count, i);
                    break;
                }
                count += currentCount;
            }
        }

        private void calcDP() {
            for (int i = 1; i <= stoneIndex; i++) {
                for (int j = 0; j <= 200; j++) {
                    for (int k = 0; k <= 200; k++) {
                        for (int l = 0; l <= 200; l++) {
                            if (destStoneSum < j + k) {
                                continue;
                            }
                            if (k < l) {
                                continue;
                            }
                            dp[i][j + k][k] += dp[i - 1][j][l];
                        }
                    }
                }
            }
        }

        private void initDP() {
            for (int i = 1; i <= 200; i++) {
                if (destStoneSum < i) {
                    continue;
                }
                dp[0][i][i] = 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        new Main.Solver(N, M, K).solve();
    }
}
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static class Solver {

        int magicStoneIndex;
        int targetSum;
        int targetLexOrder;

        int[][][] dp;

        public Solver(
            int N,
            int M,
            int K
        ) {
            this.magicStoneIndex = N - 1;
            this.targetSum = M;
            this.targetLexOrder = K;
            this.dp = new int[N][M + 1][M + 1];
        }


        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            Stack<Integer> numbers = new Stack<>();
            int count = 0;
            int currentTargetSum = targetSum;
            for (int index = magicStoneIndex; index >= 0; index--) {
                for (int lastNumber = currentTargetSum; lastNumber >= 1;
                    lastNumber--) {
                    int curCount = dp[index][lastNumber][currentTargetSum];
                    if (count + curCount < targetLexOrder) {
                        count += curCount;
                        continue;
                    }
                    currentTargetSum -= lastNumber;
                    numbers.add(lastNumber);
                    break;
                }
            }
            while (!numbers.isEmpty()) {
                System.out.printf("%d ", numbers.pop());
            }
        }

        private void calcDP() {
            for (int index = 1; index <= magicStoneIndex; index++) {
                for (int lastNumber = 1; lastNumber <= targetSum; lastNumber++) {
                    for (int sum = 1; sum <= targetSum; sum++) {
                        for (int prevLastNumber = 1; prevLastNumber <= targetSum;
                            prevLastNumber++) {
                            if (lastNumber < prevLastNumber) {
                                continue;
                            }
                            if (sum - lastNumber < 0) {
                                continue;
                            }
                            dp[index][lastNumber][sum] += dp[index - 1][prevLastNumber][sum
                                - lastNumber];
                        }
                    }
                }
            }
        }

        private void initDP() {
            for (int i = 1; i <= targetSum; i++) {
                dp[0][i][i] = 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        new Solver(N, M, K).solve();
    }
}
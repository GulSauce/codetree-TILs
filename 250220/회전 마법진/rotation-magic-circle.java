import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int circleIndex;
        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        String sourceMagicCircle;
        String destMagicCircle;

        int[][] dp = new int[10000][10];

        public Solver(
            int N,
            String sourceMagicCircle,
            String destMagicCircle
        ) {
            this.circleIndex = N - 1;
            this.sourceMagicCircle = sourceMagicCircle;
            this.destMagicCircle = destMagicCircle;
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = NOT_ALLOCATED;
            for (int i = 0; i <= 9; i++) {
                answer = Math.min(dp[circleIndex][i], answer);
            }
            System.out.println(answer);
        }

        private void printDP() {
            for (int[] array : dp) {
                for (int value : array) {
                    System.out.printf("%d ", value);
                }
                System.out.println();
            }
        }

        private void calcDP() {
            for (int i = 1; i <= circleIndex; i++) {
                int source = sourceMagicCircle.charAt(i) - '0';
                int dest = destMagicCircle.charAt(i) - '0';
                for (int j = 0; j <= 9; j++) {
                    for (int k = 0; k <= 9; k++) {
                        int rotateCount = getClockWiseRotateCount((source + j + k) % 10, dest) + j;
                        dp[i][(j + k) % 10] = Math.min(dp[i][(j + k) % 10],
                            dp[i - 1][k] + rotateCount);
                    }
                }
            }
        }

        private void initDP() {
            for (int[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            int source = sourceMagicCircle.charAt(0) - '0';
            int dest = destMagicCircle.charAt(0) - '0';
            for (int i = 0; i <= 9; i++) {
                dp[0][i] = getClockWiseRotateCount((source + i) % 10, dest) + i;
            }
        }

        private int getClockWiseRotateCount(int source, int dest) {
            return (source - dest + 10) % 10;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String sourceMagicCircle = sc.next();
        String destMagicCircle = sc.next();
        new Solver(N, sourceMagicCircle, destMagicCircle).solve();
    }


}
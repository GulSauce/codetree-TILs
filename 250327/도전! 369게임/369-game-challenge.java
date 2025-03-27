import java.util.Scanner;

public class Main {

    private static class Solver {

        int finishNumberLength;
        int answer = 0;
        final int NOT_TIGHT = 0;
        final int TIGHT = 1;
        final int FLAG_COUNT = 2;
        final int MOD = 3;
        final int REMAINDER = 1_000_000_000 + 7;
        int[] numberCountDigitAt;
        int[][][] dp;

        String finishNumber;

        public Solver(
            String N
        ) {
            this.finishNumberLength = N.length();
            this.finishNumber = " " + N;
            this.numberCountDigitAt = new int[N.length() + 1];
            this.dp = new int[N.length() + 1][MOD][FLAG_COUNT];
        }

        public void solve() {
            setNumberCountDigitAt();
            initDP();
            calcDP();
            printAnswer();
        }

        private void setNumberCountDigitAt() {
            numberCountDigitAt[0] = 1;
            for (int i = 1; i <= finishNumberLength; i++) {
                numberCountDigitAt[i] = numberCountDigitAt[i - 1] * 10;
                numberCountDigitAt[i] %= REMAINDER;
            }
        }

        private void printAnswer() {
            answer += dp[finishNumberLength][0][NOT_TIGHT];
            answer %= REMAINDER;
            answer += dp[finishNumberLength][0][TIGHT];
            answer %= REMAINDER;
            answer--;
            answer = Math.floorMod(answer, REMAINDER);
            System.out.println(answer);
        }

        private void calcDP() {
            for (int i = 1; i < finishNumberLength; i++) {
                int nextNum = finishNumber.charAt(i + 1) - '0';
                for (int remain = 0; remain <= 2; remain++) {
                    for (int num = 0; num <= 9; num++) {
                        if (is369(num)) {
                            processNum369(num, nextNum, i, remain);
                            continue;
                        }
                        int nextRemain = (num + remain) % MOD;
                        if (num < nextNum) {
                            dp[i + 1][nextRemain][NOT_TIGHT] += dp[i][remain][NOT_TIGHT];
                            dp[i + 1][nextRemain][NOT_TIGHT] %= REMAINDER;
                            dp[i + 1][nextRemain][NOT_TIGHT] += dp[i][remain][TIGHT];
                            dp[i + 1][nextRemain][NOT_TIGHT] %= REMAINDER;
                        }

                        if (num == nextNum) {
                            dp[i + 1][nextRemain][NOT_TIGHT] += dp[i][remain][NOT_TIGHT];
                            dp[i + 1][nextRemain][NOT_TIGHT] %= REMAINDER;
                            dp[i + 1][nextRemain][TIGHT] += dp[i][remain][TIGHT];
                            dp[i + 1][nextRemain][TIGHT] %= REMAINDER;
                        }

                        if (nextNum < num) {
                            dp[i + 1][nextRemain][NOT_TIGHT] += dp[i][remain][NOT_TIGHT];
                            dp[i + 1][nextRemain][NOT_TIGHT] %= REMAINDER;
                        }
                    }
                }
            }
        }

        private void processNum369(int num, int nextNum, int i, int remain) {
            if (num <= nextNum) {
                answer +=
                    dp[i][remain][NOT_TIGHT] * numberCountDigitAt[finishNumberLength
                        - i - 1];
                answer %= REMAINDER;
                answer +=
                    dp[i][remain][TIGHT] * numberCountDigitAt[finishNumberLength
                        - i - 1];
                answer %= REMAINDER;
            }
            if (nextNum < num) {
                answer +=
                    dp[i][remain][NOT_TIGHT] * numberCountDigitAt[finishNumberLength
                        - i - 1];
                answer %= REMAINDER;
            }
        }

        private void initDP() {
            int nextNum = finishNumber.charAt(1) - '0';
            for (int num = 0; num <= nextNum; num++) {
                if (is369(num)) {
                    answer += numberCountDigitAt[finishNumberLength - 1];
                    answer %= REMAINDER;
                    continue;
                }
                int nextRemain = (num) % MOD;
                if (num < nextNum) {
                    dp[1][nextRemain][0]++;
                }
                if (num == nextNum) {
                    dp[1][nextRemain][1]++;
                }
            }
        }

        private boolean is369(int num) {
            return num == 3 || num == 6 || num == 9;
        }
    }

    public static void main(String[] args) {
        String N;

        Scanner sc = new Scanner(System.in);
        N = sc.next();
        sc.close();

        new Main.Solver(N).solve();
    }
}
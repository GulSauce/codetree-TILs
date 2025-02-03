import java.util.Scanner;

public class Main {

    private static class Solver {

        int numberStringIndex;
        final int R = 1000000007;
        final int NOT_ALLOCATED = 0;

        String number;

        long[] countAfterRemain;
        int[][][] dp;
        int[][][] dp2;

        public Solver(
            String n
        ) {
            this.numberStringIndex = n.length() - 1;
            this.number = getReversStringOf(n);
            this.countAfterRemain = new long[100001];
            this.dp = new int[n.length()][3][2];
            this.dp2 = new int[n.length()][2][2];
        }

        public void solve() {
            setCountAfterRemain();
            initDP();
            calcDP();
            initDP2();
            calcDP2();
            printAnswer();
        }

        private void setCountAfterRemain() {
            countAfterRemain[0] = 10;
            for (int i = 1; i <= 100000; i++) {
                countAfterRemain[i] = countAfterRemain[i - 1] * 10;
                countAfterRemain[i] %= R;
            }
        }

        private String getReversStringOf(String str) {
            StringBuilder reverseStr = new StringBuilder();
            for (int i = numberStringIndex; i >= 0; i--) {
                reverseStr.append(str.charAt(i));
            }
            return reverseStr.toString();
        }

        private void printAnswer() {
            long result = dp[numberStringIndex][0][0] + dp2[numberStringIndex][1][0];
            result--;
            result %= R;
            System.out.println(result);
        }

        private void calcDP() {
            for (int i = 1; i <= numberStringIndex; i++) {
                int currentNumber = number.charAt(i) - '0';
                for (int j = 0; j <= 9; j++) {
                    if (j == 3 || j == 6 || j == 9) {
                        continue;
                    }
                    for (int prevRemain = 0; prevRemain <= 2; prevRemain++) {
                        int remain = (j % 3 + prevRemain % 3) % 3;
                        if (j < currentNumber) {
                            if (dp[i - 1][prevRemain][0] != NOT_ALLOCATED) {
                                dp[i][remain][0] += dp[i - 1][prevRemain][0];
                                dp[i][remain][0] %= R;
                            }
                            if (dp[i - 1][prevRemain][1] != NOT_ALLOCATED) {
                                dp[i][remain][0] += dp[i - 1][prevRemain][1];
                                dp[i][remain][0] %= R;
                            }
                        }
                        if (j == currentNumber) {
                            if (dp[i - 1][prevRemain][0] != NOT_ALLOCATED) {
                                dp[i][remain][0] += dp[i - 1][prevRemain][0];
                                dp[i][remain][0] %= R;
                            }
                            if (dp[i - 1][prevRemain][1] != NOT_ALLOCATED) {
                                dp[i][remain][1] += dp[i - 1][prevRemain][1];
                                dp[i][remain][1] %= R;
                            }
                        }
                        if (currentNumber < j) {
                            if (dp[i - 1][prevRemain][0] != NOT_ALLOCATED) {
                                dp[i][remain][1] += dp[i - 1][prevRemain][0];
                                dp[i][remain][1] %= R;
                            }
                            if (dp[i - 1][prevRemain][1] != NOT_ALLOCATED) {
                                dp[i][remain][1] += dp[i - 1][prevRemain][1];
                                dp[i][remain][1] %= R;
                            }
                        }
                    }
                }
            }
        }

        private void initDP() {
            int currentNumber = number.charAt(0) - '0';
            for (int i = 0; i <= 9; i++) {
                if (i == 3 || i == 6 || i == 9) {
                    continue;
                }
                if (i <= currentNumber) {
                    dp[0][i % 3][0]++;
                    continue;
                }
                dp[0][i % 3][1]++;
            }
        }

        private void calcDP2() {
            for (int i = 1; i <= numberStringIndex; i++) {
                int currentNumber = number.charAt(i) - '0';
                for (int j = 0; j <= 9; j++) {
                    if (j == 3 || j == 6 || j == 9) {
                        if (j < currentNumber) {
                            dp2[i][1][0] += dp2[i - 1][0][0];
                            dp2[i][1][0] += dp2[i - 1][1][0];
                            dp2[i][1][0] += dp2[i - 1][0][1];
                            dp2[i][1][0] += dp2[i - 1][1][1];
                        }
                        if (j == currentNumber) {
                            dp2[i][1][0] += dp2[i - 1][0][0];
                            dp2[i][1][0] += dp2[i - 1][1][0];
                            dp2[i][1][1] += dp2[i - 1][0][1];
                            dp2[i][1][1] += dp2[i - 1][1][1];
                        }
                        if (currentNumber < j) {
                            dp2[i][1][1] += dp2[i - 1][0][0];
                            dp2[i][1][1] += dp2[i - 1][1][0];
                            dp2[i][1][1] += dp2[i - 1][0][1];
                            dp2[i][1][1] += dp2[i - 1][1][1];
                        }
                        dp2[i][0][0] %= R;
                        dp2[i][0][1] %= R;
                        dp2[i][1][0] %= R;
                        dp2[i][1][1] %= R;
                        continue;
                    }
                    if (j < currentNumber) {
                        dp2[i][0][0] += dp2[i - 1][0][0];
                        dp2[i][1][0] += dp2[i - 1][1][0];
                        dp2[i][0][0] += dp2[i - 1][0][1];
                        dp2[i][1][0] += dp2[i - 1][1][1];
                    }
                    if (j == currentNumber) {
                        dp2[i][0][0] += dp2[i - 1][0][0];
                        dp2[i][1][0] += dp2[i - 1][1][0];
                        dp2[i][0][1] += dp2[i - 1][0][1];
                        dp2[i][1][1] += dp2[i - 1][1][1];
                    }
                    if (currentNumber < j) {
                        dp2[i][0][1] += dp2[i - 1][0][0];
                        dp2[i][1][1] += dp2[i - 1][1][0];
                        dp2[i][0][1] += dp2[i - 1][0][1];
                        dp2[i][1][1] += dp2[i - 1][1][1];
                    }
                    dp2[i][0][0] %= R;
                    dp2[i][0][1] %= R;
                    dp2[i][1][0] %= R;
                    dp2[i][1][1] %= R;
                }
            }
        }

        private void initDP2() {
            int currentNumber = number.charAt(0) - '0';
            for (int i = 0; i <= 9; i++) {
                if (i == 3 || i == 6 || i == 9) {
                    if (i <= currentNumber) {
                        dp2[0][1][0]++;
                    }
                    if (currentNumber < i) {
                        dp2[0][1][1]++;
                    }
                    continue;
                }
                if (i <= currentNumber) {
                    dp2[0][0][0]++;
                }
                if (currentNumber < i) {
                    dp2[0][0][1]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        new Main.Solver(n).solve();
    }
}
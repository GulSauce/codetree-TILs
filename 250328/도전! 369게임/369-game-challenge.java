import java.util.Scanner;

public class Main {

    private static class Solver {


        int finishNumberLength;
        final int NOT_EXIST_369 = 0;
        final int EXIST_369 = 1;
        final int UNDER_NUMBER = 0;
        final int SAME_NUMBER = 1;
        final int FLAG_369_COUNT = 2;
        final int FLAG_NUMBER_COUNT = 2;
        final int MOD = 3;
        final int REMAINDER = 1_000_000_000 + 7;

        long[] numberCountDigitAt;
        long[][][][] dp;

        String finishNumber;

        public Solver(
            String N
        ) {
            this.finishNumberLength = N.length();
            this.finishNumber = " " + N;
            this.numberCountDigitAt = new long[N.length() + 1];
            this.dp = new long[N.length() + 1][MOD][FLAG_369_COUNT][FLAG_NUMBER_COUNT];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            long answer = 0;
            for (int mod = 0; mod <= 2; mod++) {
                for (int flag369 = 0; flag369 <= 1; flag369++) {
                    for (int numberFlag = 0; numberFlag <= 1; numberFlag++) {
                        if (flag369 == NOT_EXIST_369 && mod != 0) {
                            continue;
                        }
                        answer += dp[finishNumberLength][mod][flag369][numberFlag];
                        answer %= REMAINDER;
                    }
                }
            }
            System.out.println(answer - 1);
        }

        private void calcDP() {
            for (int i = 0; i < finishNumberLength; i++) {
                for (int mod = 0; mod <= 2; mod++) {
                    for (int flag369 = 0; flag369 <= 1; flag369++) {
                        for (int numberFlag = 0; numberFlag <= 1; numberFlag++) {
                            int limit =
                                numberFlag == UNDER_NUMBER ? 9 : finishNumber.charAt(i + 1) - '0';
                            for (int number = 0; number <= limit; number++) {
                                int nextMod = (mod + number) % 3;

                                int nextNumberFlag;
                                if (numberFlag == UNDER_NUMBER) {
                                    nextNumberFlag = UNDER_NUMBER;
                                } else if (number < limit) {
                                    nextNumberFlag = UNDER_NUMBER;
                                } else {
                                    nextNumberFlag = SAME_NUMBER;
                                }

                                int next369Flag;
                                if (flag369 == EXIST_369) {
                                    next369Flag = EXIST_369;
                                } else if (is369(number)) {
                                    next369Flag = EXIST_369;
                                } else {
                                    next369Flag = NOT_EXIST_369;
                                }

                                dp[i + 1][nextMod][next369Flag][nextNumberFlag]
                                    += dp[i][mod][flag369][numberFlag];
                                dp[i + 1][nextMod][next369Flag][nextNumberFlag]
                                    %= REMAINDER;
                            }
                        }
                    }
                }
            }
        }

        private boolean is369(int number) {
            return number == 3 || number == 6 || number == 9;
        }

        private void initDP() {
            dp[0][0][NOT_EXIST_369][SAME_NUMBER] = 1;
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
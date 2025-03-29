import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, K;
        String moveString;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        moveString = sc.next();
        sc.close();

        new Solver(N, K, moveString).solve();
    }
}

class Solver {

    int gemPosStringIndex;
    int maxMoveCount;

    final int LEFT = 0;
    final int RIGHT = 1;
    final int DIRECTION_COUNT = 2;
    final int NOT_ALLOCATED = Integer.MIN_VALUE;

    int[][][] dp;
    String gemPosString;

    public Solver(
        int N,
        int K,
        String gemPosString
    ) {
        this.gemPosStringIndex = N;
        this.maxMoveCount = K;
        this.gemPosString = " " + gemPosString;
        this.dp = new int[N + 1][K + 1][DIRECTION_COUNT];
    }

    public void solve() {
        initDP();
        calcDP();
        printAnswer();
    }

    private void printAnswer() {
        int answer = 0;
        for (int moveCount = 0; moveCount <= maxMoveCount; moveCount++) {
            answer = Math.max(answer, dp[gemPosStringIndex][moveCount][LEFT]);
            answer = Math.max(answer, dp[gemPosStringIndex][moveCount][RIGHT]);
        }
        System.out.println(answer);
    }

    private void calcDP() {
        for (int i = 1; i <= gemPosStringIndex; i++) {
            char pos = gemPosString.charAt(i);
            for (int moveCount = 0; moveCount <= maxMoveCount; moveCount++) {
                if (pos == 'L') {
                    if (dp[i - 1][moveCount][LEFT] != NOT_ALLOCATED) {
                        dp[i][moveCount][LEFT] = Math.max(dp[i][moveCount][LEFT],
                            dp[i - 1][moveCount][LEFT] + 1);
                    }
                    if (dp[i - 1][moveCount][RIGHT] != NOT_ALLOCATED) {
                        dp[i][moveCount][RIGHT] = Math.max(dp[i][moveCount][RIGHT],
                            dp[i - 1][moveCount][RIGHT]);

                        if (moveCount < maxMoveCount) {
                            dp[i][moveCount + 1][LEFT] = Math.max(dp[i][moveCount + 1][LEFT],
                                dp[i - 1][moveCount][RIGHT] + 1);
                        }
                    }
                }
                if (pos == 'R') {
                    if (dp[i - 1][moveCount][RIGHT] != NOT_ALLOCATED) {
                        dp[i][moveCount][RIGHT] = Math.max(dp[i][moveCount][RIGHT],
                            dp[i - 1][moveCount][RIGHT] + 1);
                    }
                    if (dp[i - 1][moveCount][LEFT] != NOT_ALLOCATED) {
                        dp[i][moveCount][LEFT] = Math.max(dp[i][moveCount][LEFT],
                            dp[i - 1][moveCount][LEFT]);
                        if (moveCount < maxMoveCount) {
                            dp[i][moveCount + 1][RIGHT] = Math.max(dp[i][moveCount + 1][RIGHT],
                                dp[i - 1][moveCount][LEFT] + 1);
                        }
                    }
                }
            }
        }
    }

    private void initDP() {
        for (int[][] arrays : dp) {
            for (int[] array : arrays) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
        }
        dp[0][0][LEFT] = 0;
    }
}
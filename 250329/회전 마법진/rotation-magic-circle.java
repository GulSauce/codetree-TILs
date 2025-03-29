import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        String initCircle;
        String targetCircle;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        initCircle = sc.next();
        targetCircle = sc.next();
        sc.close();

        new Solver(N, initCircle, targetCircle).solve();
    }
}

class Solver {

    int circleIndex;
    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    final int REMAINDER = 10;

    String initCircle;
    String targetCircle;

    int[][] dp;

    public Solver(
        int N,
        String initCircle,
        String targetCircle
    ) {
        this.circleIndex = N;
        this.initCircle = " " + initCircle;
        this.targetCircle = " " + targetCircle;
        this.dp = new int[N + 1][REMAINDER];
    }

    public void solve() {
        initDP();
        calcDP();
        printAnswer();
    }

    private void printAnswer() {
        int answer = Integer.MAX_VALUE;
        for (int counterRotate = 0; counterRotate < REMAINDER; counterRotate++) {
            answer = Math.min(answer, dp[circleIndex][counterRotate]);
        }
        System.out.println(answer);
    }

    private void calcDP() {
        for (int i = 1; i <= circleIndex; i++) {
            int curNumber = initCircle.charAt(i) - '0';
            int targetNumber = targetCircle.charAt(i) - '0';
            for (int counterRotate = 0; counterRotate < REMAINDER; counterRotate++) {
                for (int prevCounterRotate = 0; prevCounterRotate < REMAINDER;
                    prevCounterRotate++) {
                    if (dp[i - 1][prevCounterRotate] == NOT_ALLOCATED) {
                        continue;
                    }
                    int realCurNumber = (curNumber + prevCounterRotate) % REMAINDER;
                    int numberAfterCountRotate = (realCurNumber + counterRotate) % REMAINDER;
                    int rotateCount = Math.floorMod(numberAfterCountRotate - targetNumber,
                        REMAINDER);
                    dp[i][(prevCounterRotate + counterRotate) % REMAINDER] = Math.min(
                        dp[i][(prevCounterRotate + counterRotate) % REMAINDER],
                        dp[i - 1][prevCounterRotate] + rotateCount + counterRotate);
                }
            }
        }
    }

    private void initDP() {
        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        dp[0][0] = 0;
    }
}
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int N, M, K;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        sc.close();

        new Solver(N, M, K).solve();
    }
}

class Solver {

    int gemCount;
    int targetSum;
    int targetLex;
    final int NOT_ALLOCATED = 0;
    int[][][] dp;

    public Solver(
        int N,
        int M,
        int K
    ) {
        this.gemCount = N;
        this.targetSum = M;
        this.targetLex = K;
        this.dp = new int[N + 1][M + 1][M + 1];
    }

    public void solve() {
        initDP();
        calcDP();
        printAnswer();
    }

    private void printAnswer() {
        int curLex = 0;
        int curMaxNum = targetSum;
        int curTargetSum = targetSum;
        Stack<Integer> answer = new Stack<>();
        for (int i = gemCount; i >= 1; i--) {
            for (int curNum = curMaxNum; curNum >= 1; curNum--) {
                if (dp[i][curNum][curTargetSum] == NOT_ALLOCATED) {
                    continue;
                }
                if (targetLex <= curLex + dp[i][curNum][curTargetSum]) {
                    curTargetSum = curTargetSum - curNum;
                    curMaxNum = curNum;
                    answer.add(curNum);
                    break;
                }
                curLex += dp[i][curNum][curTargetSum];
            }
        }
        while (!answer.isEmpty()) {
            System.out.print(answer.pop() + " ");
        }
    }

    private void calcDP() {
        for (int i = 2; i <= gemCount; i++) {
            for (int curNumber = 1; curNumber <= targetSum; curNumber++) {
                for (int sum = 1; sum <= targetSum; sum++) {
                    for (int prevNumber = 1; prevNumber <= curNumber; prevNumber++) {
                        if (sum - curNumber < 0) {
                            continue;
                        }
                        if (dp[i - 1][prevNumber][sum - curNumber] == NOT_ALLOCATED) {
                            continue;
                        }
                        dp[i][curNumber][sum] += dp[i - 1][prevNumber][sum - curNumber];
                    }
                }
            }
        }
    }

    private void initDP() {
        for (int curNumber = 1; curNumber <= targetSum; curNumber++) {
            dp[1][curNumber][curNumber] = 1;
        }
    }
}
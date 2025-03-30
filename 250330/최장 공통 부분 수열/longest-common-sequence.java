import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String A;
        String B;

        Scanner sc = new Scanner(System.in);
        A = sc.next();
        B = sc.next();
        sc.close();

        new Solver(A, B).solve();
    }
}

class Solver {

    String A;
    String B;

    int aLength;
    int bLength;
    final int NOT_ALLOCATED = Integer.MIN_VALUE;
    int[][] dp;

    public Solver(
        String A,
        String B
    ) {
        this.A = " " + A;
        this.B = " " + B;
        this.aLength = A.length();
        this.bLength = B.length();
        this.dp = new int[A.length() + 1][B.length() + 1];
    }

    public void solve() {
        initDP();
        calcDP();
        printAnswer();
    }

    private void printAnswer() {
        System.out.println(dp[aLength][bLength]);
    }

    private void calcDP() {
        for (int aIndex = 1; aIndex <= aLength; aIndex++) {
            for (int bIndex = 1; bIndex <= bLength; bIndex++) {
                if (A.charAt(aIndex) == B.charAt(bIndex)
                    && dp[aIndex - 1][bIndex - 1] != NOT_ALLOCATED) {
                    dp[aIndex][bIndex] = dp[aIndex - 1][bIndex - 1] + 1;
                    continue;
                }
                if (dp[aIndex - 1][bIndex] != NOT_ALLOCATED) {
                    dp[aIndex][bIndex] = Math.max(dp[aIndex][bIndex], dp[aIndex - 1][bIndex]);
                }
                if (dp[aIndex][bIndex - 1] != NOT_ALLOCATED) {
                    dp[aIndex][bIndex] = Math.max(dp[aIndex][bIndex], dp[aIndex][bIndex - 1]);
                }
            }
        }
    }

    private void initDP() {
        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        dp[0][0] = 0;
        for (int aIndex = 1; aIndex <= aLength; aIndex++) {
            dp[aIndex][0] = 0;
        }
        for (int bIndex = 1; bIndex <= bLength; bIndex++) {
            dp[0][bIndex] = 0;
        }
    }
}
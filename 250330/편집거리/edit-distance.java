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
        for (int i = 1; i <= aLength; i++) {
            for (int j = 1; j <= bLength; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                int deleteDist = dp[i - 1][j] + 1;
                int insertDist = dp[i][j - 1] + 1;
                int changeDist = dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(deleteDist, Math.max(insertDist, changeDist));
            }
        }
    }

    private void initDP() {
        dp[0][0] = 0;
        for (int i = 1; i <= aLength; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i <= bLength; i++) {
            dp[0][i] = dp[0][i] + 1;
        }
    }
}
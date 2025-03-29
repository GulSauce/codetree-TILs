import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();

        new Solver(N).solve();
    }
}

class Solver {

    int targetWidth;
    final int REMAINDER = 1_000_000_007;

    long[] dp;

    public Solver(
        int N
    ) {
        this.targetWidth = N;
        this.dp = new long[N + 1];
    }

    public void solve() {
        if (targetWidth == 1) {
            System.out.println(2);
            return;
        }
        initDP();
        calcDP();
        printAnswer();
    }

    private void printAnswer() {
        System.out.println(dp[targetWidth]);
    }

    private void initDP() {
        dp[1] = 2;
        dp[2] = 7;
    }

    private void calcDP() {
        for (int width = 3; width <= targetWidth; width++) {
            long count = 0;

            count += 2;

            count += dp[width - 1] * 2;
            count %= REMAINDER;

            count += dp[width - 2] * 3;
            count %= REMAINDER;

            for (int originWidth = 3; originWidth <= width - 1; originWidth++) {
                count += dp[width - originWidth] * 2;
                count %= REMAINDER;
            }

            dp[width] = count;
        }
    }
}
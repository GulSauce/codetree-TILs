import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        new Solver(N).solve();
    }
}

class Solver {

    final int R = 10_007;
    final int FULL = (1 << 10) - 1;
    int[][][] dp;
    int digit;

    public Solver(int digit) {
        this.digit = digit;
        this.dp = new int[11][digit + 1][FULL + 1];
    }

    public void solve() {
        for (int number = 1; number <= 9; number++) {
            dp[number][1][1 << number] = 1;
        }
        for (int bitMask = 1; bitMask <= FULL; bitMask++) {
            for (int i = 1; i < digit; i++) {
                for (int number = 0; number <= 9; number++) {
                    if (number - 1 >= 0) {
                        dp[number - 1][i + 1][bitMask | 1 << number - 1]
                            += dp[number][i][bitMask];
                        dp[number - 1][i + 1][bitMask | 1 << number - 1]
                            %= R;
                    }
                    if (number + 1 < 10) {
                        dp[number + 1][i + 1][bitMask | 1 << number + 1]
                            += dp[number][i][bitMask];
                        dp[number + 1][i + 1][bitMask | 1 << number + 1]
                            %= R;
                    }
                }
            }
        }
        int answer = 0;
        for (int number = 0; number <= 9; number++) {
            answer += dp[number][digit][FULL];
            answer %= R;
        }
        System.out.println(answer);
    }
}
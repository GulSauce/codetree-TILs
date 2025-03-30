import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String S;
        String P;

        Scanner sc = new Scanner(System.in);
        S = sc.next();
        P = sc.next();
        sc.close();

        new Solver(S, P).solve();
    }
}

class Solver {

    String string;
    String pattern;

    int stringLength;
    int patternLength;
    boolean[][] dp;

    public Solver(
        String S,
        String P
    ) {
        this.string = " " + S;
        this.pattern = " " + P;
        this.stringLength = S.length();
        this.patternLength = P.length();
        this.dp = new boolean[S.length() + 1][P.length() + 1];
    }

    public void solve() {
        initDP();
        calcDP();
        printAnswer();
    }

    private void printAnswer() {
        System.out.println(dp[stringLength][patternLength]);
    }

    private void calcDP() {
        for (int i = 1; i <= stringLength; i++) {
            for (int j = 1; j <= patternLength; j++) {
                char curChar = string.charAt(i);
                char curPattern = pattern.charAt(j);
                if (curPattern == '*') {
                    char prevPattern = pattern.charAt(j - 1);
                    if (prevPattern == '.' || curChar == prevPattern) {
                        dp[i][j] = true & dp[i - 1][j];
                        continue;
                    }
                    dp[i][j] = true & dp[i][j - 2];
                    continue;
                }
                if (curPattern == '.' || curChar == curPattern) {
                    dp[i][j] = true & dp[i - 1][j - 1];
                    continue;
                }
                dp[i][j] = false;
            }
        }
    }

    private void initDP() {
        dp[0][0] = true;
        for (int i = 1; i <= stringLength; i++) {
            dp[i][0] = false;
        }
        for (int i = 1; i <= patternLength; i++) {
            if (pattern.charAt(i) == '*') {
                dp[0][i] = true & dp[0][i - 2];
                continue;
            }
            dp[0][i] = false;
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        String str;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        new Solver(str).solve();
    }
}

class Solver {

    String str;

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int[][] dp;

    public Solver(String str) {
        this.str = str;
        this.dp = new int[str.length()][str.length()];
    }

    public void solve() {
        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        for (int i = 0; i < str.length(); i++) {
            dp[i][i] = 0;
        }
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) == str.charAt(i)) {
                dp[i - 1][i] = 0;
            } else {
                dp[i - 1][i] = 1;
            }
        }
        for (int length = 3; length <= str.length(); length++) {
            for (int i = 0; i < str.length(); i++) {
                int j = i + length - 1;
                if (j >= str.length()) {
                    continue;
                }
                if (str.charAt(i) != str.charAt(j)) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        Math.min(dp[i][j - 1], dp[i + 1][j]) + 1
                    );
                } else {
                    dp[i][j] = dp[i + 1][j - 1];
                }
            }
        }

        System.out.println(dp[0][str.length() - 1]);
    }
}
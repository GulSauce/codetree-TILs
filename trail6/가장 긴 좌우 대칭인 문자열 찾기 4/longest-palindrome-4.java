import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    boolean[][] dp;

    public Solver(String str) {
        this.str = str;
        this.dp = new boolean[str.length()][str.length()];
    }

    public void solve() {
        for (int i = 0; i < str.length(); i++) {
            dp[i][i] = true;
        }

        for (int length = 2; length <= str.length(); length++) {
            for (int i = 0; i < str.length(); i++) {
                int j = i + length - 1;
                if (j >= str.length()) {
                    continue;
                }
                if (str.charAt(i) != str.charAt(j)) {
                    continue;
                }
                if (length == 2) {
                    dp[i][j] = true;
                } else if (dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                if (dp[i][j]) {
                    answer = Math.max(answer, j - i + 1);
                }
            }
        }
        System.out.println(answer);
    }
}
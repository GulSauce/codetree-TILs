import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String T;
        int m;
        List<String> patterns = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = st.nextToken();
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            patterns.add(st.nextToken());
        }

        new Solver(T, patterns).solve();
    }
}

class Solver {

    String article;
    List<String> patterns;

    boolean[][] isPutable;

    int[] dp;

    public Solver(String article, List<String> patterns) {
        this.article = article;
        this.patterns = patterns;
        this.dp = new int[article.length() + 1];
        this.isPutable = new boolean[article.length() + 1][patterns.size()];
    }

    public void solve() {
        for (int i = 0; i < patterns.size(); i++) {
            kmp(i);
        }
        for (int i = 1; i <= article.length(); i++) {
            dp[i] = dp[i - 1];
            for (int j = 0; j < patterns.size(); j++) {
                String pattern = patterns.get(j);
                if (i - pattern.length() < 0) {
                    continue;
                }
                if (!isPutable[i][j]) {
                    continue;
                }
                dp[i] = Math.max(dp[i], dp[i - pattern.length()] + pattern.length());
            }
        }

        System.out.println(dp[article.length()]);
    }

    private void kmp(int curIndex) {
        String pattern = patterns.get(curIndex);
        int[] preSufLength = new int[pattern.length()];

        int length = 0;
        int i = 1;
        while (true) {
            if (i == pattern.length()) {
                break;
            }
            if (pattern.charAt(length) == pattern.charAt(i)) {
                length++;
                preSufLength[i] = length;
                i++;
            } else if (length == 0) {
                i++;
            } else {
                length = preSufLength[length - 1];
            }
        }

        i = 0;
        int j = 0;
        while (true) {
            if (i == article.length()) {
                break;
            }
            if (article.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    isPutable[i + 1][curIndex] = true;
                    j = preSufLength[j - 1];
                    i++;
                } else {
                    i++;
                    j++;
                }
            } else if (j == 0) {
                i++;
            } else {
                j = preSufLength[j - 1];
            }
        }
    }
}
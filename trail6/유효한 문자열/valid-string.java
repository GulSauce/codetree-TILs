import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String str;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        str = st.nextToken();

        new Solver(str).solve();
    }
}

class Solver {

    final int R = 10_007;
    int[][] dp;
    String str;

    char[] closeLetterArray = {']', '}', ')'};
    char[] openLetterArray = {'[', '{', '('};
    HashSet<Character> closeLetterHashSet = new HashSet<>();
    HashSet<Character> openLetterHashSet = new HashSet<>();

    public Solver(String str) {
        this.str = str;
        this.dp = new int[str.length()][str.length()];
    }

    public void solve() {
        for (char letter : closeLetterArray) {
            closeLetterHashSet.add(letter);
        }
        for (char letter : openLetterArray) {
            openLetterHashSet.add(letter);
        }

        for (int i = 1; i < str.length(); i++) {
            dp[i - 1][i] = getMatchCount(i - 1, i);
        }

        for (int length = 4; length <= str.length(); length++) {
            for (int i = 0; i < str.length(); i++) {
                int j = i + length - 1;
                if (j >= str.length()) {
                    break;
                }
                for (int k = i + 1; k <= j; k += 2) {
                    int innerStart = i + 1;
                    int innerEnd = k - 1;
                    int innerValue = 1;
                    if (innerStart <= innerEnd - 1) {
                        innerValue = dp[innerStart][innerEnd];
                    }

                    int outerStart = k + 1;
                    int outerEnd = j;
                    int outerValue = 1;
                    if (outerStart <= outerEnd - 1) {
                        outerValue = dp[outerStart][outerEnd];
                    }

                    dp[i][j] += (getMatchCount(i, k) * innerValue % R) * outerValue;
                    dp[i][j] %= R;
                }
            }
        }

        System.out.println(dp[0][str.length() - 1]);
    }

    private int getMatchCount(int i, int j) {
        if (str.charAt(i) == '?') {
            if (str.charAt(j) == '?') {
                return 3;
            } else if (closeLetterHashSet.contains(str.charAt(j))) {
                return 1;
            }
        }
        if (str.charAt(j) == '?') {
            if (str.charAt(i) == '?') {
                return 3;
            } else if (openLetterHashSet.contains(str.charAt(i))) {
                return 1;
            }
        }
        if (str.charAt(i) == '{' && str.charAt(j) == '}') {
            return 1;
        }
        if (str.charAt(i) == '[' && str.charAt(j) == ']') {
            return 1;
        }
        if (str.charAt(i) == '(' && str.charAt(j) == ')') {
            return 1;
        }
        return 0;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n; int m;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(-1);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st); m = toInt(st);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) numbers.add(toInt(st));
        new Solver(numbers, m).solve();
    }
    private static int toInt(StringTokenizer st) { return Integer.parseInt(st.nextToken()); }
}
class Solver {
    int removableCount;
    final long NOT_ALLOCATED = Long.MAX_VALUE;
    long[][][] dp;
    List<Integer> numbers;
    public Solver(List<Integer> numbers, int removableCount) {
        this.numbers = numbers; this.removableCount = removableCount;
        this.dp = new long[numbers.size()][numbers.size()][removableCount + 1];
    }
    public void solve() {
        for (long[][] arrays : dp) for (long[] array : arrays) Arrays.fill(array, NOT_ALLOCATED);
        for (int i = 1; i < numbers.size(); i++) { int d=i-1; if(d<=removableCount){ dp[i][0][d]=0; dp[0][i][d]=0; } }
        for (int i = 1; i < numbers.size(); i++) {
            for (int j = 0; j <= i - 1; j++) {
                for (int k = 0; k <= removableCount; k++) {
                    for (int skipCount = 0; skipCount <= removableCount; skipCount++) {
                        if (removableCount < k + skipCount) continue;
                        int shouldAdded = i - 1 - skipCount;
                        if (shouldAdded < 0) continue;
                        if (j < shouldAdded && dp[shouldAdded][j][k] != NOT_ALLOCATED) {
                            long cand = (shouldAdded == 0) ? dp[shouldAdded][j][k] : dp[shouldAdded][j][k] + Math.abs(numbers.get(i) - numbers.get(shouldAdded));
                            dp[i][j][k + skipCount] = Math.min(dp[i][j][k + skipCount], cand);
                        } else if (1 <= shouldAdded && j == shouldAdded) {
                            long value = Long.MAX_VALUE;
                            for (int l = 0; l < shouldAdded; l++) {
                                if (dp[shouldAdded][l][k] == NOT_ALLOCATED) continue;
                                if (l == 0) value = Math.min(value, dp[shouldAdded][l][k]);
                                else value = Math.min(value, dp[shouldAdded][l][k] + Math.abs(numbers.get(i) - numbers.get(l)));
                            }
                            if (value != Long.MAX_VALUE) dp[i][shouldAdded][k + skipCount] = Math.min(dp[i][shouldAdded][k + skipCount], value);
                        }
                        dp[j][i][k + skipCount] = dp[i][j][k + skipCount];
                    }
                }
            }
        }
        long answer = Long.MAX_VALUE;
        for (int i = 0; i < numbers.size(); i++)
            for (int j = 0; j <= i - 1; j++)
                for (int k = 0; k <= removableCount; k++) {
                    int n1 = numbers.size()-1; if (k + (n1 - i) > removableCount) continue;
                    answer = Math.min(answer, dp[i][j][k]);
                }
        System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m;
        List<Integer> numbers = new ArrayList<>();
        List<Integer> limits = new ArrayList<>();
        numbers.add(-1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        m = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers.add(toInt(st));
        }

        if (m > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                limits.add(toInt(st));
            }
        }

        new Solver(numbers, limits).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final long NOT_ALLOCATED = Long.MAX_VALUE;
    long[][] dp;

    List<Integer> limits;
    List<Integer> numbers;
    HashSet<Integer> limitsHashSet;

    public Solver(List<Integer> numbers, List<Integer> limits) {
        this.limits = limits;
        this.numbers = numbers;
        this.dp = new long[numbers.size()][numbers.size()];
    }

    public void solve() {
        for (long[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        limitsHashSet = new HashSet<>(limits);
        dp[0][0] = 0;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                int curIndex = Math.max(i, j) + 1;
                if (curIndex == numbers.size()) {
                    break;
                }
                if (dp[i][j] == NOT_ALLOCATED) {
                    continue;
                }
                int curNumber = numbers.get(curIndex);
                {
                    int prevNumber = numbers.get(i);
                    if (i == 0) {
                        prevNumber = curNumber;
                    }
                    dp[curIndex][j] = Math.min(dp[curIndex][j],
                        dp[i][j] + Math.abs(curNumber - prevNumber));
                }
                {
                    int prevNumber = numbers.get(j);
                    if (j == 0) {
                        prevNumber = curNumber;
                    }
                    if (limitsHashSet.contains(curIndex)) {
                        continue;
                    }
                    dp[i][curIndex] = Math.min(dp[i][curIndex],
                        dp[i][j] + Math.abs(curNumber - prevNumber));
                }
            }
        }
        long answer = Long.MAX_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            answer = Math.min(answer, dp[numbers.size() - 1][i]);
            answer = Math.min(answer, dp[i][numbers.size() - 1]);
        }
        System.out.println(answer);
    }
}

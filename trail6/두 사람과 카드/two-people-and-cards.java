import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(-1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers.add(toInt(st));
        }

        new Solver(numbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final long NOT_ALLOCATED = Long.MAX_VALUE;
    List<Integer> numbers;
    long[][] dp;

    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
        this.dp = new long[numbers.size()][numbers.size()];
    }

    public void solve() {
        for (long[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
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

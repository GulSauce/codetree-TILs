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
        numbers.add(Integer.MAX_VALUE);

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

    final Long NOT_ALLOCATED = Long.MAX_VALUE;
    long[][] dp;
    List<Integer> numbers;

    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
        this.dp = new long[numbers.size()][numbers.size()];
    }

    public void solve() {
        for (long[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }

        dp[0][0] = 0;
        for (int blue = 0; blue < numbers.size(); blue++) {
            for (int green = 0; green < numbers.size(); green++) {
                int curAppend = Math.max(blue, green) + 1;

                if (curAppend >= numbers.size()) {
                    continue;
                }
                if (dp[blue][green] == NOT_ALLOCATED) {
                    continue;
                }

                dp[curAppend][green] = Math.min(
                    dp[curAppend][green],
                    dp[blue][green] + (blue == 0 ? 0
                        : Math.abs(numbers.get(curAppend) - numbers.get(blue)))
                );

                dp[blue][curAppend] = Math.min(
                    dp[blue][curAppend],
                    dp[blue][green] + (green == 0 ? 0
                        : Math.abs(numbers.get(curAppend) - numbers.get(green)))
                );
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
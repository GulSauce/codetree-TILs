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
        int m;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(Integer.MAX_VALUE);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        m = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers.add(toInt(st));
        }

        new Solver(numbers, m).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int maxIgnoreCount;
    final long NOT_ALLOCATED = Long.MAX_VALUE;

    long[][][] dp;

    List<Integer> numbers;

    public Solver(List<Integer> numbers, int maxIgnoreCount) {
        this.numbers = numbers;
        this.maxIgnoreCount = maxIgnoreCount;
        this.dp = new long[numbers.size()][numbers.size()][maxIgnoreCount + 1];
    }

    public void solve() {
        for (long[][] arrays : dp) {
            for (long[] array : arrays) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
        }

        dp[0][0][0] = 0;
        for (int blue = 0; blue < numbers.size(); blue++) {
            for (int green = 0; green < numbers.size(); green++) {
                for (int i = 0; i <= maxIgnoreCount; i++) {
                    for (int ignoreCount = 0; ignoreCount <= maxIgnoreCount; ignoreCount++) {
                        int curAppend = Math.max(blue, green) + 1 + ignoreCount;

                        if (curAppend >= numbers.size()) {
                            continue;
                        }
                        if (i + ignoreCount > maxIgnoreCount) {
                            continue;
                        }
                        if (dp[blue][green][i] == NOT_ALLOCATED) {
                            continue;
                        }

                        dp[curAppend][green][i + ignoreCount]
                            = Math.min(dp[curAppend][green][i + ignoreCount],
                            dp[blue][green][i]
                                + (blue == 0 ? 0
                                : Math.abs(numbers.get(curAppend) - numbers.get(blue)))
                        );

                        dp[blue][curAppend][i + ignoreCount]
                            = Math.min(dp[blue][curAppend][i + ignoreCount],
                            dp[blue][green][i]
                                + (green == 0 ? 0 :
                                +Math.abs(numbers.get(curAppend) - numbers.get(green)))
                        );
                    }
                }
            }
        }

        long answer = Long.MAX_VALUE;
        for (int blue = 0; blue < numbers.size(); blue++) {
            for (int green = 0; green < numbers.size(); green++) {
                for (int i = 0; i <= maxIgnoreCount; i++) {
                    {
                        int remain = (numbers.size() - 1 - blue);
                        if (remain + i > maxIgnoreCount) {
                            continue;
                        }

                        answer = Math.min(answer, dp[blue][green][i]);
                    }
                    {
                        int remain = (numbers.size() - 1 - green);
                        if (remain + i > maxIgnoreCount) {
                            continue;
                        }

                        answer = Math.min(answer, dp[blue][green][i]);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
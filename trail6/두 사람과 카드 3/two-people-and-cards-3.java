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
        numbers.add(-1);

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

    int removableCount;
    final long NOT_ALLOCATED = Long.MAX_VALUE;

    long[][][] dp;

    List<Integer> numbers;

    public Solver(List<Integer> numbers, int removableCount) {
        this.numbers = numbers;
        this.removableCount = removableCount;
        this.dp = new long[numbers.size()][numbers.size()][removableCount + 1];
    }

    public void solve() {
        for (long[][] arrays : dp) {
            for (long[] array : arrays) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
        }

        dp[0][0][0] = 0;
        for (int i = 1; i < numbers.size(); i++) {
            if (i - 1 > removableCount) {
                break;
            }
            dp[i][0][i - 1] = 0;
            dp[0][i][i - 1] = 0;
        }

        for (int i = 1; i < numbers.size(); i++) {
            for (int j = 0; j <= i - 1; j++) {
                for (int k = 0; k <= removableCount; k++) {
                    for (int skipCount = 0; skipCount <= removableCount; skipCount++) {
                        if (removableCount < k + skipCount) {
                            continue;
                        }

                        // 반드시 이어야하는 꼬리
                        int shouldAdded = i - 1 - skipCount;
                        if (shouldAdded < 0) {
                            continue;
                        }

                        if (j < shouldAdded && dp[shouldAdded][j][k] != NOT_ALLOCATED) {
                            if (shouldAdded == 0) {
                                dp[i][j][k + skipCount] = Math.min(dp[i][j][k + skipCount],
                                    dp[shouldAdded][j][k]);
                            } else {
                                dp[i][j][k + skipCount] = Math.min(
                                    dp[i][j][k + skipCount],
                                    dp[shouldAdded][j][k] + Math.abs(
                                        numbers.get(i) - numbers.get(shouldAdded)));
                            }
                        } else if (1 <= shouldAdded && j == shouldAdded) {
                            long value = Long.MAX_VALUE;
                            for (int l = 0; l < shouldAdded; l++) {
                                if (dp[shouldAdded][l][k] == NOT_ALLOCATED) {
                                    continue;
                                }
                                if (l == 0) {
                                    value = Math.min(value, dp[shouldAdded][l][k]);
                                } else {
                                    value = Math.min(value,
                                        dp[shouldAdded][l][k]
                                            + Math.abs(numbers.get(i) - numbers.get(l))
                                    );
                                }
                            }
                            dp[i][shouldAdded][k + skipCount] = Math.min(
                                dp[i][shouldAdded][k + skipCount], value);
                        }
                        dp[j][i][k + skipCount] = dp[i][j][k + skipCount];
                    }
                }
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j <= i - 1; j++) {
                for (int k = 0; k <= removableCount; k++) {
                    if (numbers.size() - 1 - i + k > removableCount) {
                        continue;
                    }
                    answer = Math.min(answer, dp[i][j][k]);
                }
            }
        }
        System.out.println(answer);
    }
}


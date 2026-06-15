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
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                // 이미 버린 카드 개수
                for (int k = 0; k <= removableCount; k++) {
                    // 버릴 카드 개수
                    for (int l = 0; l <= removableCount; l++) {
                        int curIndex = Math.max(i, j) + 1 + l;
                        if (curIndex >= numbers.size()) {
                            break;
                        }
                        if (k + l > removableCount) {
                            break;
                        }
                        if (dp[i][j][k] == NOT_ALLOCATED) {
                            continue;
                        }
                        int curNumber = numbers.get(curIndex);
                        {
                            int prevNumber = numbers.get(i);
                            if (i == 0) {
                                prevNumber = curNumber;
                            }
                            dp[curIndex][j][k + l] =
                                Math.min(dp[curIndex][j][k + l],
                                    dp[i][j][k] + Math.abs(curNumber - prevNumber)
                                );
                        }
                        {
                            int prevNumber = numbers.get(j);
                            if (j == 0) {
                                prevNumber = curNumber;
                            }
                            dp[i][curIndex][k + l] =
                                Math.min(dp[i][curIndex][k + l],
                                    dp[i][j][k] + Math.abs(curNumber - prevNumber)
                                );
                        }
                    }
                }
            }
        }
        long answer = Long.MAX_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                for (int k = 0; k <= removableCount; k++) {
                    int tail = numbers.size() - 1 - Math.max(i, j);
                    if (tail + k > removableCount) {
                        continue;
                    }
                    answer = Math.min(answer, dp[i][j][k]);
                }
            }
        }
        System.out.println(answer);
    }
}

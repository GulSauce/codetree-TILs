import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    long[][] dp;
    List<Integer> numbers;

    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
        this.dp = new long[numbers.size()][numbers.size()];
    }

    public void solve() {
        for (int i = 2; i < numbers.size(); i++) {
            for (int j = 0; j <= i - 2; j++) {
                int diff = Math.abs(
                    numbers.get(i) - numbers.get(i - 1)
                );
                dp[i][j] = dp[i - 1][j] + diff;
                dp[j][i] = dp[i][j];
            }
            // j = i-1인 경우의 처리
            {
                long value = Long.MAX_VALUE;
                for (int j = 0; j <= i - 2; j++) {
                    int diff = 0;
                    if (0 < j) {
                        diff = Math.abs(
                            numbers.get(i) - numbers.get(j)
                        );
                    }
                    value = Math.min(value, dp[i - 1][j] + diff);
                }
                dp[i][i - 1] = value;
                dp[i - 1][i] = dp[i][i - 1];
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i < numbers.size() - 1; i++) {
            answer = Math.min(answer, dp[numbers.size() - 1][i]);
        }
        System.out.println(answer);
    }
}
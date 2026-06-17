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
            int tail1 = i - 1;
            for (int tail2 = 0; tail2 <= tail1; tail2++) {
                if (tail2 < tail1) {
                    int diff = Math.abs(
                        numbers.get(i) - numbers.get(tail1)
                    );
                    dp[i][tail2] = dp[i - 1][tail2] + diff;
                }
                if (tail2 == tail1) {
                    long value = Long.MAX_VALUE;
                    for (int k = 0; k < tail1; k++) {

                        int diff = k == 0 ?
                            0 : Math.abs(numbers.get(i) - numbers.get(k));
                        value = Math.min(value, dp[tail1][k] + diff);
                    }
                    dp[i][tail1] = value;
                }
                dp[tail2][i] = dp[i][tail2];
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i < numbers.size() - 1; i++) {
            answer = Math.min(answer, dp[numbers.size() - 1][i]);
        }
        System.out.println(answer);
    }
}
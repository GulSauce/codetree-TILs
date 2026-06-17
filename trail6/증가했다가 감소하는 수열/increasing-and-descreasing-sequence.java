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

    final int R = 10_007;
    int[][] dp;
    List<Integer> numbers;

    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
        this.dp = new int[numbers.size()][numbers.size()];
    }

    public void solve() {
        dp[0][0] = 1;
        for (int i = 1; i < numbers.size() - 1; i++) {
            int value = 0;
            for (int j = 0; j <= i - 1; j++) {
                if (numbers.get(j) < numbers.get(i)) {
                    value += dp[j][0];
                    value %= R;
                }
            }
            dp[i][0] = value;
            dp[0][i] = value;
        }
        for (int i = 1; i < numbers.size() - 1; i++) {
            for (int tail1 = 1; tail1 <= i - 1; tail1++) {
                for (int tail2 = 1; tail2 <= tail1; tail2++) {
                    if (tail2 < tail1) {
                        if (numbers.get(tail1) < numbers.get(i)) {
                            dp[i][tail2] += dp[tail1][tail2];
                            dp[i][tail2] %= R;
                        }
                    } else if (tail2 == tail1) {
                        for (int j = 0; j <= tail2 - 1; j++) {
                            if (numbers.get(j) < numbers.get(i)) {
                                dp[i][tail1] += dp[tail1][j];
                                dp[i][tail1] %= R;
                            }
                        }
                    }
                    dp[tail2][i] = dp[i][tail2];
                }
            }
        }

        if (numbers.get(numbers.size() - 1) < numbers.get(0)) {
            System.out.println(0);
            System.exit(0);
        }
        int answer = 1;
        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = 0; j < numbers.size() - 1; j++) {
                if (i == j) {
                    continue;
                }
                if (numbers.get(numbers.size() - 1) <= numbers.get(i)) {
                    continue;
                }
                if (numbers.get(numbers.size() - 1) <= numbers.get(j)) {
                    continue;
                }
                answer += dp[i][j];
                answer %= R;
            }
        }
        System.out.println(answer);
    }
}
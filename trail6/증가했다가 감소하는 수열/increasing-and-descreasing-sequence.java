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
        for (int blue = 0; blue < numbers.size(); blue++) {
            for (int green = 0; green < numbers.size(); green++) {
                int appendStart = Math.max(blue, green) + 1;
                for (int curAppend = appendStart; curAppend < numbers.size(); curAppend++) {
                    if (curAppend == numbers.size() - 1
                        && numbers.get(blue) < numbers.get(curAppend)
                        && numbers.get(green) < numbers.get(curAppend)
                    ) {
                        dp[curAppend][green] += dp[blue][green];
                        dp[curAppend][green] %= R;

                        dp[blue][curAppend] += dp[blue][green];
                        dp[blue][curAppend] %= R;
                    } else if (curAppend < numbers.size() - 1) {
                        if (numbers.get(blue) < numbers.get(curAppend)) {
                            dp[curAppend][green] += dp[blue][green];
                            dp[curAppend][green] %= R;
                        }
                        if (numbers.get(green) < numbers.get(curAppend)) {
                            dp[blue][curAppend] += dp[blue][green];
                            dp[blue][curAppend] %= R;
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < numbers.size(); i++) {
            answer += dp[numbers.size() - 1][i];
            answer %= R;
        }
        System.out.println(answer);
    }
}
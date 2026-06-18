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
        for (int curAppend = 2; curAppend < numbers.size(); curAppend++) {
            int blue = curAppend - 1;
            for (int green = 0; green <= blue; green++) {
                if (green < blue) {
                    int diff = Math.abs(
                        numbers.get(curAppend) - numbers.get(blue)
                    );
                    dp[curAppend][green] = dp[curAppend - 1][green] + diff;
                }
                if (green == blue) {
                    long value = Long.MAX_VALUE;
                    for (int subGreen = 0; subGreen < green; subGreen++) {
                        int diff = subGreen == 0 ?
                            0 : Math.abs(numbers.get(curAppend) - numbers.get(subGreen));
                        value = Math.min(value, dp[green][subGreen] + diff);
                    }
                    dp[curAppend][green] = value;
                }
                dp[green][curAppend] = dp[curAppend][green];
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i < numbers.size() - 1; i++) {
            answer = Math.min(answer, dp[numbers.size() - 1][i]);
        }
        System.out.println(answer);
    }
}
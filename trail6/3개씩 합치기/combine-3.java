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

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int[][] dp;
    List<Integer> numbers;

    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
        this.dp = new int[numbers.size()][numbers.size()];
    }

    public void solve() {
        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        for (int i = 0; i < numbers.size(); i++) {
            dp[i][i] = 0;
            if (i + 1 < numbers.size()) {
                dp[i][i + 1] = 0;
            }
        }

        for (int lenght = 3; lenght <= numbers.size(); lenght++) {
            for (int i = 0; i < numbers.size(); i++) {
                int j = i + lenght - 1;
                if (j >= numbers.size()) {
                    continue;
                }
                for (int k = i + 1; k <= j; k++) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i][k] + dp[k][j] + numbers.get(i) * numbers.get(k) * numbers.get(j)
                    );
                }
            }
        }

        System.out.println(dp[0][numbers.size() - 1]);
    }
}
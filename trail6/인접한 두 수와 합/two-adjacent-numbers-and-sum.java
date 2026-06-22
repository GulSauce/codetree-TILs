import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(toInt(st));
        }

        new Solver(numbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = -1;
    int[][] dp;
    int[][] merged;
    List<Integer> numbers;

    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
        this.dp = new int[numbers.size()][numbers.size()];
        this.merged = new int[numbers.size()][numbers.size()];
    }

    public void solve() {
        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        for (int i = 0; i < numbers.size(); i++) {
            dp[i][i] = 0;
        }
        for (int i = 0; i < numbers.size(); i++) {
            merged[i][i] = numbers.get(i);
        }
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                merged[i][j] = merged[i][j - 1] + numbers.get(j);
            }
        }
        for (int length = 2; length <= numbers.size(); length++) {
            for (int i = 0; i < numbers.size(); i++) {
                int j = i + length - 1;
                if (j >= numbers.size()) {
                    break;
                }
                for (int k = i; k <= j - 1; k++) {
                    dp[i][j] = Math.max(
                        dp[i][j],
                        dp[i][k] + dp[k + 1][j] + Math.abs(merged[i][k] - merged[k + 1][j])
                    );
                }
            }
        }
        System.out.println(dp[0][numbers.size() - 1]);
    }
}
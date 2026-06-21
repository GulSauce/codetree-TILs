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

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int[][] dp;
    int[][] merged;
    final int NUMBER_COUNT;
    List<Integer> numbers;

    public Solver(List<Integer> numbers) {
        this.dp = new int[numbers.size()][numbers.size()];
        this.merged = new int[numbers.size()][numbers.size()];
        this.numbers = numbers;
        this.NUMBER_COUNT = numbers.size();
    }

    public void solve() {
        for (int i = 0; i < NUMBER_COUNT; i++) {
            merged[i][i] = numbers.get(i);
            for (int j = i + 1; j < NUMBER_COUNT; j++) {
                merged[i][j] = merged[i][j - 1] + numbers.get(j);
            }
        }

        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }

        for (int i = 0; i < NUMBER_COUNT; i++) {
            dp[i][i] = 0;
        }

        for (int size = 2; size <= numbers.size(); size++) {
            for (int i = 0; i < numbers.size(); i++) {
                int j = i + size - 1;
                if (j >= numbers.size()) {
                    continue;
                }

                for (int k = i + 1; k <= j; k++) {
                    int cost = dp[i][k - 1] + dp[k][j]
                        + merged[i][k - 1] + merged[k][j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        System.out.println(dp[0][numbers.size() - 1]);
    }
}
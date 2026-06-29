import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, K;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        K = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(toInt(st));
        }

        new Solver(K, numbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int FULL;
    final int groupCount;
    final int NOT_ALLOCATED = -1;

    final int[] dp;

    List<Integer> numberList;

    public Solver(int groupCount, List<Integer> numberList) {
        this.FULL = (1 << numberList.size()) - 1;
        this.groupCount = groupCount;
        this.numberList = numberList;
        this.dp = new int[FULL + 1];
    }

    public void solve() {
        int targetSum = 0;
        for (int number : numberList) {
            targetSum += number;
        }

        if (targetSum % groupCount != 0) {
            System.out.println("No");
            System.exit(0);
        }

        targetSum /= groupCount;
        Arrays.fill(dp, NOT_ALLOCATED);
        dp[0] = 0;
        for (int bitmask = 0; bitmask <= FULL; bitmask++) {
            for (int i = 0; i < numberList.size(); i++) {
                if (dp[bitmask] == NOT_ALLOCATED) {
                    continue;
                }
                if ((bitmask >> i & 1) == 1) {
                    continue;
                }
                if (dp[bitmask] + numberList.get(i) > targetSum) {
                    continue;
                }
                dp[bitmask | 1 << i] = dp[bitmask] + numberList.get(i);
                dp[bitmask | 1 << i] %= targetSum;
            }
        }

        System.out.println(dp[FULL] == 0 ? "Yes" : "No");
    }
}
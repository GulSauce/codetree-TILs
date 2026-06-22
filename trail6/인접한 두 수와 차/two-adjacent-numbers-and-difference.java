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

    final int MAX_DIFF = 10;

    final int NOT_ALLOCATED = -1;
    int[][][] dp;


    List<Integer> numbers;


    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
        this.dp = new int[numbers.size()][numbers.size()][MAX_DIFF + 1];
    }

    public void solve() {
        for (int[][] arrays : dp) {
            for (int[] array : arrays) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            dp[i][i][numbers.get(i)] = 0;
        }
        for (int i = 1; i < numbers.size(); i++) {
            int prev = numbers.get(i - 1);
            int cur = numbers.get(i);
            dp[i - 1][i][Math.abs(cur - prev)] = prev + cur;
        }
        for (int length = 3; length <= numbers.size(); length++) {
            for (int i = 0; i < numbers.size(); i++) {
                for (int lSurvivedNumber = 0; lSurvivedNumber <= MAX_DIFF; lSurvivedNumber++) {
                    for (int rSurvivedNumber = 0; rSurvivedNumber <= MAX_DIFF; rSurvivedNumber++) {
                        int j = i + length - 1;
                        if (j >= numbers.size()) {
                            break;
                        }
                        for (int k = i; k <= j - 1; k++) {
                            int lPoint = dp[i][k][lSurvivedNumber];
                            int rPoint = dp[k + 1][j][rSurvivedNumber];
                            if (lPoint == NOT_ALLOCATED || rPoint == NOT_ALLOCATED) {
                                continue;
                            }
                            int curDiff = Math.abs(lSurvivedNumber - rSurvivedNumber);
                            dp[i][j][curDiff] =
                                Math.max(
                                    dp[i][j][curDiff],
                                    lPoint + rPoint + lSurvivedNumber + rSurvivedNumber
                                );
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int number = 0; number <= MAX_DIFF; number++) {
            answer = Math.max(answer, dp[0][numbers.size() - 1][number]);
        }

        System.out.println(answer);
    }
}
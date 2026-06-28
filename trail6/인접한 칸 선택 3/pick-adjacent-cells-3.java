import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m, k;
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        m = toInt(st);
        k = toInt(st);

        grid = new int[n + 1][m + 1];
        for (int row = 1; row <= n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= m; col++) {
                grid[row][col] = toInt(st);
            }
        }

        new Solver(grid, k).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int ROW_LENGTH;
    final int COL_LENGTH;
    final int MAX_SELECT_COUNT;
    final int FULL;
    int[][] grid;
    int[][][] dp;

    public Solver(int[][] grid, int maxSelectCount) {
        this.ROW_LENGTH = grid.length - 1;
        this.COL_LENGTH = grid[0].length - 1;
        this.MAX_SELECT_COUNT = maxSelectCount;
        this.FULL = (1 << COL_LENGTH) - 1;
        this.grid = grid;
        this.dp = new int[ROW_LENGTH + 1][FULL + 1][MAX_SELECT_COUNT + 1];
    }

    public void solve() {
        for (int bitmask = 0; bitmask <= FULL; bitmask++) {
            if ((bitmask & bitmask << 1) != 0) {
                continue;
            }
            int bitCount = Integer.bitCount(bitmask);
            if (MAX_SELECT_COUNT < bitCount) {
                continue;
            }
            dp[1][bitmask][bitCount] = getSum(1, bitmask);
        }

        for (int row = 2; row <= ROW_LENGTH; row++) {
            for (int prevBitmask = 0; prevBitmask <= FULL; prevBitmask++) {
                for (int prevSelectCount = 0; prevSelectCount <= MAX_SELECT_COUNT;
                    prevSelectCount++) {
                    for (int bitmask = 0; bitmask <= FULL; bitmask++) {
                        if ((bitmask & bitmask << 1) != 0) {
                            continue;
                        }
                        if ((bitmask & prevBitmask) != 0) {
                            continue;
                        }
                        int bitCountSum = Integer.bitCount(bitmask) + prevSelectCount;
                        if (MAX_SELECT_COUNT < bitCountSum) {
                            continue;
                        }
                        dp[row][bitmask][bitCountSum] =
                            Math.max(
                                dp[row][bitmask][bitCountSum],
                                dp[row - 1][prevBitmask][prevSelectCount] + getSum(row, bitmask)
                            );
                    }
                }
            }
        }

        int answer = 0;
        for (int bitmask = 0; bitmask <= FULL; bitmask++) {
            for (int selectCount = 0; selectCount <= MAX_SELECT_COUNT; selectCount++) {
                answer = Math.max(
                    answer,
                    dp[ROW_LENGTH][bitmask][selectCount]
                );
            }
        }
        System.out.println(answer);
    }

    private int getSum(int row, int bitMask) {
        int answer = 0;
        for (int col = 1; col <= COL_LENGTH; col++) {
            if ((bitMask >> (col - 1) & 1) == 1) {
                answer += grid[row][col];
            }
        }
        return answer;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m;
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        m = toInt(st);

        grid = new int[n + 1][m + 1];
        for (int row = 1; row <= n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= m; col++) {
                grid[row][col] = toInt(st);
            }
        }

        new Solver(grid).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int ROW_LENGTH;
    int COL_LENGTH;
    final int FULL;
    int[][] grid;
    int[] wallInfoList;
    int[][] dp;

    public Solver(int[][] grid) {
        this.ROW_LENGTH = grid.length - 1;
        this.COL_LENGTH = grid[0].length - 1;
        this.FULL = (1 << COL_LENGTH) - 1;
        this.grid = grid;
        this.wallInfoList = new int[ROW_LENGTH + 1];
        this.dp = new int[ROW_LENGTH + 1][FULL + 1];
    }

    public void solve() {
        for (int row = 1; row <= ROW_LENGTH; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 1; col <= COL_LENGTH; col++) {
                sb.append(grid[row][col]);
            }
            wallInfoList[row] = Integer.parseInt(sb.toString(), 2);
        }

        for (int bitmask = 0; bitmask <= FULL; bitmask++) {
            if ((wallInfoList[1] & bitmask) != 0) {
                continue;
            }
            if ((bitmask & bitmask << 1) != 0) {
                continue;
            }
            dp[1][bitmask] = Integer.bitCount(bitmask);
        }

        for (int row = 2; row <= ROW_LENGTH; row++) {
            for (int bitmask = 0; bitmask <= FULL; bitmask++) {
                if ((wallInfoList[row] & bitmask) != 0) {
                    continue;
                }
                if ((bitmask & bitmask << 1) != 0) {
                    continue;
                }
                for (int prevBitmask = 0; prevBitmask <= FULL; prevBitmask++) {
                    if ((bitmask & prevBitmask) != 0) {
                        continue;
                    }
                    dp[row][bitmask] = Math.max(dp[row][bitmask],
                        dp[row - 1][prevBitmask] + Integer.bitCount(bitmask));
                }
            }
        }

        int answer = 0;
        for (int bitmask = 0; bitmask <= FULL; bitmask++) {
            answer = Math.max(
                answer,
                dp[ROW_LENGTH][bitmask]
            );
        }
        System.out.println(answer);
    }
}
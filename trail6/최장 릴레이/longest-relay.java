import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        grid = new int[n + 1][n + 1];

        for (int row = 1; row <= n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= n; col++) {
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

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    final int FULL;
    final int GRID_LENGTH;
    int[][] dp;
    int[][] grid;

    public Solver(int[][] grid) {
        this.grid = grid;
        this.GRID_LENGTH = grid.length - 1;
        this.FULL = (1 << GRID_LENGTH + 1) - 1;
        this.dp = new int[GRID_LENGTH + 1][FULL + 1];
    }

    public void solve() {
        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }

        dp[1][1 << 1] = 0;

        for (int bitmask = 0; bitmask <= FULL; bitmask++) {
            for (int cur = 1; cur <= GRID_LENGTH; cur++) {
                for (int next = 1; next <= GRID_LENGTH; next++) {
                    if ((bitmask >> next & 1) == 1) {
                        continue;
                    }
                    if ((bitmask >> cur & 1) == 0) {
                        continue;
                    }
                    if (dp[cur][bitmask] == NOT_ALLOCATED) {
                        continue;
                    }
                    if (dp[cur][bitmask] < grid[cur][next]) {
                        dp[next][bitmask | 1 << next] =
                            Math.min(
                                dp[next][bitmask | 1 << next],
                                grid[cur][next]
                            );
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= GRID_LENGTH; i++) {
            for (int bitmask = 0; bitmask <= FULL; bitmask++) {
                if (dp[i][bitmask] == NOT_ALLOCATED) {
                    continue;
                }
                answer = Math.max(answer, Integer.bitCount(bitmask));
            }
        }
        System.out.println(answer);
    }
}
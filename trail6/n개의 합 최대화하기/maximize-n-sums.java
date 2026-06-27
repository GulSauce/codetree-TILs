import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        grid = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                grid[i][j] = toInt(st);
            }
        }

        new Solver(grid).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int GRID_LENGTH;
    final int FULL;
    int[][] grid;
    int[][] dp;

    public Solver(int[][] grid) {
        this.GRID_LENGTH = grid.length - 1;
        this.FULL = (1 << GRID_LENGTH + 1) - 1;
        this.grid = grid;
        this.dp = new int[GRID_LENGTH + 1][FULL + 1];
    }

    public void solve() {
        for (int i = 1; i <= GRID_LENGTH; i++) {
            dp[1][1 << i] = grid[1][i];
        }
        for (int row = 1; row < GRID_LENGTH; row++) {
            for (int col = 1; col <= GRID_LENGTH; col++) {
                for (int bitmask = 0; bitmask <= FULL; bitmask++) {
                    if (dp[row][bitmask] == 0) {
                        continue;
                    }
                    if ((bitmask >> col & 1) == 1) {
                        continue;
                    }
                    dp[row + 1][bitmask | (1 << col)] = Math.max(
                        dp[row + 1][bitmask | (1 << col)],
                        dp[row][bitmask] + grid[row + 1][col]
                    );
                }
            }
        }
        System.out.println(dp[GRID_LENGTH][FULL - 1]);
    }
}
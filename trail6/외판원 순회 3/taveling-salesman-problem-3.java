import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, K;
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        K = toInt(st);

        grid = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                grid[row][col] = toInt(st);
            }
        }

        new Solver(K, grid).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    final int CANT_MOVE = 0;
    final int NODE_COUNT;
    final int FULL;
    int visitCount;
    int[][] grid;
    int[][] dp;

    public Solver(int visitCount, int[][] grid) {
        this.NODE_COUNT = grid.length - 1;
        this.FULL = (1 << NODE_COUNT + 1) - 1;
        this.dp = new int[NODE_COUNT + 1][FULL + 1];
        this.visitCount = visitCount;
        this.grid = grid;
    }

    public void solve() {
        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        dp[1][1 << 1] = 0;
        for (int bitmask = 0; bitmask <= FULL; bitmask++) {
            for (int v = 1; v <= NODE_COUNT; v++) {
                for (int nV = 1; nV <= NODE_COUNT; nV++) {
                    if (dp[v][bitmask] == NOT_ALLOCATED) {
                        continue;
                    }
                    if (grid[v][nV] == CANT_MOVE) {
                        continue;
                    }
                    if ((bitmask >> nV & 1) == 1) {
                        continue;
                    }
                    dp[nV][bitmask | 1 << nV]
                        = Math.min(
                        dp[nV][bitmask | 1 << nV],
                        dp[v][bitmask] + grid[v][nV]
                    );
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int bitmask = 0; bitmask <= FULL; bitmask++) {
            for (int lastV = 2; lastV <= NODE_COUNT; lastV++) {
                if (dp[lastV][bitmask] == NOT_ALLOCATED) {
                    continue;
                }
                if (grid[lastV][1] == CANT_MOVE) {
                    continue;
                }

                int bitCount = 0;
                for (int i = 2; i <= NODE_COUNT; i++) {
                    if ((bitmask >> i & 1) == 1) {
                        bitCount++;
                    }
                }
                if (bitCount != visitCount) {
                    continue;
                }
                
                answer = Math.min(
                    answer,
                    dp[lastV][bitmask] + grid[lastV][1]
                );
            }
        }

        System.out.println(answer);
    }
}
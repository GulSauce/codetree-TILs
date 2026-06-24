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
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        grid = new int[N + 1][N + 1];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
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

    final int FULL_MASK;
    final int END_NODE_NUMBER;
    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int[][] grid;
    int[][] dp;

    List<List<Edge>> graph = new ArrayList<>();

    public Solver(int[][] grid) {
        this.grid = grid;
        this.END_NODE_NUMBER = grid.length - 1;
        this.FULL_MASK = (1 << (END_NODE_NUMBER + 1)) - 1;
        this.dp = new int[FULL_MASK + 1][END_NODE_NUMBER + 1];
    }

    public void solve() {
        // 그래프 생성
        for (int i = 0; i <= END_NODE_NUMBER; i++) {
            graph.add(new ArrayList<>());
        }
        for (int row = 1; row <= END_NODE_NUMBER; row++) {
            for (int col = 1; col <= END_NODE_NUMBER; col++) {
                if (grid[row][col] == 0) {
                    continue;
                }
                graph.get(row).add(new Edge(col, grid[row][col]));
            }
        }

        // dp 초기화
        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        dp[0][1] = 0;

        for (int mask = 0; mask <= FULL_MASK; mask++) {
            for (int cur = 1; cur <= END_NODE_NUMBER; cur++) {
                if (dp[mask][cur] == NOT_ALLOCATED) {
                    continue;
                }
                for (Edge nearEdge : graph.get(cur)) {
                    int nearNumber = nearEdge.number;
                    int nearW = nearEdge.w;
                    if ((mask >> nearNumber & 1) == 1) {
                        continue;
                    }
                    dp[mask | (1 << nearNumber)][nearNumber]
                        = Math.min(
                        dp[mask | (1 << nearNumber)][nearNumber],
                        dp[mask][cur] + nearW
                    );
                }
            }
        }

        System.out.println(dp[FULL_MASK - 1][1]);
    }
}

class Edge {

    int number;
    int w;

    public Edge(int number, int w) {
        this.number = number;
        this.w = w;
    }
}
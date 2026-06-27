import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, k;
        List<Integer> directPrices = new ArrayList<>();
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        k = toInt(st);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            directPrices.add(toInt(st));
        }

        grid = new int[n + 1][n + 1];
        for (int row = 1; row <= n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= n; col++) {
                grid[row][col] = toInt(st);
            }
        }

        new Solver(k, directPrices, grid).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    final int FULL;
    final int GRID_LENGTH;
    int minPutCount;

    int[] dp;
    int[][] grid;

    List<Integer> directPrices;
    List<List<Edge>> graph = new ArrayList<>();

    public Solver(int minPutCount, List<Integer> directPrices, int[][] grid) {
        this.GRID_LENGTH = grid.length - 1;
        this.FULL = (1 << GRID_LENGTH + 1) - 1;
        this.minPutCount = minPutCount;
        this.directPrices = directPrices;
        this.dp = new int[FULL + 1];
        this.grid = grid;
    }

    public void solve() {
        for (int i = 0; i <= GRID_LENGTH; i++) {
            graph.add(new ArrayList<>());
        }
        for (int row = 1; row <= GRID_LENGTH; row++) {
            for (int col = 1; col <= GRID_LENGTH; col++) {
                if (grid[row][col] == 0) {
                    continue;
                }
                graph.get(row).add(new Edge(col, grid[row][col]));
            }
        }

        graph.get(0).add(new Edge(-1, -1));
        for (int i = 0; i < directPrices.size(); i++) {
            graph.get(0).add(new Edge(i + 1, directPrices.get(i)));
        }

        Arrays.fill(dp, NOT_ALLOCATED);
        dp[0] = 0;
        for (int bitMask = 0; bitMask <= FULL; bitMask++) {
            for (int v = 1; v <= GRID_LENGTH; v++) {
                for (Edge edge : graph.get(v)) {
                    if (dp[bitMask] == NOT_ALLOCATED) {
                        continue;
                    }
                    if ((bitMask >> edge.number & 1) != 0) {
                        continue;
                    }
                    int add = graph.get(0).get(edge.number).weight;
                    if ((bitMask >> v & 1) == 1) {
                        add = Math.min(add, edge.weight);
                    }
                    dp[bitMask | 1 << edge.number] =
                        Math.min(
                            dp[bitMask | 1 << edge.number],
                            dp[bitMask] + add
                        );
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int bitMask = 2; bitMask <= FULL; bitMask++) {
            if (Integer.bitCount(bitMask) >= minPutCount) {
                answer = Math.min(answer, dp[bitMask]);
            }
        }
        System.out.println(answer);
    }
}

class Edge {

    int number;
    int weight;

    public Edge(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}
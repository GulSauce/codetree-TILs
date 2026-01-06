import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(n, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;

    boolean[] visited;

    final int UNAVAILABLE = Integer.MAX_VALUE;
    final int NOT_SELECTED = 0;
    final int SELECTED = 1;
    final int PARENT_NEEDED = 2;
    int[][] dp;

    List<Edge> edges;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int nodeCount, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.edges = edges;
        this.visited = new boolean[nodeCount + 1];
        this.dp = new int[nodeCount + 1][3];
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
            graph.get(edge.end).add(edge.start);
        }

        final int ROOT_NUMBER = 1;
        DPDFS(ROOT_NUMBER);

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= 2; i++) {
            answer = Math.min(answer, dp[ROOT_NUMBER][i]);
        }
        System.out.println(answer);
    }

    private void DPDFS(int cur) {
        visited[cur] = true;

        boolean childAllNotSelected = true;
        boolean isLeaf = true;
        int minDiff = Integer.MAX_VALUE;
        for (int child : graph.get(cur)) {
            if (visited[child]) {
                continue;
            }
            isLeaf = false;
            DPDFS(child);

            int selectedValue = Math.min(dp[child][PARENT_NEEDED],
                Math.min(dp[child][SELECTED], dp[child][NOT_SELECTED]));
            if (selectedValue == UNAVAILABLE) {
                dp[cur][SELECTED] = UNAVAILABLE;
            } else if (dp[cur][SELECTED] != UNAVAILABLE) {
                dp[cur][SELECTED] += selectedValue;
            }

            int notSelectedValue = Math.min(dp[child][NOT_SELECTED], dp[child][SELECTED]);
            if (dp[child][SELECTED] <= dp[child][NOT_SELECTED]) {
                childAllNotSelected = false;
            } else {
                minDiff = Math.min(minDiff, dp[child][SELECTED] - dp[child][NOT_SELECTED]);
            }
            if (notSelectedValue == UNAVAILABLE) {
                dp[cur][NOT_SELECTED] = UNAVAILABLE;
            } else if (dp[cur][NOT_SELECTED] != UNAVAILABLE) {
                dp[cur][NOT_SELECTED] += notSelectedValue;
            }

            int parentNeededValue = dp[child][NOT_SELECTED];
            if (parentNeededValue == UNAVAILABLE) {
                dp[cur][PARENT_NEEDED] = UNAVAILABLE;
            } else if (dp[cur][PARENT_NEEDED] != UNAVAILABLE) {
                dp[cur][PARENT_NEEDED] += parentNeededValue;
            }
        }
        if (!isLeaf && childAllNotSelected && dp[cur][NOT_SELECTED] != UNAVAILABLE) {
            dp[cur][NOT_SELECTED] += minDiff;
        }
        if (isLeaf) {
            dp[cur][NOT_SELECTED] = UNAVAILABLE;
        }
        if (dp[cur][SELECTED] != UNAVAILABLE) {
            dp[cur][SELECTED]++;
        }
    }
}

class Edge {

    int start;
    int end;

    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
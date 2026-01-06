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
    final int CHILD_COVERED = 0;
    final int SELECTED = 1;
    final int PARENT_COVERED = 2;
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

        int answer = Math.min(dp[ROOT_NUMBER][SELECTED], dp[ROOT_NUMBER][CHILD_COVERED]);
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

            int selectedValue = Math.min(dp[child][PARENT_COVERED],
                Math.min(dp[child][SELECTED], dp[child][CHILD_COVERED]));
            if (selectedValue == UNAVAILABLE) {
                dp[cur][SELECTED] = UNAVAILABLE;
            } else if (dp[cur][SELECTED] != UNAVAILABLE) {
                dp[cur][SELECTED] += selectedValue;
            }

            int childCoveredValue = Math.min(dp[child][CHILD_COVERED], dp[child][SELECTED]);
            if (dp[child][SELECTED] == UNAVAILABLE && dp[child][CHILD_COVERED] == UNAVAILABLE) {
                dp[cur][CHILD_COVERED] = UNAVAILABLE;
            } else if (dp[cur][CHILD_COVERED] != UNAVAILABLE) {
                dp[cur][CHILD_COVERED] += childCoveredValue;
            }
            if (dp[child][SELECTED] <= dp[child][CHILD_COVERED]) {
                childAllNotSelected = false;
            } else {
                int curDiff = dp[child][SELECTED] == UNAVAILABLE ? UNAVAILABLE
                    : dp[child][SELECTED] - dp[child][CHILD_COVERED];
                minDiff = Math.min(minDiff, curDiff);
            }

            int parentCoveredValue = dp[child][CHILD_COVERED];
            if (parentCoveredValue == UNAVAILABLE) {
                dp[cur][PARENT_COVERED] = UNAVAILABLE;
            } else if (dp[cur][PARENT_COVERED] != UNAVAILABLE) {
                dp[cur][PARENT_COVERED] += parentCoveredValue;
            }
        }
        if (!isLeaf && childAllNotSelected && dp[cur][CHILD_COVERED] != UNAVAILABLE) {
            if (minDiff == UNAVAILABLE) {
                dp[cur][CHILD_COVERED] = UNAVAILABLE;
            } else {
                dp[cur][CHILD_COVERED] += minDiff;
            }
        }
        if (isLeaf) {
            dp[cur][CHILD_COVERED] = UNAVAILABLE;
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
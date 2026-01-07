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
    List<Edge> edges;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int nodeCount, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.edges = edges;
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
        DPDFS(ROOT_NUMBER, -1);

        int answer = Math.min(dp[ROOT_NUMBER][SELECTED], dp[ROOT_NUMBER][CHILD_COVERED]);
        System.out.println(answer);
    }

    final int UNAVAILABLE = 100_000_000;
    final int CHILD_COVERED = 0;
    final int SELECTED = 1;
    final int NEED_PARENT_COVER = 2;
    int[][] dp;

    private void DPDFS(int cur, int parent) {
        boolean childAllNotSelected = true;
        boolean isLeaf = true;
        int minDiff = Integer.MAX_VALUE;
        for (int child : graph.get(cur)) {
            if (child == parent) {
                continue;
            }
            isLeaf = false;
            DPDFS(child, cur);

            int toSelectedValue = Math.min(dp[child][NEED_PARENT_COVER],
                Math.min(dp[child][SELECTED], dp[child][CHILD_COVERED]));
            dp[cur][SELECTED] = Math.min(UNAVAILABLE, dp[cur][SELECTED] + toSelectedValue);

            int toNeedParentCoverValue = dp[child][CHILD_COVERED];
            dp[cur][NEED_PARENT_COVER] = Math.min(UNAVAILABLE,
                dp[cur][NEED_PARENT_COVER] + toNeedParentCoverValue);

            int toChildCoveredValue = Math.min(dp[child][CHILD_COVERED], dp[child][SELECTED]);
            dp[cur][CHILD_COVERED] = Math.min(UNAVAILABLE,
                dp[cur][CHILD_COVERED] + toChildCoveredValue);

            if (dp[child][SELECTED] <= dp[child][CHILD_COVERED]) {
                childAllNotSelected = false;
            } else {
                int curDiff = dp[child][SELECTED] == UNAVAILABLE ? UNAVAILABLE
                    : dp[child][SELECTED] - dp[child][CHILD_COVERED];
                minDiff = Math.min(minDiff, curDiff);
            }
        }

        if (!isLeaf && childAllNotSelected) {
            dp[cur][CHILD_COVERED] = Math.min(UNAVAILABLE, dp[cur][CHILD_COVERED] + minDiff);
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
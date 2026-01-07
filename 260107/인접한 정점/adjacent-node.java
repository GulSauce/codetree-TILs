import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Integer> nodeValues = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);

        st = new StringTokenizer(br.readLine());
        nodeValues.add(-1);
        for (int i = 1; i <= N; i++) {
            nodeValues.add(toInt(st));
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(N, nodeValues, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    List<Integer> nodeValues;
    List<Edge> edges;

    int[][] dp;
    List<Node> graph = new ArrayList<>();

    public Solver(int endNodeNumber, List<Integer> nodeValues, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.nodeValues = nodeValues;
        this.edges = edges;
        this.dp = new int[endNodeNumber + 1][3];
    }

    public void solve() {
        for (int v = 0; v <= endNodeNumber; v++) {
            graph.add(new Node(v, nodeValues.get(v)));
        }
        for (Edge edge : edges) {
            Node start = graph.get(edge.start);
            Node end = graph.get(edge.end);
            graph.get(start.number).children.add(end);
            graph.get(end.number).children.add(start);
        }
        final Node ROOT = graph.get(1);
        DPDFS(ROOT, new Node(-1, -1));

        int answer = Math.max(dp[ROOT.number][CHILD_COVERED], dp[ROOT.number][SELECTED]);
        System.out.println(answer);
    }

    final int UNAVAILABLE = Integer.MIN_VALUE;
    final int CHILD_COVERED = 0;
    final int SELECTED = 1;
    final int NEED_PARENT_COVER = 2;

    private void DPDFS(Node cur, Node parent) {
        int pNumber = cur.number;

        boolean isLeaf = true;
        boolean allChildIsNotSelected = true;
        int minDiff = Integer.MAX_VALUE;

        for (Node child : cur.children) {
            if (child.number == parent.number) {
                continue;
            }
            isLeaf = false;
            DPDFS(child, cur);

            int cNumber = child.number;

            int toSelectedValue = Math.max(dp[cNumber][CHILD_COVERED],
                dp[cNumber][NEED_PARENT_COVER]);
            dp[pNumber][SELECTED] =
                dp[pNumber][SELECTED] + toSelectedValue < 0 ? UNAVAILABLE
                    : dp[pNumber][SELECTED] + toSelectedValue;

            int toNeedParentCoverValue = dp[cNumber][CHILD_COVERED];
            dp[pNumber][NEED_PARENT_COVER] =
                dp[pNumber][NEED_PARENT_COVER] + toNeedParentCoverValue < 0 ? UNAVAILABLE
                    : dp[pNumber][NEED_PARENT_COVER] + toNeedParentCoverValue;

            int toChildCoveredValue = Math.max(dp[cNumber][SELECTED], dp[cNumber][CHILD_COVERED]);
            dp[pNumber][CHILD_COVERED] =
                dp[pNumber][CHILD_COVERED] + toChildCoveredValue < 0 ? UNAVAILABLE
                    : dp[pNumber][CHILD_COVERED] + toChildCoveredValue;

            if (dp[cNumber][CHILD_COVERED] <= dp[cNumber][SELECTED]) {
                allChildIsNotSelected = false;
            } else {
                minDiff = dp[cNumber][CHILD_COVERED] == UNAVAILABLE ? UNAVAILABLE
                    : Math.min(minDiff, dp[cNumber][CHILD_COVERED] - dp[cNumber][SELECTED]);
            }
        }
        if (!isLeaf && allChildIsNotSelected) {
            dp[pNumber][CHILD_COVERED] =
                minDiff == UNAVAILABLE ? UNAVAILABLE : dp[pNumber][CHILD_COVERED] - minDiff;
        }
        if (isLeaf) {
            dp[pNumber][CHILD_COVERED] = UNAVAILABLE;
        }
        dp[pNumber][SELECTED] += cur.value;
    }
}

class Node {

    int number;
    int value;
    List<Node> children = new ArrayList<>();

    public Node(int number, int value) {
        this.number = number;
        this.value = value;
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

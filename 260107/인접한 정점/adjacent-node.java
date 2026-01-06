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

    boolean[] visited;


    final int UNAVAILABLE = Integer.MIN_VALUE;
    final int CHILD_COVERED = 0;
    final int SELECTED = 1;
    final int NEED_PARENT_COVER = 2;

    int[][] dp;
    List<Node> graph = new ArrayList<>();

    public Solver(int endNodeNumber, List<Integer> nodeValues, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.nodeValues = nodeValues;
        this.edges = edges;
        this.visited = new boolean[endNodeNumber + 1];
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
        DPDFS(ROOT);

        int answer = 0;
        for (int flag = 0; flag <= 2; flag++) {
            answer = Math.max(answer, dp[ROOT.number][flag]);
        }
        System.out.println(answer);
    }

    private void DPDFS(Node cur) {
        int curNumber = cur.number;
        visited[curNumber] = true;

        boolean isLeaf = true;
        boolean isAllChildOff = true;
        int minDiff = Integer.MIN_VALUE;
        for (Node child : cur.children) {
            if (visited[child.number]) {
                continue;
            }
            isLeaf = false;
            DPDFS(child);

            int childNumber = child.number;
            int toSelectedValue = Math.max(dp[childNumber][CHILD_COVERED],
                dp[childNumber][NEED_PARENT_COVER]);
            if (toSelectedValue == UNAVAILABLE) {
                dp[curNumber][SELECTED] = UNAVAILABLE;
            } else if (dp[curNumber][SELECTED] != UNAVAILABLE) {
                dp[curNumber][SELECTED] += toSelectedValue;
            }

            int toChildCoveredValue = Math.max(dp[childNumber][SELECTED],
                dp[childNumber][CHILD_COVERED]);
            if (toSelectedValue == UNAVAILABLE) {
                dp[curNumber][CHILD_COVERED] = UNAVAILABLE;
            } else if (toChildCoveredValue != UNAVAILABLE) {
                dp[curNumber][CHILD_COVERED] += toChildCoveredValue;
            }
            if (dp[childNumber][CHILD_COVERED] <= dp[childNumber][SELECTED]) {
                isAllChildOff = false;
            } else {
                int curDiff = dp[childNumber][SELECTED] == UNAVAILABLE ? UNAVAILABLE
                    : dp[childNumber][SELECTED] - dp[childNumber][CHILD_COVERED];
                minDiff = Math.max(minDiff, curDiff);
            }

            int toNeedParentCoverValue = dp[childNumber][CHILD_COVERED];
            if (toNeedParentCoverValue == UNAVAILABLE) {
                dp[curNumber][NEED_PARENT_COVER] = UNAVAILABLE;
            } else if (dp[curNumber][NEED_PARENT_COVER] != UNAVAILABLE) {
                dp[curNumber][NEED_PARENT_COVER] += toNeedParentCoverValue;
            }
        }

        if (!isLeaf && isAllChildOff && dp[curNumber][CHILD_COVERED] != UNAVAILABLE) {
            if (minDiff == UNAVAILABLE) {
                dp[curNumber][CHILD_COVERED] = UNAVAILABLE;
            } else {
                dp[curNumber][CHILD_COVERED] += minDiff;
            }
        }

        if (isLeaf) {
            dp[curNumber][CHILD_COVERED] = UNAVAILABLE;
        }

        dp[curNumber][SELECTED] += cur.value;
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
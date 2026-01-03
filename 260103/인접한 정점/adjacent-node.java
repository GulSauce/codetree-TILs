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


    final int NOT_SELECTED = 0;
    final int SELECTED = 1;
    final int PARENT_NEEDED = 2;

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
        for (Node child : cur.children) {
            if (visited[child.number]) {
                continue;
            }
            DPDFS(child);
        }

        for (Node child : cur.children) {
            int childNumber = child.number;
            dp[curNumber][SELECTED] += Math.max(dp[childNumber][NOT_SELECTED],
                dp[childNumber][PARENT_NEEDED]);
            dp[curNumber][PARENT_NEEDED] += dp[childNumber][NOT_SELECTED];
        }

        boolean isAllChildOff = true;
        int minDiff = Integer.MAX_VALUE;
        for (Node child : cur.children) {
            int childNumber = child.number;
            int notSelectedValue = dp[childNumber][NOT_SELECTED];
            int selectedValue = dp[childNumber][SELECTED];
            if (notSelectedValue <= selectedValue) {
                isAllChildOff = false;
            } else {
                minDiff = Math.min(minDiff, notSelectedValue - selectedValue);
            }
            dp[curNumber][NOT_SELECTED] += Math.max(notSelectedValue, selectedValue);
        }
        if (isAllChildOff) {
            dp[curNumber][NOT_SELECTED] -= minDiff;
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
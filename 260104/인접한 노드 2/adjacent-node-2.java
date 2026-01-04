import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        for (int i = 0; i < N; i++) {
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
    final int NOT_SELECTED = 0;
    final int SELECTED = 1;

    List<Edge> edges;
    List<Node> graph = new ArrayList<>();
    List<Integer> nodeValues;

    List<Integer> answer = new ArrayList<>();
    boolean[] visited;
    int[][] dp;

    public Solver(int endNodeNumber, List<Integer> nodeValues, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.nodeValues = nodeValues;
        this.edges = edges;
        this.visited = new boolean[endNodeNumber + 1];
        this.dp = new int[endNodeNumber + 1][2];
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new Node(i, nodeValues.get(i)));
        }
        for (Edge edge : edges) {
            graph.get(edge.start).children.add(graph.get(edge.end));
            graph.get(edge.end).children.add(graph.get(edge.start));
        }
        Node rootNode = graph.get(1);
        DPDFS(rootNode);

        Arrays.fill(visited, false);
        setAnswerTraceBackDFS(rootNode);
        Collections.sort(answer);
        System.out.println(
            Math.max(dp[rootNode.number][NOT_SELECTED], dp[rootNode.number][SELECTED]));
        for (int number : answer) {
            System.out.print(number + " ");
        }
    }

    private void setAnswerTraceBackDFS(Node cur) {
        visited[cur.number] = true;
        int curNumber = cur.number;
        int selected = dp[curNumber][SELECTED];
        int notSelected = dp[curNumber][NOT_SELECTED];
        if (notSelected <= selected) {
            answer.add(cur.number);
        }
        for (Node child : cur.children) {
            if (visited[child.number]) {
                continue;
            }
            setAnswerTraceBackDFS(child);
        }
    }

    private void DPDFS(Node cur) {
        visited[cur.number] = true;
        int curNumber = cur.number;
        for (Node child : cur.children) {
            if (visited[child.number]) {
                continue;
            }
            DPDFS(child);
            int childNumber = child.number;
            dp[curNumber][NOT_SELECTED] += Math.max(
                dp[childNumber][SELECTED],
                dp[childNumber][NOT_SELECTED]
            );
            dp[curNumber][SELECTED] += dp[childNumber][NOT_SELECTED];
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
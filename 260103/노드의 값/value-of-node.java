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
    int answer = 0;
    int[] dp;
    boolean[] visited;

    List<Integer> nodeValues;
    List<Edge> edges;
    List<Node> graph = new ArrayList<>();

    public Solver(int endNodeNumber, List<Integer> nodeValues, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.nodeValues = nodeValues;
        this.edges = edges;
        this.dp = new int[endNodeNumber + 1];
        this.visited = new boolean[endNodeNumber + 1];
    }

    public void solve() {
        graph.add(new Node(0, 0));
        for (int v = 1; v <= endNodeNumber; v++) {
            graph.add(new Node(v, nodeValues.get(v - 1)));
        }
        for (Edge edge : edges) {
            graph.get(edge.start).linkedNodes.add(graph.get(edge.end));
            graph.get(edge.end).linkedNodes.add(graph.get(edge.start));
        }
        int rootNode = 1;
        DPDFS(graph.get(rootNode));
        System.out.println(answer);
    }

    private void DPDFS(Node cur) {
        visited[cur.number] = true;
        for (Node child : cur.linkedNodes) {
            if (visited[child.number]) {
                continue;
            }
            DPDFS(child);
            dp[cur.number] += (dp[child.number] - 1);
            answer += Math.abs(dp[child.number] - 1);
        }
        dp[cur.number] += cur.value;
    }
}

class Node {

    int number;
    int value;
    List<Node> linkedNodes = new ArrayList<>();


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
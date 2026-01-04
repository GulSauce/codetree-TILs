import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Edge> edges = new ArrayList<>();
        List<Integer> nodeValues = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        nodeValues.add(-1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nodeValues.add(toInt(st));
        }

        new Solver(N, edges, nodeValues).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    boolean[] visited;
    int answer = 0;
    int[] dp;
    int endNodeNumber;
    List<Node> graph = new ArrayList<>();
    List<Edge> edges;
    List<Integer> nodeValues;

    public Solver(int endNodeNumber, List<Edge> edges, List<Integer> nodeValues) {
        this.endNodeNumber = endNodeNumber;
        this.edges = edges;
        this.nodeValues = nodeValues;
        this.dp = new int[endNodeNumber + 1];
        this.visited = new boolean[endNodeNumber + 1];
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

        for (int v = 1; v <= endNodeNumber; v++) {
            answer = Math.max(answer, dp[v]);
        }
        System.out.println(answer);
    }

    private void DPDFS(Node cur) {
        visited[cur.number] = true;

        int firstBigSubtree = 0;
        int secondBigSubtree = 0;

        for (Node child : cur.children) {
            if (visited[child.number]) {
                continue;
            }
            DPDFS(child);
            if (firstBigSubtree < dp[child.number]) {
                secondBigSubtree = firstBigSubtree;
                firstBigSubtree = dp[child.number];
            }
            if (secondBigSubtree < dp[child.number] && dp[child.number] < firstBigSubtree) {
                secondBigSubtree = dp[child.number];
            }
        }
        answer = Math.max(answer, cur.value + firstBigSubtree + secondBigSubtree);
        dp[cur.number] += cur.value + firstBigSubtree;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int M;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<List<Integer>> graph = new ArrayList<>();
    boolean[] visited;
    int nodeCount;
    List<Edge> edges;

    public Solver(List<Edge> edges) {
        this.edges = edges;
    }

    public void solve() {
        int nodeCount = -1;
        for (Edge edge : edges) {
            nodeCount = Math.max(nodeCount, edge.start);
            nodeCount = Math.max(nodeCount, edge.end);
        }

        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
        }

        int[] inputCount = new int[nodeCount + 1];
        for (Edge edge : edges) {
            inputCount[edge.end]++;
        }

        boolean isTree = true;

        final int NOT_EXIST = -1;
        int rootNode = NOT_EXIST;
        for (int v = 1; v <= nodeCount; v++) {
            if (inputCount[v] == 0) {
                if (rootNode != NOT_EXIST) {
                    isTree = false;
                    break;
                }
                rootNode = v;
            }
        }
        if (!isTree) {
            printNotTree();
            return;
        }

        for (int v = 1; v <= nodeCount; v++) {
            if (v == rootNode) {
                continue;
            }
            if (inputCount[v] != 1) {
                isTree = false;
                break;
            }
        }
        if (!isTree) {
            printNotTree();
            return;
        }

        visited = new boolean[nodeCount + 1];
        dfs(rootNode);

        for (int v = 1; v <= nodeCount; v++) {
            if (visited[v]) {
                continue;
            }
            isTree = false;
            break;
        }
        if (!isTree) {
            printNotTree();
            return;
        }

        System.out.println(1);
    }

    private void dfs(int cur) {
        visited[cur] = true;

        List<Integer> nextEdges = graph.get(cur);
        for (int nextEdge : nextEdges) {
            dfs(nextEdge);
        }
    }

    private void printNotTree() {
        System.out.println(0);
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
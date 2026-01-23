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
        int targetA, targetB;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        st = new StringTokenizer(br.readLine());
        targetA = toInt(st);
        targetB = toInt(st);

        new Solver(N, edges, targetA, targetB).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    List<Edge> edges;

    List<List<Integer>> graph = new ArrayList<>();
    int targetA;
    int targetB;

    int[] dist;
    int[] parentOf;
    boolean[] isChild;

    public Solver(int nodeCount, List<Edge> edges, int targetA, int targetB) {
        this.nodeCount = nodeCount;
        this.edges = edges;
        this.targetA = targetA;
        this.targetB = targetB;
        this.dist = new int[nodeCount + 1];
        this.parentOf = new int[nodeCount + 1];
        this.isChild = new boolean[nodeCount + 1];
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            parentOf[edge.end] = edge.start;
        }
        lca(targetA, targetB);
    }

    private void lca(int a, int b) {
        int depthA = getDepth(a);
        int depthB = getDepth(b);

        if (depthA < depthB) {
            lca(b, a);
            return;
        }

        while (depthB < depthA) {
            depthA--;
            a = parentOf[a];
        }

        while (a != b) {
            a = parentOf[a];
            b = parentOf[b];
        }

        System.out.println(a);
    }

    private int getDepth(int node) {
        int cur = node;
        int depth = 0;
        while (true) {
            cur = parentOf[cur];
            if (cur == 0) {
                break;
            }
            depth++;
        }
        return depth;
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
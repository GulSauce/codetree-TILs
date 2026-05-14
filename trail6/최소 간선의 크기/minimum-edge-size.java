import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        int A, B;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        st = new StringTokenizer(br.readLine());
        A = toInt(st);
        B = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, edges, A, B).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    List<Edge> edges = new ArrayList<>();

    int source;
    int dest;
    int[] rootOf;

    public Solver(int nodeCount, List<Edge> edges, int source, int dest) {
        this.nodeCount = nodeCount;
        this.edges = edges;
        this.source = source;
        this.dest = dest;
        this.rootOf = new int[100_001];
    }

    public void solve() {
        for (int v = 1; v <= nodeCount; v++) {
            rootOf[v] = v;
        }

        Collections.sort(edges, Comparator.reverseOrder());

        for (Edge edge : edges) {
            union(edge.v1, edge.v2);
            if (findWithCompact(source) == findWithCompact(dest)) {
                System.out.println(edge.weight);
                System.exit(0);
            }
        }
    }

    private void union(int v1, int v2) {
        int left = findWithCompact(v1);
        int right = findWithCompact(v2);

        if (left <= right) {
            rootOf[right] = left;
        } else {
            rootOf[left] = right;
        }
    }

    private int findWithCompact(int cur) {
        int parent = rootOf[cur];
        if (parent == cur) {
            return cur;
        }

        rootOf[cur] = findWithCompact(parent);
        return rootOf[cur];
    }
}

class Edge implements Comparable<Edge> {

    int v1;
    int v2;
    int weight;

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.weight);
    }
}
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

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N; i++) {
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

    List<Edge> edges = new ArrayList<>();
    int[] rootOf;
    int[] count;

    public Solver(List<Edge> edges) {
        this.edges = edges;
        this.rootOf = new int[100_001];
        this.count = new int[100_001];
    }

    public void solve() {
        for (int v = 1; v <= 100_000; v++) {
            rootOf[v] = v;
            count[v] = 1;
        }

        for (Edge edge : edges) {
            union(edge.a, edge.b);
            System.out.println(count[findWithCompact(edge.a)]);
        }
    }

    private void union(int v1, int v2) {
        int left = findWithCompact(v1);
        int right = findWithCompact(v2);

        if (left == right) {
            return;
        }
        rootOf[left] = right;
        count[right] += count[left];
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

class Edge {

    int a;
    int b;

    public Edge(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(N, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    List<Edge> edges;

    int[] rootOf;

    public Solver(int endNodeNumber, List<Edge> edges) {
        this.rootOf = new int[100_001];
        this.endNodeNumber = endNodeNumber;
        this.edges = edges;
    }

    public void solve() {
        for (int v = 1; v <= endNodeNumber; v++) {
            rootOf[v] = v;
        }

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (findWithCompact(edge.v1) == findWithCompact(edge.v2)) {
                System.out.println(i + 1);
                System.exit(0);
            }
            union(edge.v1, edge.v2);
        }
        System.out.println("happy");
    }

    private void union(int left, int right) {
        int leftRoot = findWithCompact(left);
        int rightRoot = findWithCompact(right);

        if (leftRoot < rightRoot) {
            rootOf[rightRoot] = leftRoot;
        } else {
            rootOf[leftRoot] = rightRoot;
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

class Edge {

    int v1;
    int v2;

    public Edge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}
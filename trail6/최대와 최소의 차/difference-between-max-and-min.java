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
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNumber;
    List<Edge> edges;
    int[] rootOf;

    public Solver(int endNumber, List<Edge> edges) {
        this.endNumber = endNumber;
        this.edges = edges;
        this.rootOf = new int[100_001];
    }

    public void solve() {
        for (int v = 1; v <= endNumber; v++) {
            rootOf[v] = v;
        }
        edges.sort((a, b) -> {
            if (a.toIntType() < b.toIntType()) {
                return -1;
            }
            if (a.toIntType() == b.toIntType()) {
                return 0;
            }
            return 1;
        });

        int firstCount = 0;
        for (Edge edge : edges) {
            if (findWithCompact(edge.v1) == findWithCompact(edge.v2)) {
                continue;
            }
            union(edge.v1, edge.v2);
            firstCount += 1 ^ edge.toIntType();
        }
        int first = firstCount * firstCount;

        // 끝

        for (int v = 1; v <= endNumber; v++) {
            rootOf[v] = v;
        }
        edges.sort((a, b) -> {
            if (a.toIntType() < b.toIntType()) {
                return 1;
            }
            if (a.toIntType() == b.toIntType()) {
                return 0;
            }
            return -1;
        });
        int secondCount = 0;
        for (Edge edge : edges) {
            if (findWithCompact(edge.v1) == findWithCompact(edge.v2)) {
                continue;
            }
            union(edge.v1, edge.v2);
            secondCount += 1 ^ edge.toIntType();
        }
        int second = secondCount * secondCount;

        System.out.println(first-second);
    }

    private void union(int v1, int v2) {
        int left = findWithCompact(v1);
        int right = findWithCompact(v2);

        rootOf[left] = right;
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
    private final boolean type;

    public Edge(int v1, int v2, int typeNumber) {
        this.v1 = v1;
        this.v2 = v2;
        this.type = typeNumber == 0 ? false : true;
    }

    public int toIntType() {
        return type == false ? 0 : 1;
    }
}
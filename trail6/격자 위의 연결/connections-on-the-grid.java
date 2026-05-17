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

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col < M; col++) {
                int left = (row - 1) * M + col;
                int right = (row - 1) * M + (col + 1);
                int weight = toInt(st);
                edges.add(new Edge(left, right, weight));
            }
        }

        for (int row = 1; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= M; col++) {
                int up = (row - 1) * M + col;
                int down = row * M + col;
                int weight = toInt(st);
                edges.add(new Edge(up, down, weight));
            }
        }

        new Solver(edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<Edge> edges;
    int[] rootOf;

    public Solver(List<Edge> edges) {
        this.edges = edges;
        this.rootOf = new int[100_001];
    }

    public void solve() {
        for (int v = 1; v <= 100_000; v++) {
            rootOf[v] = v;
        }
        edges.sort((a, b) -> Integer.compare(a.w, b.w));

        int answer = 0;
        for (Edge edge : edges) {
            int v1 = edge.v1;
            int v2 = edge.v2;
            if (findWithCompact(v1) == findWithCompact(v2)) {
                continue;
            }
            answer += edge.w;
            union(v1, v2);
        }
        System.out.println(answer);
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
    int w;

    public Edge(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
}
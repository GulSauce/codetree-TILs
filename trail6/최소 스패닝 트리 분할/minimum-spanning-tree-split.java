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

    int endNodeNumber;
    int[] rootOf;
    List<Edge> edges;

    public Solver(int endNodeNumber, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.edges = edges;
        this.rootOf = new int[10_001];
    }

    public void solve() {
        for (int v = 1; v <= endNodeNumber; v++) {
            rootOf[v] = v;
        }
        edges.sort((a, b) -> Integer.compare(a.w, b.w));

        List<Edge> selectedEdges = new ArrayList<>();

        int answer = 0;
        for (Edge edge : edges) {
            if (findWithCompact(edge.v1) == findWithCompact(edge.v2)) {
                continue;
            }
            union(edge.v1, edge.v2);
            selectedEdges.add(edge);
            answer += edge.w;
        }
        selectedEdges.sort((a, b) -> Integer.compare(b.w, a.w));
        answer -= selectedEdges.get(0).w;
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

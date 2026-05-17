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
        for (int i = 1; i <= endNodeNumber; i++) {
            rootOf[i] = i;
        }
        edges.sort((o1, o2) -> Integer.compare(o1.w, o2.w));

        int answer = 0;
        for (Edge edge : edges) {
            if (findWithCompact(edge.a) == findWithCompact(edge.b)) {
                continue;
            }
            answer += edge.w;
            union(edge.a, edge.b);
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

    int a;
    int b;
    int w;

    public Edge(int a, int b, int w) {
        this.a = a;
        this.b = b;
        this.w = w;
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<String> nodeTypes = new ArrayList<>();
        nodeTypes.add("");
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nodeTypes.add(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }
        new Solver(N, nodeTypes, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    int[] rootOf;
    HashMap<Integer, String> nodeTypeHashMap;
    List<Edge> edges;

    public Solver(int endNodeNumber, List<String> nodeTypes, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.nodeTypeHashMap = new HashMap<>();
        for (int i = 1; i < nodeTypes.size(); i++) {
            nodeTypeHashMap.put(i, nodeTypes.get(i));
        }
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
            if (nodeTypeHashMap.get(edge.a).equals(nodeTypeHashMap.get(edge.b))) {
                continue;
            }

            answer += edge.w;
            union(edge.a, edge.b);
        }

        for (int v = 1; v <= endNodeNumber; v++) {
            findWithCompact(v);
        }

        int nodeNumber = rootOf[1];
        for (int v = 1; v <= endNodeNumber; v++) {
            if (nodeNumber != rootOf[v]) {
                System.out.println(-1);
                System.exit(0);
            }
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


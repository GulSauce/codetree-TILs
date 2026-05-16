import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Edge> edges = new ArrayList<>();
        int A, B, K;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        st = new StringTokenizer(br.readLine());
        A = toInt(st);
        B = toInt(st);
        K = toInt(st);

        new Solver(A, B, K, N, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int A, B, K;
    int nodeCount;
    List<Edge> edges;
    int[] rootOf;

    public Solver(int a, int b, int k, int nodeCount, List<Edge> edges) {
        A = a;
        B = b;
        K = k;
        this.nodeCount = nodeCount;
        this.edges = edges;
        this.rootOf = new int[100_001];
    }

    public void solve() {
        for (int i = 1; i <= nodeCount; i++) {
            rootOf[i] = i;
        }
        for (Edge edge : edges) {
            union(edge.v1, edge.v2);
        }
        for (int i = 1; i <= nodeCount; i++) {
            findWithCompact(i);
        }

        HashMap<Integer, Integer> countPerOuterNode = new HashMap<>();
        for (int i = 1; i <= nodeCount; i++) {
            if (rootOf[i] != findWithCompact(A) && rootOf[i] != findWithCompact(B)) {
                countPerOuterNode.compute(rootOf[i], (key, value) ->
                    value == null ? 1 : value + 1
                );
            }
        }

        List<Map.Entry<Integer, Integer>> hasManyNodes
            = new ArrayList<>(countPerOuterNode.entrySet());
        hasManyNodes.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int answer = 0;
        for (int i = 0; i < K; i++) {
            if (i == hasManyNodes.size()) {
                break;
            }
            answer += hasManyNodes.get(i).getValue();
        }
        for (int i = 1; i <= nodeCount; i++) {
            if (rootOf[i] == findWithCompact(A)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private void union(int v1, int v2) {
        int leftRoot = findWithCompact(v1);
        int rightRoot = findWithCompact(v2);

        if (leftRoot <= rightRoot) {
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
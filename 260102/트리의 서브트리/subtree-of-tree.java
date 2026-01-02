import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, R, Q;
        List<Edge> edges = new ArrayList<>();
        List<Integer> queries = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        R = toInt(st);
        Q = toInt(st);

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            queries.add(toInt(st));
        }

        new Solver(N, R, edges, queries).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    int rootNodeNumber;

    int[] dp;

    boolean[] visited;

    List<Edge> edges;
    List<Integer> queries;

    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int nodeCount, int rootNodeNumber, List<Edge> edges, List<Integer> queries) {
        this.nodeCount = nodeCount;
        this.rootNodeNumber = rootNodeNumber;
        this.edges = edges;
        this.queries = queries;
        this.dp = new int[nodeCount + 1];
        this.visited = new boolean[nodeCount + 1];
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
            graph.get(edge.end).add(edge.start);
        }
        DPDFS(rootNodeNumber);
        for (int query : queries) {
            System.out.println(dp[query]);
        }
    }

    private void DPDFS(int cur) {
        visited[cur] = true;
        for (int child : graph.get(cur)) {
            if (visited[child]) {
                continue;
            }
            DPDFS(child);
            dp[cur] += dp[child];
        }
        dp[cur]++;
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
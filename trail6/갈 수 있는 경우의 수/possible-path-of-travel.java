import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        m = toInt(st);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(n, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;

    int[] dp;
    int[] indegree;

    List<Edge> edges;
    List<List<Integer>> graph = new ArrayList<>();

    final int R = 1_000_000_007;

    public Solver(int endNodeNumber, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.dp = new int[endNodeNumber + 1];
        this.indegree = new int[endNodeNumber + 1];
        this.edges = edges;
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            indegree[edge.v2]++;
            graph.get(edge.v1).add(edge.v2);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int v = 1; v <= endNodeNumber; v++) {
            if (indegree[v] == 0) {
                q.add(v);
            }
        }

        dp[1] = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                dp[near] = (dp[near] % R + dp[cur] % R) % R;
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }

        System.out.println(dp[endNodeNumber] % R);
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
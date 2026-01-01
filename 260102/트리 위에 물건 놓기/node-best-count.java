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
        for (int i = 0; i < N - 1; i++) {
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
    int[][] dp;
    boolean[] visited;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int endNodeNumber, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.edges = edges;
        this.dp = new int[endNodeNumber + 1][2];
        this.visited = new boolean[endNodeNumber + 1];
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
            graph.get(edge.end).add(edge.start);
        }

        int ROOT_NUMBER = 1;
        DPDFS(ROOT_NUMBER);
        System.out.println(Math.min(dp[ROOT_NUMBER][0], dp[ROOT_NUMBER][1]));
    }

    private void DPDFS(int cur) {
        visited[cur] = true;
        List<Integer> children = graph.get(cur);
        for (int child : children) {
            if (visited[child]) {
                continue;
            }
            DPDFS(child);
            dp[cur][1] += Math.min(dp[child][0], dp[child][1]);
            dp[cur][0] += dp[child][1];
        }
        dp[cur][1]++;
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
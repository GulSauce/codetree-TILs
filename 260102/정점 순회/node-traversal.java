import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, S, D;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        S = toInt(st);
        D = toInt(st);

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(N, S, D, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    int startNodeNumber;
    int coloredDist;

    boolean[] visited;
    int[] dp;
    List<Edge> edges;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int endNodeNumber, int startNodeNumber, int coloredDist, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.startNodeNumber = startNodeNumber;
        this.coloredDist = coloredDist;
        this.edges = edges;
        this.visited = new boolean[endNodeNumber + 1];
        this.dp = new int[endNodeNumber + 1];
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
            graph.get(edge.end).add(edge.start);
        }

        DPDFS(startNodeNumber);

        int answer = 0;
        for (int v = 1; v <= endNodeNumber; v++) {
            if (v == startNodeNumber) {
                continue;
            }
            if (dp[v] >= coloredDist) {
                answer++;
            }
        }
        System.out.println(answer * 2);
    }

    private void DPDFS(int cur) {
        visited[cur] = true;
        for (int child : graph.get(cur)) {
            if (visited[child]) {
                continue;
            }
            DPDFS(child);
            dp[cur] = Math.max(dp[cur], dp[child] + 1);
        }
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
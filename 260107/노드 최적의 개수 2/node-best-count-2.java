import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Edge> edges = new ArrayList<>();
        List<Integer> putNodeNumbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            putNodeNumbers.add(toInt(st));
        }

        new Solver(N, edges, putNodeNumbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    int[][] dp;

    List<Edge> edges;
    List<Integer> putNodeNumbers;
    List<List<Integer>> graph = new ArrayList<>();

    HashSet<Integer> putNodeNumberHashSet;

    public Solver(int endNodeNumber, List<Edge> edges, List<Integer> putNodeNumbers) {
        this.endNodeNumber = endNodeNumber;
        this.edges = edges;
        this.putNodeNumbers = putNodeNumbers;
        this.dp = new int[endNodeNumber + 1][2];
        this.putNodeNumberHashSet = new HashSet<>(putNodeNumbers);
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
            graph.get(edge.end).add(edge.start);
        }

        DPDFS(1, -1);
        System.out.println(Math.min(dp[1][SELECTED], dp[1][NOT_SELECTED]));
    }

    final int UNAVAILABLE = 100_000_000;
    final int NOT_SELECTED = 0;
    final int SELECTED = 1;

    private void DPDFS(int cur, int parent) {
        for (int child : graph.get(cur)) {
            if (child == parent) {
                continue;
            }

            DPDFS(child, cur);

            int toSelectedValue = Math.min(dp[child][SELECTED], dp[child][NOT_SELECTED]);
            dp[cur][SELECTED] = Math.min(UNAVAILABLE, dp[cur][SELECTED] + toSelectedValue);

            if (putNodeNumberHashSet.contains(cur)) {
                continue;
            }

            int toNotSelectedValue = dp[child][SELECTED];
            dp[cur][NOT_SELECTED] = Math.min(UNAVAILABLE,
                dp[cur][NOT_SELECTED] + toNotSelectedValue);
        }

        if (!putNodeNumberHashSet.contains(cur)) {
            dp[cur][SELECTED]++;
        } else {
            dp[cur][NOT_SELECTED] = UNAVAILABLE;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    int[] indegree;
    int[] dp;

    List<Edge> edges;
    List<List<Integer>> graph = new ArrayList<>();

    HashMap<Integer, LargestCount> memo = new HashMap<>();

    public Solver(int endNodeNumber, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.indegree = new int[endNodeNumber + 1];
        this.dp = new int[endNodeNumber + 1];
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
                dp[v] = 1;
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                indegree[near]--;
                memo.compute(near, (k, v) -> {
                    int curValue = dp[cur];
                    if (v == null) {
                        return new LargestCount(curValue, 1);
                    }
                    if (v.value == curValue) {
                        v.count++;
                    }
                    if (v.value < curValue) {
                        v.value = curValue;
                        v.count = 1;
                    }
                    return v;
                });
                if (indegree[near] == 0) {
                    if (memo.get(near).count > 1) {
                        dp[near] = memo.get(near).value + 1;
                    } else {
                        dp[near] = memo.get(near).value;
                    }
                    q.add(near);
                }
            }
        }

        int answer = 0;
        for (int v = 1; v <= endNodeNumber; v++) {
            answer = Math.max(answer, dp[v]);
        }
        System.out.println(answer);
    }
}

class LargestCount {

    int value;
    int count;

    public LargestCount(int value, int count) {
        this.value = value;
        this.count = count;
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

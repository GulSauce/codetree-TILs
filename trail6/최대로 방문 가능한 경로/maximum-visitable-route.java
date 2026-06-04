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
    int[] prevNumberOf;
    List<Edge> edges;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int endNodeNumber, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.dp = new int[endNodeNumber + 1];
        this.indegree = new int[endNodeNumber + 1];
        this.prevNumberOf = new int[endNodeNumber + 1];
        this.edges = edges;
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            indegree[edge.x]++;
            graph.get(edge.y).add(edge.x);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int v = 1; v <= endNodeNumber; v++) {
            if (indegree[v] == 0) {
                q.add(v);
            }
        }

        dp[endNodeNumber] = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                if (dp[cur] != 0) {
                    if (dp[near] < dp[cur] + 1) {
                        prevNumberOf[near] = cur;
                        dp[near] = dp[cur] + 1;
                    } else if (dp[near] == dp[cur] + 1) {
                        if (cur < prevNumberOf[near]) {
                            prevNumberOf[near] = cur;
                        }
                    }
                }
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }

        if (prevNumberOf[1] == 0) {
            System.out.println(-1);
            System.exit(0);
        }

        Queue<Integer> answer = new LinkedList<>();
        answer.add(1);
        int prevNumber = prevNumberOf[1];
        while (prevNumber != 0) {
            answer.add(prevNumber);
            prevNumber = prevNumberOf[prevNumber];
        }

        System.out.println(dp[1]);
        while (!answer.isEmpty()) {
            System.out.print(answer.poll() + " ");
        }
    }
}

class Edge {

    int x;
    int y;

    public Edge(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

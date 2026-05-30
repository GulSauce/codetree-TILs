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

    int manCount;
    int[] indegree;
    List<Edge> edges;

    HashMap<Integer, List<Integer>> graph = new HashMap<>();

    public Solver(int manCount, List<Edge> edges) {
        this.manCount = manCount;
        this.indegree = new int[manCount + 1];
        this.edges = edges;
    }

    public void solve() {
        for (Edge edge : edges) {
            graph.computeIfAbsent(edge.a, k -> new ArrayList<>()).add(edge.b);
            graph.computeIfAbsent(edge.b, k -> new ArrayList<>());
            indegree[edge.b]++;
        }

        int searchedNodeCount = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int key : graph.keySet()) {
            if (indegree[key] == 0) {
                q.add(key);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            searchedNodeCount++;
            List<Integer> nears = graph.getOrDefault(cur, new ArrayList<>());
            for (int near : nears) {
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }

        System.out.println(searchedNodeCount == graph.size() ? "Consistent" : "Inconsistent");
    }
}

class Edge {

    int a;
    int b;

    public Edge(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
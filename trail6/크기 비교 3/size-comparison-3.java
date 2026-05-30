import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
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

    List<Edge> edges;
    HashMap<Integer, List<Integer>> graph = new HashMap<>();

    public Solver(int endNodeNumber, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.indegree = new int[endNodeNumber + 1];
        this.edges = edges;
    }

    public void solve() {
        for (int v = 1; v <= endNodeNumber; v++) {
            graph.put(v, new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.a).add(edge.b);
            indegree[edge.b]++;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int v = 1; v <= endNodeNumber; v++) {
            if (indegree[v] == 0) {
                q.add(v);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur + " ");
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }
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
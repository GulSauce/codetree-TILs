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

    int manCount;
    int[] indegree;
    List<Edge> edges;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int manCount, List<Edge> edges) {
        this.manCount = manCount;
        this.indegree = new int[manCount + 1];
        this.edges = edges;
    }

    public void solve() {
        for (int v = 0; v <= manCount; v++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            indegree[edge.small]++;
            graph.get(edge.big).add(edge.small);
        }

        int start = -1;
        for (int v = 1; v <= manCount; v++) {
            if (indegree[v] == 0) {
                start = v;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
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

    int big;
    int small;

    public Edge(int v1, int v2) {
        this.big = v1;
        this.small = v2;
    }
}
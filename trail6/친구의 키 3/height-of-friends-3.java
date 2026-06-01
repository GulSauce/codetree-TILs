import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
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
        for (int i = 0; i <= manCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.v1).add(edge.v2);
            indegree[edge.v2]++;
        }

        List<Integer> answer = new ArrayList<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int v = 1; v <= manCount; v++) {
            if (indegree[v] == 0) {
                q.add(v);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            answer.add(cur);
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }
        if (answer.size() != manCount) {
            System.out.println(-1);
            System.exit(0);
        }
        for (int value : answer) {
            System.out.print(value + " ");
        }
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
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
    List<Edge> edges;

    public Solver(int manCount, List<Edge> edges) {
        this.manCount = manCount;
        this.edges = edges;
    }

    public void solve() {
        int left = 0;
        int right = edges.size() - 1;
        int answer = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isCycle(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer == -1 ? "Consistent" : answer + 1);
    }

    private boolean isCycle(int end) {
        int[] indegree = new int[manCount + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= manCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i <= end; i++) {
            Edge edge = edges.get(i);
            indegree[edge.b]++;
            graph.get(edge.a).add(edge.b);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int v = 1; v <= manCount; v++) {
            if (indegree[v] == 0) {
                q.add(v);
            }
        }

        int count = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            count++;
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }

        return count < manCount;
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
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

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Edge> edgeList = new ArrayList<>();
        n = toInt(st);
        m = toInt(st);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(n, edgeList).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    int[] indegree;

    List<List<Integer>> graph = new ArrayList<>();
    List<Edge> edgeList;

    public Solver(int nodeCount, List<Edge> edgeList) {
        this.nodeCount = nodeCount;
        this.edgeList = edgeList;
        this.indegree = new int[nodeCount + 1];
    }

    public void solve() {
        for (int v = 0; v <= nodeCount; v++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edgeList) {
            graph.get(edge.v1).add(edge.v2);
            indegree[edge.v2]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int v = 1; v <= nodeCount; v++) {
            if (indegree[v] == 0) {
                q.add(v);
            }
        }

        int searchCount = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            searchCount++;
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }

        System.out.println(searchCount == nodeCount ? "Not Exists" : "Exists");
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
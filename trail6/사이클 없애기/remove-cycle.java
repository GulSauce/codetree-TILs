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
        int n, m1, m2;
        List<Edge> directEdges = new ArrayList<>();
        List<Edge> undirectEdges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        m1 = toInt(st);
        m2 = toInt(st);

        for (int i = 0; i < m1; i++) {
            st = new StringTokenizer(br.readLine());
            directEdges.add(new Edge(toInt(st), toInt(st)));
        }
        for (int i = 0; i < m2; i++) {
            st = new StringTokenizer(br.readLine());
            undirectEdges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(n, directEdges, undirectEdges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    int[] indegree;
    List<Edge> directEdges;
    List<Edge> undirectEdges;

    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int nodeCount, List<Edge> directEdges, List<Edge> undirectEdges) {
        this.nodeCount = nodeCount;
        this.indegree = new int[nodeCount + 1];
        this.directEdges = directEdges;
        this.undirectEdges = undirectEdges;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : directEdges) {
            graph.get(edge.v1).add(edge.v2);
            indegree[edge.v2]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int v = 1; v <= nodeCount; v++) {
            if (indegree[v] == 0) {
                q.add(v);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }

        for (int v = 1; v <= nodeCount; v++) {
            if (indegree[v] != 0) {
                System.out.println("No");
                System.exit(0);
            }
        }
        System.out.println("Yes");
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
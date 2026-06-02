import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
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

    int nodeCount;
    int[] indegree;

    List<Edge> edges;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int nodeCount, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.indegree = new int[nodeCount + 1];
        this.edges = edges;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.y).add(edge.x);
            indegree[edge.x]++;
        }

        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int i = 1; i <= nodeCount; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int curNumber = nodeCount;
        HashMap<Integer, Integer> answer = new HashMap<>();
        while (!q.isEmpty()) {
            int cur = q.poll();
            answer.put(cur, curNumber);
            curNumber--;
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }

        if (answer.size() < nodeCount) {
            System.out.println(-1);
            System.exit(0);
        }

        List<Entry<Integer, Integer>> sortedAnswer = new ArrayList<>(answer.entrySet());
        sortedAnswer.sort((a, b) -> Integer.compare(a.getKey(), b.getKey()));
        for (Entry<Integer, Integer> value : sortedAnswer) {
            System.out.print(value.getValue() + " ");
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
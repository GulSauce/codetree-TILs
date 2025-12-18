import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }

        br.close();

        new Solver(N, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int nodeCount;
    List<Edge> edges;
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public Solver(int nodeCount, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.edges = edges;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(new Node(edge.end, edge.weight));
        }

        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> {
                return Integer.compare(a.weight, b.weight);
            }
        );

        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.number] != NOT_ALLOCATED) {
                continue;
            }
            dist[cur.number] = cur.weight;
            ArrayList<Node> nearNodes = graph.get(cur.number);
            for (Node node : nearNodes) {
                int distToNode = cur.weight + node.weight;
                pq.add(new Node(cur.number, distToNode));
            }
        }
        for (int i = 1; i <= nodeCount; i++) {
            System.out.println(dist[i] == NOT_ALLOCATED ? -1 : dist[i]);
        }
    }
}

class Edge {

    int start;
    int end;
    int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

class Node {

    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}
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

    int nodeCount;
    List<Edge> edges;

    public Solver(int nodeCount, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.edges = edges;
    }

    public void solve() {
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.end).add(new Node(edge.start, edge.weight));
        }

        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[nodeCount] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.add(new Node(nodeCount, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.number] != cur.weight) {
                continue;
            }
            ArrayList<Node> nearNodes = graph.get(cur.number);
            for (Node next : nearNodes) {
                int nextWeight = next.weight + dist[cur.number];
                if (dist[next.number] <= nextWeight) {
                    continue;
                }
                dist[next.number] = nextWeight;
                pq.add(new Node(next.number, nextWeight));
            }
        }

        int answer = 0;
        for (int i = 1; i <= nodeCount; i++) {
            answer = Math.max(answer, dist[i]);
        }
        System.out.println(answer);
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
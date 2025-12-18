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
        int K;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        st = new StringTokenizer(br.readLine());
        K = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }

        br.close();

        new Solver(N, K, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    int targetNumber;
    List<Edge> edges;
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public Solver(int nodeCount, int targetNumber, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.targetNumber = targetNumber;
        this.edges = edges;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(new Node(edge.end, edge.weight));
            graph.get(edge.end).add(new Node(edge.start, edge.weight));
        }

        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        dist[targetNumber] = 0;
        pq.add(new Node(targetNumber, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.number] != cur.weight) {
                continue;
            }
            ArrayList<Node> nearNodes = graph.get(cur.number);
            for (Node next : nearNodes) {
                int newWeight = dist[cur.number] + next.weight;
                if (dist[next.number] <= newWeight) {
                    continue;
                }
                dist[next.number] = newWeight;
                pq.add(new Node(next.number, newWeight));
            }
        }
        for (int i = 1; i <= nodeCount; i++) {
            System.out.println(dist[i] == NOT_ALLOCATED ? -1 : dist[i]);
        }
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
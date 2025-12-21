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
        int A, B, C;
        List<Edge> edges = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);

        st = new StringTokenizer(br.readLine());
        A = toInt(st);
        B = toInt(st);
        C = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }
        br.close();

        new Solver(N, edges, A, B, C).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    int nodeCount;
    List<Edge> edges;
    int A, B, C;

    public Solver(int nodeCount, List<Edge> edges, int a, int b, int c) {
        this.nodeCount = nodeCount;
        this.edges = edges;
        A = a;
        B = b;
        C = c;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(new Node(edge.end, edge.weight));
            graph.get(edge.end).add(new Node(edge.start, edge.weight));
        }

        int[] distA = dijkstra(A);
        int[] distB = dijkstra(B);
        int[] distC = dijkstra(C);

        int answer = 0;
        for (int v = 1; v <= nodeCount; v++) {
            int maxDist = getMinTriple(distA[v], distB[v], distC[v]);
            answer = Math.max(answer, maxDist);
        }

        System.out.println(answer);
    }

    private int getMinTriple(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private int[] dijkstra(int start) {
        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.weight != dist[cur.number]) {
                continue;
            }

            ArrayList<Node> nearNodes = graph.get(cur.number);
            for (Node node : nearNodes) {
                int nextWeight = node.weight + dist[cur.number];
                if (dist[node.number] <= nextWeight) {
                    continue;
                }
                dist[node.number] = nextWeight;
                pq.add(new Node(node.number, nextWeight));
            }
        }

        return dist;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Edge> edges = new ArrayList<>();
        int start, end;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }

        st = new StringTokenizer(br.readLine());
        start = toInt(st);
        end = toInt(st);

        new Solver(N, edges, start, end).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int nodeCount;
    List<Edge> edges;
    int start;
    int end;

    public Solver(int nodeCount, List<Edge> edges, int start, int end) {
        this.nodeCount = nodeCount;
        this.edges = edges;
        this.start = start;
        this.end = end;
    }

    public void solve() {
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            graph.get(edge.start).add(new Node(edge.end, edge.weight));
            graph.get(edge.end).add(new Node(edge.start, edge.weight));
        }

        int[] dist = new int[nodeCount + 1];
        int[] prevNodeOf = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.add(new Node(start, 0));
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
                prevNodeOf[next.number] = cur.number;
                pq.add(new Node(next.number, nextWeight));
            }
        }
        Stack<Integer> pathStack = new Stack<>();

        int cur = end;
        pathStack.add(cur);
        while (cur != start) {
            cur = prevNodeOf[cur];
            pathStack.add(cur);
        }
        System.out.println(dist[end]);
        while (!pathStack.isEmpty()) {
            System.out.print(pathStack.pop() + " ");
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
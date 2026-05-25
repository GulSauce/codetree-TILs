import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m;
        List<Edge> edgeList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        m = toInt(st);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(n, edgeList).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    final int NOT_ALLOCATED = Integer.MAX_VALUE;

    int[] dist;
    boolean[] intMST;

    List<Edge> edgeList;

    HashMap<Integer, List<Node>> graph = new HashMap<>();

    public Solver(int nodeCount, List<Edge> edgeList) {
        this.nodeCount = nodeCount;
        this.edgeList = edgeList;
        this.dist = new int[nodeCount + 1];
        this.intMST = new boolean[nodeCount + 1];
    }

    public void solve() {
        for (Edge edge : edgeList) {
            graph.computeIfAbsent(edge.v1, k -> new ArrayList<>()).add(new Node(edge.v2, edge.w));
            graph.computeIfAbsent(edge.v2, k -> new ArrayList<>()).add(new Node(edge.v1, edge.w));
        }

        PriorityQueue<Node> nodes = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.weight, b.weight));
        nodes.add(new Node(1, 0));
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[1] = 0;
        int answer = 0;
        while (!nodes.isEmpty()) {
            Node cur = nodes.poll();
            if (intMST[cur.number]) {
                continue;
            }
            intMST[cur.number] = true;
            List<Node> near = graph.get(cur.number);
            for (Node node : near) {
                if (intMST[node.number]) {
                    continue;
                }
                int weight = node.weight;
                dist[node.number] = Math.min(dist[node.number], weight);
                nodes.add(new Node(node.number, weight));
            }
        }

        for (int v = 1; v <= nodeCount; v++) {
            answer += dist[v];
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

    int v1;
    int v2;
    int w;

    public Edge(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
}
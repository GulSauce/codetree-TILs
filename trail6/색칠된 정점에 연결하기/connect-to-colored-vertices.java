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
        int N, M, K;
        List<Integer> colorNodeNumbers = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);
        K = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            colorNodeNumbers.add(toInt(st));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, colorNodeNumbers, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    boolean[] inMST;

    int nodeCount;
    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int[] dist;

    List<Integer> colorNodeNumbers;
    List<Edge> edges;
    List<List<Node>> graph = new ArrayList<>();

    public Solver(int nodeCount, List<Integer> colorNodeNumbers, List<Edge> edges) {
        this.inMST = new boolean[nodeCount + 1];
        this.nodeCount = nodeCount;
        this.dist = new int[nodeCount + 1];
        this.colorNodeNumbers = colorNodeNumbers;
        this.edges = edges;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < colorNodeNumbers.size() - 1; i++) {
            int prev = colorNodeNumbers.get(i);
            int next = colorNodeNumbers.get(i + 1);
            graph.get(prev).add(new Node(next, 0));
            graph.get(next).add(new Node(prev, 0));
        }

        for (Edge edge : edges) {
            int v1 = edge.v1;
            int v2 = edge.v2;
            int w = edge.w;

            graph.get(v1).add(new Node(v2, w));
            graph.get(v2).add(new Node(v1, w));
        }

        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        q.add(new Node(1, 0));
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[1] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (inMST[cur.number]) {
                continue;
            }

            inMST[cur.number] = true;
            List<Node> nears = graph.get(cur.number);
            for (Node near : nears) {
                if (inMST[near.number]) {
                    continue;
                }
                int nW = near.weight;
                if (dist[near.number] <= nW) {
                    continue;
                }
                dist[near.number] = nW;
                q.add(new Node(near.number, nW));
            }
        }

        int answer = 0;
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
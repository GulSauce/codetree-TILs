import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M, K;
        List<Integer> prices = new ArrayList<>();
        prices.add(-1);
        List<Edge> blockedEdges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);
        K = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            prices.add(toInt(st));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            blockedEdges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(N, prices, blockedEdges, K).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int budget;
    int nodeCount;
    final int NOT_ALLOCATED = Integer.MAX_VALUE;

    int[] dist;
    boolean[] inMST;

    List<Integer> prices;

    List<Edge> blockedEdges;

    List<List<Node>> graph = new ArrayList<>();

    HashSet<Edge> blocked = new HashSet<>();

    public Solver(int nodeCount, List<Integer> prices, List<Edge> blockedEdges, int budget) {
        this.dist = new int[nodeCount + 2];
        this.inMST = new boolean[nodeCount + 2];
        this.nodeCount = nodeCount;
        this.prices = prices;
        this.blockedEdges = blockedEdges;
        this.budget = budget;
    }

    public void solve() {
        for (int v = 0; v <= nodeCount; v++) {
            graph.add(new ArrayList<>());
        }
        // consider an extra node
        graph.add(new ArrayList<>());
        int extraNodeNumber = graph.size() - 1;
        for (int v = 1; v <= nodeCount; v++) {
            int price = prices.get(v);
            graph.get(extraNodeNumber).add(new Node(v, price));
            graph.get(v).add(new Node(extraNodeNumber, price));
        }

        blocked.addAll(blockedEdges);

        for (int v = 1; v < nodeCount; v++) {
            if (blocked.contains(new Edge(v, v + 1))) {
                continue;
            }
            graph.get(v).add(new Node(v + 1, 0));
            graph.get(v + 1).add(new Node(v, 0));
        }

        int endNodeNumber = nodeCount;
        if (!blocked.contains(new Edge(1, endNodeNumber))) {
            graph.get(endNodeNumber).add(new Node(1, 0));
            graph.get(1).add(new Node(endNodeNumber, 0));
        }

        Arrays.fill(dist, NOT_ALLOCATED);
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        q.add(new Node(1, 0));
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
                if (dist[near.number] < near.weight) {
                    continue;
                }
                dist[near.number] = near.weight;
                q.add(near);
            }
        }

        long sum = 0;
        boolean extraNotNeeded = true;
        for (int v = 1; v <= endNodeNumber + 1; v++) {
            sum += dist[v];
            if (v <= endNodeNumber && dist[v] != 0) {
                extraNotNeeded = false;
            }
        }

        if (extraNotNeeded) {
            System.out.println(1);
        } else {
            System.out.println(budget < sum ? 0 : 1);
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

    int v1;
    int v2;

    public Edge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge e = (Edge) o;
        return (v1 == e.v1 && v2 == e.v2) || (v1 == e.v2 && v2 == e.v1);
    }

    @Override
    public int hashCode() {
        return v1 + v2;
    }
}
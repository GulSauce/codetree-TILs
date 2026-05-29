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
        List<Edge> edgeList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, edgeList).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    final int NOT_ALLOCATED = Integer.MAX_VALUE;

    int[] dist;
    int[] nodeFrom;
    boolean[] inMST;

    List<Edge> edgeList;
    List<List<Node>> tree = new ArrayList<>();
    List<List<Node>> graph = new ArrayList<>();

    public Solver(int nodeCount, List<Edge> edgeList) {
        this.nodeCount = nodeCount;
        this.edgeList = edgeList;
        this.dist = new int[nodeCount + 1];
        this.nodeFrom = new int[nodeCount + 1];
        this.inMST = new boolean[nodeCount + 1];
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edgeList) {
            graph.get(edge.v1).add(new Node(edge.v2, edge.w));
            graph.get(edge.v2).add(new Node(edge.v1, edge.w));
        }

        Arrays.fill(dist, NOT_ALLOCATED);
        dist[1] = 0;
        nodeFrom[1] = 1;
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
        q.add(new Node(1, 0));

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
                int weight = near.w;
                if (dist[near.number] < weight) {
                    continue;
                }
                dist[near.number] = weight;
                nodeFrom[near.number] = cur.number;
                q.add(new Node(near.number, weight));
            }
        }
        int distSum = 0;
        for (int v = 1; v <= nodeCount; v++) {
            distSum += dist[v];
        }
        System.out.println(distSum);

        for (int i = 0; i <= nodeCount; i++) {
            tree.add(new ArrayList<>());
        }
        for (int v1 = 1; v1 <= nodeCount; v1++) {
            int v2 = nodeFrom[v1];
            if (v1 == v2) {
                continue;
            }
            tree.get(v1).add(new Node(v2, dist[v1]));
            tree.get(v2).add(new Node(v1, dist[v1]));
        }

        int farNode1 = new TreeDistFinder(tree).find(1);
        TreeDistFinder t = new TreeDistFinder(tree);
        t.find(farNode1);
        System.out.println(t.maxDist);
    }
}

class TreeDistFinder {

    boolean[] visited;
    int maxDist = 0;
    int maxDistNumber = 0;
    List<List<Node>> tree = new ArrayList<>();

    public TreeDistFinder(List<List<Node>> tree) {
        this.visited = new boolean[100_001];
        this.tree = tree;
    }

    public int find(int node) {
        dfs(node, 0);
        return maxDistNumber;
    }

    public void dfs(int cur, int curDist) {
        visited[cur] = true;
        List<Node> nodes = tree.get(cur);
        for (Node node : nodes) {
            if (visited[node.number]) {
                continue;
            }
            int nextDist = curDist + node.w;
            if (maxDist < nextDist) {
                maxDist = nextDist;
                maxDistNumber = node.number;
            }
            dfs(node.number, nextDist);
        }
    }
}

class Node {

    int number;
    int w;

    public Node(int number, int w) {
        this.number = number;
        this.w = w;
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
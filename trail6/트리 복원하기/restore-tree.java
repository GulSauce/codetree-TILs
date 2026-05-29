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
        int n;
        int[][] weightSumGrid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        weightSumGrid = new int[n + 1][n + 1];

        for (int row = 1; row <= n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= n; col++) {
                weightSumGrid[row][col] = toInt(st);
            }
        }

        new Solver(n, weightSumGrid).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int gridLength;
    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    boolean[] inMST;
    int[] dist;
    int[] edgeFrom;
    int[][] weightSumGrid;

    List<List<Node>> graph = new ArrayList<>();
    List<Edge> restoredTree = new ArrayList<>();

    public Solver(int gridLength, int[][] weightSumGrid) {
        this.gridLength = gridLength;
        this.inMST = new boolean[gridLength + 1];
        this.dist = new int[gridLength + 1];
        this.edgeFrom = new int[gridLength + 1];
        this.weightSumGrid = weightSumGrid;
    }

    public void solve() {
        for (int v = 0; v <= gridLength; v++) {
            graph.add(new ArrayList<>());
        }

        for (int v = 1; v <= gridLength; v++) {
            for (int i = 1; i <= gridLength; i++) {
                int weightSum = weightSumGrid[v][i];
                if (weightSum == 0) {
                    continue;
                }
                graph.get(v).add(new Node(i, weightSum));
            }
        }
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        q.add(new Node(1, 0));
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[1] = 0;
        edgeFrom[1] = 1;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (inMST[cur.number]) {
                continue;
            } else {
                inMST[cur.number] = true;
            }
            int first;
            int second;
            if (edgeFrom[cur.number] < cur.number) {
                first = edgeFrom[cur.number];
                second = cur.number;
            } else {
                first = cur.number;
                second = edgeFrom[cur.number];
            }
            if (first != second) {
                restoredTree.add(new Edge(first, second, dist[cur.number]));
            }
            List<Node> nears = graph.get(cur.number);
            for (Node near : nears) {
                if (inMST[near.number]) {
                    continue;
                }
                int weight = near.weight;
                if (dist[near.number] < weight) {
                    continue;
                }
                dist[near.number] = weight;
                edgeFrom[near.number] = cur.number;
                q.add(new Node(near.number, weight));
            }
        }

        restoredTree.sort((a, b) -> {
            if (a.v1 < b.v1) {
                return -1;
            }
            if (a.v1 > b.v1) {
                return 1;
            }
            if (a.v2 < b.v2) {
                return -1;
            }
            if (a.v2 > b.v2) {
                return 1;
            }
            if (a.w < b.w) {
                return -1;
            }
            if (a.w > b.w) {
                return 1;
            }
            return 0;
        });

        for (Edge edge : restoredTree) {
            System.out.println(edge.v1 + " " + edge.v2 + " " + edge.w);
        }
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

class Node {

    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}

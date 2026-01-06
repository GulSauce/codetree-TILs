import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Edge> edges = new ArrayList<>();
        List<Integer> nodeValues = new ArrayList<>();
        int maxColorNode;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        nodeValues.add(-1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nodeValues.add(toInt(st));
        }
        st = new StringTokenizer(br.readLine());
        maxColorNode = toInt(st);

        new Solver(N, edges, nodeValues, maxColorNode).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    int maxColorNode;
    List<Node> graph = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();
    List<Integer> nodeValues = new ArrayList<>();

    final int NOT_ALLOCATED = Integer.MIN_VALUE;
    final int NOT_SELECTED = 0;
    final int SELECTED = 1;

    boolean[] visited;
    int[][][] dp;

    public Solver(int nodeCount, List<Edge> edges, List<Integer> nodeValues, int maxColorNode) {
        this.nodeCount = nodeCount;
        this.edges = edges;
        this.nodeValues = nodeValues;
        this.maxColorNode = maxColorNode;
        this.dp = new int[nodeCount + 1][maxColorNode + 1][2];
        this.visited = new boolean[nodeCount + 1];
    }


    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new Node(i, nodeValues.get(i)));
        }
        for (Edge edge : edges) {
            graph.get(edge.start).children.add(graph.get(edge.end));
            graph.get(edge.end).children.add(graph.get(edge.start));
        }

        for (int[][] arrays : dp) {
            for (int[] array : arrays) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
        }

        Node rootNode = graph.get(1);
        DPDFS(rootNode);

        int answer = 0;
        for (int count = 0; count <= maxColorNode; count++) {
            answer = Math.max(answer, dp[rootNode.number][count][NOT_SELECTED]);
            answer = Math.max(answer, dp[rootNode.number][count][SELECTED]);
        }
        System.out.println(answer);
    }

    private void DPDFS(Node cur) {
        visited[cur.number] = true;
        boolean leaf = true;

        Node left = null;
        Node right = null;
        for (Node child : cur.children) {
            if (visited[child.number]) {
                continue;
            }
            leaf = false;
            if (left == null) {
                left = child;
            } else {
                right = child;
            }
        }
        if (leaf) {
            dp[cur.number][1][SELECTED] = cur.weight;
            dp[cur.number][0][NOT_SELECTED] = 0;
            return;
        }

        DPDFS(left);
        DPDFS(right);
        for (int k = 0; k <= maxColorNode; k++) {
            for (int leftSelect = 0; leftSelect <= k - 1; leftSelect++) {
                int rightSelected = (k - 1) - leftSelect;
                int leftValue = dp[left.number][leftSelect][NOT_SELECTED];
                int rightValue = dp[right.number][rightSelected][NOT_SELECTED];
                if (leftValue == NOT_ALLOCATED || rightValue == NOT_ALLOCATED) {
                    continue;
                }
                dp[cur.number][k][SELECTED] = Math.max(dp[cur.number][k][SELECTED],
                    leftValue + rightValue + cur.weight);
            }

            for (int flag = 0; flag <= 3; flag++) {
                // 00 01 10 11
                int leftFlag = (flag & 2) >> 1;
                int rightFlag = flag & 1;
                for (int leftSelect = 0; leftSelect <= k; leftSelect++) {
                    int rightSelected = k - leftSelect;
                    int leftValue = dp[left.number][leftSelect][leftFlag];
                    int rightValue = dp[right.number][rightSelected][rightFlag];
                    if (leftValue == NOT_ALLOCATED || rightValue == NOT_ALLOCATED) {
                        continue;
                    }
                    dp[cur.number][k][NOT_SELECTED] = Math.max(dp[cur.number][k][NOT_SELECTED],
                        leftValue + rightValue);
                }
            }
        }
    }
}

class Node {

    int number;
    int weight;
    List<Node> children = new ArrayList<>();

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}

class Edge {

    int start;
    int end;

    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
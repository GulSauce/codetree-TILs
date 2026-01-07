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
        int K;

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
        K = toInt(st);
        new Solver(N, edges, nodeValues, K).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    int maxColorCount;

    final int UNAVAILABLE = -100_000_000;
    int[][][] dp;

    List<Edge> edges;
    List<Integer> nodeValues;
    List<Node> graph = new ArrayList<>();

    public Solver(int n, List<Edge> edges, List<Integer> nodeValues, int maxColorCount) {
        this.endNodeNumber = n;
        this.edges = edges;
        this.nodeValues = nodeValues;
        this.dp = new int[n + 1][maxColorCount + 1][2];
        this.maxColorCount = maxColorCount;
    }


    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new Node(nodeValues.get(i), i));
        }
        for (Edge edge : edges) {
            graph.get(edge.start).children.add(graph.get(edge.end));
            graph.get(edge.end).children.add(graph.get(edge.start));
        }
        for (int[][] arrays : dp) {
            for (int[] array : arrays) {
                Arrays.fill(array, UNAVAILABLE);
            }
        }

        DPDFS(graph.get(1), graph.get(0));

        int answer = 0;
        for (int i = 0; i <= maxColorCount; i++) {
            answer = Math.max(answer, dp[1][i][SELECTED]);
            answer = Math.max(answer, dp[1][i][NOT_SELECTED]);
        }
        System.out.println(answer);
    }

    final int NOT_SELECTED = 0;
    final int SELECTED = 1;

    private void DPDFS(Node cur, Node parent) {
        dp[cur.number][1][SELECTED] = cur.value;
        dp[cur.number][0][NOT_SELECTED] = 0;

        for (Node child : cur.children) {
            if (child.number == parent.number) {
                continue;
            }
            DPDFS(child, cur);
            for (int choice = maxColorCount; choice >= 0; choice--) {
                for (int childChoice = 0; childChoice <= choice; childChoice++) {
                    int childValue = Math.max(
                        dp[child.number][childChoice][SELECTED],
                        dp[child.number][childChoice][NOT_SELECTED]);

                    if (childValue == UNAVAILABLE) {
                        continue;
                    }

                    int candidateValue =
                        dp[cur.number][choice - childChoice][NOT_SELECTED] + childValue;

                    dp[cur.number][choice][NOT_SELECTED] = Math.max(
                        dp[cur.number][choice][NOT_SELECTED],
                        candidateValue < 0 ? UNAVAILABLE : candidateValue);
                }
            }

            for (int choice = maxColorCount; choice >= 0; choice--) {
                for (int childChoice = 0; childChoice <= choice; childChoice++) {

                    if (dp[child.number][childChoice][NOT_SELECTED] == UNAVAILABLE) {
                        continue;
                    }

                    int candidateValue = dp[cur.number][choice - childChoice][SELECTED]
                        + dp[child.number][childChoice][NOT_SELECTED];

                    dp[cur.number][choice][SELECTED] = Math.max(
                        dp[cur.number][choice][SELECTED],
                        candidateValue < 0 ? UNAVAILABLE : candidateValue);
                }
            }
        }
    }
}

class Node {

    int number;
    int value;
    List<Node> children = new ArrayList<>();

    public Node(int value, int number) {
        this.value = value;
        this.number = number;
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

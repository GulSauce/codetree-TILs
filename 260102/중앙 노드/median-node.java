import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, R;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        R = toInt(st);
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(N, R, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    int rootNodeNumber;

    int[] dp;
    boolean[] visited;

    List<List<Integer>> graph = new ArrayList<>();
    List<Edge> edges;

    public Solver(int nodeCount, int rootNodeNumber, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.rootNodeNumber = rootNodeNumber;
        this.edges = edges;
        this.visited = new boolean[nodeCount + 1];
        this.dp = new int[nodeCount + 1];
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
            graph.get(edge.end).add(edge.start);
        }

        int centralNodeNumber = getCentralNodeNumberBFS();
        Arrays.fill(visited, false);
        DPDFS(centralNodeNumber);

        if (graph.get(centralNodeNumber).isEmpty()) {
            System.out.println(0);
            return;
        }

        int maxSubtreeSize = 0;
        int minSubtreeSize = Integer.MAX_VALUE;
        for (int child : graph.get(centralNodeNumber)) {
            maxSubtreeSize = Math.max(dp[child], maxSubtreeSize);
            minSubtreeSize = Math.min(dp[child], minSubtreeSize);
        }
        System.out.println(maxSubtreeSize - minSubtreeSize);
    }


    private int getCentralNodeNumberBFS() {
        int centralNodeNumber = -1;
        Queue<Integer> nextNodeQueue = new LinkedList<>();
        nextNodeQueue.add(rootNodeNumber);
        while (!nextNodeQueue.isEmpty()) {
            int cur = nextNodeQueue.poll();
            visited[cur] = true;
            List<Integer> children = graph.get(cur);

            int childCount = 0;
            for (int child : children) {
                if (visited[child]) {
                    continue;
                }
                childCount++;
                nextNodeQueue.add(child);
            }
            if (childCount >= 2) {
                centralNodeNumber = cur;
                break;
            }
            if (childCount == 0) {
                centralNodeNumber = cur;
            }
        }
        return centralNodeNumber;
    }

    private void DPDFS(int cur) {
        visited[cur] = true;
        for (int child : graph.get(cur)) {
            if (visited[child]) {
                continue;
            }
            DPDFS(child);
            dp[cur] += dp[child];
        }
        dp[cur]++;
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
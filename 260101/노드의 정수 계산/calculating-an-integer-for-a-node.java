import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<NodeInfo> nodeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            nodeInfos.add(new NodeInfo(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, nodeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int N;

    int[] dp;
    List<NodeInfo> nodeInfos;
    List<Node> graph = new ArrayList<>();

    public Solver(int n, List<NodeInfo> nodeInfos) {
        N = n;
        this.nodeInfos = nodeInfos;
        this.dp = new int[n + 1];
    }

    public void solve() {
        for (int i = 0; i <= N; i++) {
            graph.add(new Node(i));
        }

        final int ROOT_NODE = 1;
        graph.get(ROOT_NODE).weight = 0;

        final int MINUS_FLAG = 0;
        final int PLUS_FLAG = 1;

        for (int v = 2; v <= N; v++) {
            NodeInfo nodeInfo = nodeInfos.get(v - 2);
            int value = nodeInfo.t == PLUS_FLAG ? nodeInfo.a : -nodeInfo.a;
            Node cur = graph.get(v);
            cur.weight = value;
            graph.get(nodeInfo.p).children.add(cur);
        }
        for (int v = 1; v <= N; v++) {
            dp[v] = graph.get(v).weight;
        }

        DPDFS(ROOT_NODE);

        System.out.println(dp[ROOT_NODE]);
    }

    private void DPDFS(int cur) {
        for (Node child : graph.get(cur).children) {
            DPDFS(child.number);
            if (dp[child.number] <= 0) {
                continue;
            }
            dp[cur] += dp[child.number];
        }
    }
}

class Node {

    int number;
    int weight;
    List<Node> children = new ArrayList<>();

    public Node(int number) {
        this.number = number;
    }
}


class NodeInfo {

    int t;
    int a;
    int p;

    public NodeInfo(int t, int a, int p) {
        this.t = t;
        this.a = a;
        this.p = p;
    }
}
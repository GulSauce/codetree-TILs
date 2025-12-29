import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    int maxDist = 0;
    int skipNumber;
    int hasMaxDistNumber = -1;

    List<GraphMakeInfo> graphMakeInfos;
    boolean[] visited;
    HashMap<Integer, List<Edge>> graph = new HashMap<>();

    public Solver(int N, List<GraphMakeInfo> graphMakeInfos) {
        endNodeNumber = N - 1;
        this.graphMakeInfos = graphMakeInfos;
    }

    public void solve() {
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            graph.computeIfAbsent(graphMakeInfo.start, (key) -> new ArrayList<>()).add(new Edge(
                graphMakeInfo.end, graphMakeInfo.weight));
            graph.computeIfAbsent(graphMakeInfo.end, (key) -> new ArrayList<>()).add(new Edge(
                graphMakeInfo.start, graphMakeInfo.weight));
        }

        int answer = 0;
        visited = new boolean[endNodeNumber + 1];
        for (Entry<Integer, List<Edge>> entry : graph.entrySet()) {
            for (Edge edge : entry.getValue()) {
                int a = entry.getKey();
                int b = edge.to;
                int treeADiaMeter = getDiameterSkippingNumber(a, b);
                int treeBDiaMeter = getDiameterSkippingNumber(b, a);
                answer = Math.max(answer, treeADiaMeter + treeBDiaMeter + edge.weight);
            }
        }
        System.out.println(answer);
    }

    private int getDiameterSkippingNumber(int a, int skipNumber) {
        this.skipNumber = skipNumber;

        Arrays.fill(visited, false);
        this.hasMaxDistNumber = a;
        this.maxDist = 0;
        dfs(a, 0);

        Arrays.fill(visited, false);
        this.maxDist = 0;
        dfs(this.hasMaxDistNumber, 0);

        return maxDist;
    }

    private void dfs(int cur, int dist) {
        visited[cur] = true;
        List<Edge> toNextNodes = graph.get(cur);
        for (Edge toNextNode : toNextNodes) {
            if (visited[toNextNode.to]) {
                continue;
            }
            if (toNextNode.to == skipNumber) {
                continue;
            }
            int nextWeight = toNextNode.weight + dist;
            if (this.maxDist <= nextWeight) {
                this.hasMaxDistNumber = toNextNode.to;
                this.maxDist = nextWeight;
            }
            dfs(toNextNode.to, nextWeight);
        }
    }
}


class Edge {

    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class GraphMakeInfo {

    int start;
    int end;
    int weight;

    public GraphMakeInfo(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
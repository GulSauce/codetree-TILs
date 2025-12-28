import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, D;
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        D = toInt(st);

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, D, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    Dist[] dists;
    boolean[] visited;
    int endNodeNumber;
    int dayMove;

    List<GraphMakeInfo> graphMakeInfos;
    List<List<Edge>> graph = new ArrayList<>();

    public Solver(int endNodeNumber, int dayMove, List<GraphMakeInfo> graphMakeInfos) {
        this.endNodeNumber = endNodeNumber;
        this.dayMove = dayMove;
        this.graphMakeInfos = graphMakeInfos;
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }

        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            graph.get(graphMakeInfo.start).add(new Edge(graphMakeInfo.end, graphMakeInfo.weight));
            graph.get(graphMakeInfo.end).add(new Edge(graphMakeInfo.start, graphMakeInfo.weight));
        }

        visited = new boolean[endNodeNumber + 1];
        dists = new Dist[endNodeNumber + 1];

        Dist greatestDist = findGreatestDist(1);
        greatestDist = findGreatestDist(greatestDist.number);
        System.out.println((greatestDist.cost + dayMove - 1) / dayMove);
    }

    private Dist findGreatestDist(final int START) {
        Arrays.fill(visited, false);
        Arrays.fill(dists, null);
        dists[START] = new Dist(START, 0, 0);
        calcDistDFS(START);

        Dist greatestDist = new Dist(-1, -1, Integer.MAX_VALUE);
        for (int v = 1; v <= endNodeNumber; v++) {
            Dist dist = dists[v];
            if (dist.isGreaterThan(greatestDist)) {
                greatestDist = dist;
            }
        }
        return greatestDist;
    }

    private void calcDistDFS(int cur) {
        visited[cur] = true;

        List<Edge> toNextNodes = graph.get(cur);
        for (Edge toNextNode : toNextNodes) {
            if (visited[toNextNode.to]) {
                continue;
            }
            int nextCount = dists[cur].edgeCount + 1;
            int nextCost = dists[cur].cost + toNextNode.weight;
            dists[toNextNode.to] = new Dist(toNextNode.to, nextCount, nextCost);
            calcDistDFS(toNextNode.to);
        }
    }
}

class Dist {

    int number;
    int edgeCount;
    int cost;

    public Dist(int number, int edgeCount, int cost) {
        this.number = number;
        this.edgeCount = edgeCount;
        this.cost = cost;
    }

    public boolean isGreaterThan(Dist other) {
        if (this.edgeCount == other.edgeCount) {
            return other.cost > this.cost;
        }
        return this.edgeCount > other.edgeCount;
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
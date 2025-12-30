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

    boolean[] visited;
    int endNodeNumber;
    int dayMove;

    List<GraphMakeInfo> graphMakeInfos;
    List<List<Edge>> graph = new ArrayList<>();

    public Solver(int endNodeNumber, int dayMove, List<GraphMakeInfo> graphMakeInfos) {
        this.endNodeNumber = endNodeNumber;
        this.dayMove = dayMove;
        this.graphMakeInfos = graphMakeInfos;
        this.visited = new boolean[endNodeNumber + 1];
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }

        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            graph.get(graphMakeInfo.start).add(new Edge(graphMakeInfo.end, graphMakeInfo.weight));
            graph.get(graphMakeInfo.end).add(new Edge(graphMakeInfo.start, graphMakeInfo.weight));
        }

        int treeDiameter = getTreeDiameter();
        System.out.println((treeDiameter + dayMove - 1) / dayMove);
    }

    FarthestDistInfo farthestDistInfo;

    private int getTreeDiameter() {
        setFarthestDistInfo(1);
        setFarthestDistInfo(farthestDistInfo.number);
        return farthestDistInfo.cost;
    }

    private void setFarthestDistInfo(final int START) {
        Arrays.fill(visited, false);
        farthestDistInfo = new FarthestDistInfo(-1, 0, 0);
        setFarthestDistInfoDFS(START, 0, 0);
    }

    private void setFarthestDistInfoDFS(int cur, int curEdgeCount, int curCost) {
        visited[cur] = true;

        List<Edge> toNextNodes = graph.get(cur);
        for (Edge toNextNode : toNextNodes) {
            if (visited[toNextNode.to]) {
                continue;
            }
            int nextEdgeCount = curEdgeCount + 1;
            int nextCost = curCost + toNextNode.weight;

            if (farthestDistInfo.isSmallerThan(nextEdgeCount, nextCost)) {
                farthestDistInfo.update(toNextNode.to, nextEdgeCount, nextCost);
            }
            setFarthestDistInfoDFS(toNextNode.to, nextEdgeCount, nextCost);
        }
    }
}

class FarthestDistInfo {

    int number;
    int edgeCount;
    int cost;

    public FarthestDistInfo(int number, int edgeCount, int cost) {
        this.number = number;
        this.edgeCount = edgeCount;
        this.cost = cost;
    }

    public boolean isSmallerThan(int edgeCount, int cost) {
        if (this.edgeCount == edgeCount) {
            return cost < this.cost;
        }
        return this.edgeCount < edgeCount;
    }

    public void update(int number, int edgeCount, int cost) {
        this.number = number;
        this.edgeCount = edgeCount;
        this.cost = cost;
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
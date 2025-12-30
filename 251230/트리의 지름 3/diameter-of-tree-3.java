import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

        new Solver(graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NO_SKIP = -1;
    boolean[] visited;
    HashMap<Integer, List<Edge>> graph = new HashMap<>();
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(List<GraphMakeInfo> graphMakeInfos) {
        this.graphMakeInfos = graphMakeInfos;
        this.visited = new boolean[100_000 + 1];
    }

    public void solve() {
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            graph.computeIfAbsent(graphMakeInfo.start, (key) -> new ArrayList<>())
                .add(new Edge(graphMakeInfo.end, graphMakeInfo.weight));
            graph.computeIfAbsent(graphMakeInfo.end, (key) -> new ArrayList<>())
                .add(new Edge(graphMakeInfo.start, graphMakeInfo.weight));
        }

        DistInfo distInfoA = getFarestDistSkipping(1, NO_SKIP);
        int a = distInfoA.number;
        DistInfo distInfoB = getFarestDistSkipping(a, NO_SKIP);
        int b = distInfoB.number;

        DistInfo secondDistA = getFarestDistSkipping(a, b);
        DistInfo secondDistB = getFarestDistSkipping(b, a);

        System.out.println(Math.max(secondDistA.dist, secondDistB.dist));
    }

    int skipNumber;
    DistInfo farthestDistInfo;

    private DistInfo getFarestDistSkipping(int start, int skipNumber) {
        this.skipNumber = skipNumber;
        this.farthestDistInfo = new DistInfo(start, 0);
        Arrays.fill(visited, false);
        setFarthestDistInfoDFS(start, 0);

        return new DistInfo(farthestDistInfo.number, farthestDistInfo.dist);
    }

    private void setFarthestDistInfoDFS(int cur, int curDist) {
        visited[cur] = true;
        List<Edge> toNextNodes = graph.get(cur);
        for (Edge toNextNode : toNextNodes) {
            if (visited[toNextNode.to]) {
                continue;
            }
            if (toNextNode.to == skipNumber) {
                continue;
            }
            int nextDist = curDist + toNextNode.weight;
            if (farthestDistInfo.dist < nextDist) {
                farthestDistInfo.update(toNextNode.to, nextDist);
            }
            setFarthestDistInfoDFS(toNextNode.to, nextDist);
        }
    }
}

class DistInfo {

    int number;
    int dist;

    public DistInfo(int number, int dist) {
        this.number = number;
        this.dist = dist;
    }

    public void update(int number, int dist) {
        this.number = number;
        this.dist = dist;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

        new Solver(N, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;

    HashSet<Integer> visitedHashSet = new HashSet<>();
    HashMap<Integer, Integer> distHashMap = new HashMap<>();
    HashMap<Integer, List<Edge>> graph = new HashMap<>();
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
    }

    public void solve() {
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            graph.computeIfAbsent(graphMakeInfo.start, (key) -> new ArrayList<>())
                .add(new Edge(graphMakeInfo.end, graphMakeInfo.weight));
            graph.computeIfAbsent(graphMakeInfo.end, (key) -> new ArrayList<>())
                .add(new Edge(graphMakeInfo.start, graphMakeInfo.weight));
        }

        Integer farest = calcDistReturningFarest(1);
        calcDistReturningFarest(farest);

        int maxDist = 0;
        for (Integer v : graph.keySet()) {
            if (distHashMap.get(v) <= maxDist) {
                continue;
            }
            maxDist = distHashMap.get(v);
        }

        System.out.println(maxDist);
    }

    private Integer calcDistReturningFarest(int start) {
        distHashMap.clear();
        visitedHashSet.clear();
        distHashMap.put(start, 0);

        calcDistDFS(start);

        Integer farestNode = null;
        int maxDist = 0;
        for (Integer v : graph.keySet()) {
            if (distHashMap.get(v) <= maxDist) {
                continue;
            }
            maxDist = distHashMap.get(v);
            farestNode = v;
        }

        return farestNode;
    }

    private void calcDistDFS(int cur) {
        visitedHashSet.add(cur);

        List<Edge> toNextNodes = graph.get(cur);
        for (Edge toNextNode : toNextNodes) {
            if (visitedHashSet.contains(toNextNode.to)) {
                continue;
            }
            distHashMap.put(toNextNode.to, distHashMap.get(cur) + toNextNode.weight);
            calcDistDFS(toNextNode.to);
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
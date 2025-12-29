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

        new Solver(graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NO_SKIP = -1;
    HashMap<Integer, Integer> distHashMap = new HashMap<>();
    HashSet<Integer> visitedHashSet = new HashSet<>();
    HashMap<Integer, List<Edge>> graph = new HashMap<>();
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(List<GraphMakeInfo> graphMakeInfos) {
        this.graphMakeInfos = graphMakeInfos;
    }

    public void solve() {
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            graph.computeIfAbsent(graphMakeInfo.start, (key) -> new ArrayList<>())
                .add(new Edge(graphMakeInfo.end, graphMakeInfo.weight));
            graph.computeIfAbsent(graphMakeInfo.end, (key) -> new ArrayList<>())
                .add(new Edge(graphMakeInfo.start, graphMakeInfo.weight));
        }

        DistInfo distInfo = findFarestDistSkipping(1, NO_SKIP);
        int a = distInfo.number;
        findFarestDistSkipping(a, NO_SKIP);
        distInfo = findFarestDistSkipping(a, NO_SKIP);
        int b = distInfo.number;

        DistInfo secondDistA = findFarestDistSkipping(a, b);
        DistInfo secondDistB = findFarestDistSkipping(b, a);

        System.out.println(Math.max(secondDistA.dist, secondDistB.dist));
    }

    private DistInfo findFarestDistSkipping(int start, int skipNumber) {
        distHashMap.clear();
        visitedHashSet.clear();
        distHashMap.put(start, 0);
        calcFarestDistDFS(start);

        int number = -1;
        int dist = 0;
        for (Integer node : visitedHashSet) {
            if (node == skipNumber) {
                continue;
            }
            Integer cur = distHashMap.get(node);
            if (cur <= dist) {
                continue;
            }
            number = node;
            dist = cur;
        }
        return new DistInfo(number, dist);
    }

    private void calcFarestDistDFS(int cur) {
        visitedHashSet.add(cur);
        List<Edge> toNextNodes = graph.get(cur);
        for (Edge toNextNode : toNextNodes) {
            if(visitedHashSet.contains(toNextNode.to)){
                continue;
            }
            int nextDist = distHashMap.get(cur) + toNextNode.weight;
            distHashMap.put(toNextNode.to, nextDist);
            calcFarestDistDFS(toNextNode.to);
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
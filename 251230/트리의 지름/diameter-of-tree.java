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

    boolean[] visited;

    List<GraphMakeInfo> graphMakeInfos;
    List<List<Edge>> tree = new ArrayList<>();

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
        this.visited = new boolean[nodeCount + 1];
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            tree.add(new ArrayList<>());
        }
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            tree.get(graphMakeInfo.start).add(new Edge(graphMakeInfo.end, graphMakeInfo.weight));
            tree.get(graphMakeInfo.end).add(new Edge(graphMakeInfo.start, graphMakeInfo.weight));
        }

        System.out.println(getTreeDist());
    }

    int maxDist;
    int numberHasMaxDist;

    private int getTreeDist() {
        int firstFarthestNumber = getFarthestNumberStartFrom(1);
        int secondFarthestNumber = getFarthestNumberStartFrom(firstFarthestNumber);
        return maxDist;
    }

    private int getFarthestNumberStartFrom(int start) {
        Arrays.fill(visited, false);
        setFarthestInfosDFS(start, 0);
        return numberHasMaxDist;
    }

    private void setFarthestInfosDFS(int cur, int curDist) {
        visited[cur] = true;
        List<Edge> myEdges = tree.get(cur);
        for (Edge next : myEdges) {
            if (visited[next.to]) {
                continue;
            }
            int nextWeight = curDist + next.weight;
            if (maxDist <= nextWeight) {
                maxDist = nextWeight;
                numberHasMaxDist = next.to;
            }
            setFarthestInfosDFS(next.to, nextWeight);
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toInt(st), toInt(st), toInt(st)));
        }
        br.close();

        new Solver(N, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NO_EDGE = -1;
    int START = 1;
    int END;
    int[][] graph;
    int nodeCount;
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
        this.END = nodeCount;
    }

    public void solve() {

        graph = new int[nodeCount + 1][nodeCount + 1];
        for (int[] array : graph) {
            Arrays.fill(array, NO_EDGE);
        }

        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            int start = graphMakeInfo.start;
            int end = graphMakeInfo.end;
            int weight = graphMakeInfo.weight;

            graph[start][end] = weight;
            graph[end][start] = weight;
        }

        DijkstraReturn dijkstraReturn = dijkstra(START);
        int normalDist = dijkstraReturn.dist[END];
        int doubleDist = 0;

        List<Integer> path = dijkstraReturn.path;
        for (int i = 1; i < path.size(); i++) {
            int start = path.get(i - 1);
            int end = path.get(i);
            int save = graph[start][end];

            graph[start][end] *= 2;
            graph[end][start] *= 2;
            DijkstraReturn cur = dijkstra(START);
            doubleDist = Math.max(doubleDist, cur.dist[END]);
            graph[start][end] = save;
            graph[end][start] = save;
        }

        System.out.println(doubleDist - normalDist);
    }

    private DijkstraReturn dijkstra(int start) {
        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);

        boolean[] visited = new boolean[nodeCount + 1];
        int[] prevOf = new int[nodeCount + 1];
        dist[start] = 0;

        for (int i = 0; i < nodeCount; i++) {
            Edge cur = new Edge(-1, NOT_ALLOCATED);
            for (int v = 1; v <= nodeCount; v++) {
                int weight = dist[v];
                if (visited[v]) {
                    continue;
                }
                if (cur.weight <= weight) {
                    continue;
                }
                cur = new Edge(v, weight);
            }

            if (cur.to == -1) {
                break;
            }
            visited[cur.to] = true;

            for (int v = 1; v <= nodeCount; v++) {
                if (graph[cur.to][v] == NO_EDGE) {
                    continue;
                }
                int nextWeight = dist[cur.to] + graph[cur.to][v];
                if (dist[v] <= nextWeight) {
                    continue;
                }
                dist[v] = nextWeight;
                prevOf[v] = cur.to;
            }
        }

        Stack<Integer> pathStack = new Stack<>();
        int cur = END;
        while (cur != start) {
            pathStack.add(cur);
            cur = prevOf[cur];
        }

        List<Integer> path = new ArrayList<>();
        while (!pathStack.isEmpty()) {
            path.add(pathStack.pop());
        }

        return new DijkstraReturn(dist, path);
    }

    private class DijkstraReturn {

        int[] dist;
        List<Integer> path;

        public DijkstraReturn(int[] dist, List<Integer> path) {
            this.dist = dist;
            this.path = path;
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
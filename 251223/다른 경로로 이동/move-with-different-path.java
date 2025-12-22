import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        new Solver(N, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    final int START = 1;
    final int END;
    final int NO_EDGE = -1;
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
            setEdge(graphMakeInfo.start, graphMakeInfo.end, graphMakeInfo.weight);
        }

        List<Integer> path = new ArrayList<>();
        int[] dist = dijkstra(END);
        int cur = START;
        path.add(cur);
        while (cur != END) {
            for (int i = 1; i <= nodeCount; i++) {
                if (dist[i] == NOT_ALLOCATED) {
                    continue;
                }
                if (dist[i] + graph[i][cur] != dist[cur]) {
                    continue;
                }
                cur = i;
                path.add(i);
                break;
            }
        }

        for (int i = 1; i < path.size(); i++) {
            int start = path.get(i - 1);
            int end = path.get(i);
            setEdge(start, end, NO_EDGE);
        }

        int[] newDist = dijkstra(START);
        int bDist = newDist[END];
        System.out.println(bDist);
    }

    private int[] dijkstra(int start) {
        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[start] = 0;

        boolean[] visited = new boolean[nodeCount + 1];
        for (int i = 0; i < nodeCount; i++) {
            Edge cur = new Edge(-1, NOT_ALLOCATED);
            for (int v = 1; v <= nodeCount; v++) {
                if (visited[v]) {
                    continue;
                }
                if (cur.value <= dist[v]) {
                    continue;
                }
                cur = new Edge(v, dist[v]);
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
            }
        }
        return dist;
    }

    private void setEdge(int start, int end, int weight) {
        graph[start][end] = weight;
        graph[end][start] = weight;
    }
}


class Edge {

    int to;
    int value;

    public Edge(int to, int value) {
        this.to = to;
        this.value = value;
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
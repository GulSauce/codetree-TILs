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
        List<Query> queries = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toInt(st), toInt(st), toInt(st)));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            queries.add(new Query(toInt(st), toInt(st)));
        }

        new Solver(N, graphMakeInfos, queries).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int lastNode;
    int[] dist;

    boolean[] visited;

    List<List<Edge>> graph = new ArrayList<>();
    List<GraphMakeInfo> graphMakeInfos;
    List<Query> queries;

    public Solver(int lastNode, List<GraphMakeInfo> graphMakeInfos, List<Query> queries) {
        this.lastNode = lastNode;
        this.graphMakeInfos = graphMakeInfos;
        this.queries = queries;
    }

    public void solve() {
        for (int i = 0; i <= lastNode; i++) {
            graph.add(new ArrayList<>());
        }
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            graph.get(graphMakeInfo.start).add(new Edge(graphMakeInfo.end, graphMakeInfo.weight));
            graph.get(graphMakeInfo.end).add(new Edge(graphMakeInfo.start, graphMakeInfo.weight));
        }

        visited = new boolean[lastNode + 1];
        dist = new int[lastNode + 1];
        for (Query query : queries) {
            Arrays.fill(visited, false);
            Arrays.fill(dist, 0);
            dfs(query.start);
            System.out.println(dist[query.end]);
        }
    }

    private void dfs(int cur) {
        visited[cur] = true;
        List<Edge> nextEdges = graph.get(cur);
        for (Edge next : nextEdges) {
            if (visited[next.to]) {
                continue;
            }
            dist[next.to] = dist[cur] + next.weight;
            dfs(next.to);
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

class Query {

    int start;
    int end;

    public Query(int start, int end) {
        this.start = start;
        this.end = end;
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
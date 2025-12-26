import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);

        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();
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
    int END;
    int nodeCount;
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.END = nodeCount;
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
    }

    public void solve() {
        List<List<Edge>> undirectedGraph = new ArrayList<>();
        for (int i = 0; i <= nodeCount; i++) {
            undirectedGraph.add(new ArrayList<>());
        }

        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            undirectedGraph.get(graphMakeInfo.start)
                .add(new Edge(graphMakeInfo.end, graphMakeInfo.weight));
            undirectedGraph.get(graphMakeInfo.end)
                .add(new Edge(graphMakeInfo.start, graphMakeInfo.weight));
        }

        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[END] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.add(new Edge(END, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.weight != dist[cur.to]) {
                continue;
            }

            List<Edge> nextEdges = undirectedGraph.get(cur.to);
            for (Edge nextEdge : nextEdges) {
                int nextWeight = nextEdge.weight + dist[cur.to];
                if (dist[nextEdge.to] <= nextWeight) {
                    continue;
                }
                dist[nextEdge.to] = nextWeight;
                pq.add(new Edge(nextEdge.to, nextWeight));
            }
        }

        int answer = 0;
        for (int i = 1; i < dist.length; i++) {
            if (i == END) {
                continue;
            }
            answer = Math.max(answer, dist[i]);
        }
        System.out.println(answer);
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
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
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toInt(st), toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final long NOT_ALLOCATED = Long.MAX_VALUE;
    final int START = 1;
    final int END;
    int nodeCount;
    List<GraphMakeInfo> graphMakeInfos;
    List<List<Edge>> graphA = new ArrayList<>();
    List<List<Edge>> graphB = new ArrayList<>();
    List<List<Edge>> graphAReverse = new ArrayList<>();
    List<List<Edge>> graphBReverse = new ArrayList<>();
    long[] distA;
    long[] distB;

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
        this.END = nodeCount;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graphA.add(new ArrayList<>());
            graphB.add(new ArrayList<>());
            graphAReverse.add(new ArrayList<>());
            graphBReverse.add(new ArrayList<>());
        }

        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            graphA.get(graphMakeInfo.start).add(new Edge(graphMakeInfo.end, graphMakeInfo.aWeight));
            graphB.get(graphMakeInfo.start).add(new Edge(graphMakeInfo.end, graphMakeInfo.bWeight));
            graphAReverse.get(graphMakeInfo.end)
                .add(new Edge(graphMakeInfo.start, graphMakeInfo.aWeight));
            graphBReverse.get(graphMakeInfo.end)
                .add(new Edge(graphMakeInfo.start, graphMakeInfo.bWeight));
        }

        distA = dijkstra(graphAReverse, END);
        distB = dijkstra(graphBReverse, END);
        long[] answer = dijkstraByTwoDist(START);
        System.out.println(answer[END]);
    }

    private long[] dijkstraByTwoDist(int start) {
        long[] dist = new long[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.weight != dist[cur.to]) {
                continue;
            }

            List<Edge> myAEdges = graphA.get(cur.to);
            List<Edge> myBEdges = graphA.get(cur.to);
            for (int i = 0; i < myAEdges.size(); i++) {
                int warningCount = 0;
                Edge myAEdge = myAEdges.get(i);
                Edge myBEdge = myBEdges.get(i);
                if (distA[myAEdge.to] + myAEdge.weight != distA[cur.to]) {
                    warningCount++;
                }
                if (distB[myBEdge.to] + myBEdge.weight != distB[cur.to]) {
                    warningCount++;
                }
                long nextWeight = dist[cur.to] + warningCount;
                if (dist[myAEdge.to] <= nextWeight) {
                    continue;
                }
                pq.add(new Edge(myAEdge.to, nextWeight));
                dist[myAEdge.to] = nextWeight;
            }
        }
        return dist;
    }

    private long[] dijkstra(List<List<Edge>> graph, int start) {
        long[] dist = new long[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.weight != dist[cur.to]) {
                continue;
            }

            List<Edge> nextEdges = graph.get(cur.to);
            for (Edge nextEdge : nextEdges) {
                long nextWeight = nextEdge.weight + dist[cur.to];
                if (dist[nextEdge.to] <= nextWeight) {
                    continue;
                }
                dist[nextEdge.to] = nextWeight;
                pq.add(new Edge(nextEdge.to, nextWeight));
            }
        }

        return dist;
    }
}

class Edge {

    int to;
    long weight;

    public Edge(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }
}

class GraphMakeInfo {

    int start;
    int end;
    int aWeight;
    int bWeight;

    public GraphMakeInfo(int start, int end, int aWeight, int bWeight) {
        this.start = start;
        this.end = end;
        this.aWeight = aWeight;
        this.bWeight = bWeight;
    }
}
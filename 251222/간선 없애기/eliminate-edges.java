import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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

    int START = 1;
    int END;
    int nodeCount;
    HashMap<Integer, Integer> removedHashMap = new HashMap<>();
    List<GraphMakeInfo> graphMakeInfos;
    List<List<Edge>> graph = new ArrayList<>();
    HashSet<Integer> minDistHashSet = new HashSet<>();

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
        this.END = nodeCount;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            insertEdge(graphMakeInfo.start, graphMakeInfo.end, graphMakeInfo.weight);
        }

        int[] dist = dijkstra();
        int normalDist = dist[END];
        minDistHashSet.add(normalDist);

        for (int v = 1; v < graph.size(); v++) {
            List<Edge> myEdges = graph.get(v);
            for (Edge edge : myEdges) {
                removedHashMap.put(v, edge.to);
                removedHashMap.put(edge.to, v);
                int[] curDist = dijkstra();
                removedHashMap.remove(v);
                removedHashMap.remove(edge.to);

                minDistHashSet.add(curDist[END]);
            }
        }

        minDistHashSet.remove(normalDist);
        System.out.println(minDistHashSet.size());
    }

    private int[] dijkstra() {
        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[START] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.add(new Edge(START, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.weight != dist[cur.to]) {
                continue;
            }

            List<Edge> myEdge = graph.get(cur.to);
            for (Edge next : myEdge) {
                final int NOT_EXIST = -1;
                int value = removedHashMap.getOrDefault(cur.to, NOT_EXIST);
                if (value == next.to) {
                    continue;
                }
                value = removedHashMap.getOrDefault(next.to, NOT_EXIST);
                if (value == cur.to) {
                    continue;
                }

                int nextWeight = next.weight + dist[cur.to];
                if (dist[next.to] <= nextWeight) {
                    continue;
                }
                dist[next.to] = nextWeight;
                pq.add(new Edge(next.to, nextWeight));
            }
        }

        return dist;
    }

    private void insertEdge(int start, int end, int weight) {
        graph.get(start).add(new Edge(end, weight));
        graph.get(end).add(new Edge(start, weight));
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
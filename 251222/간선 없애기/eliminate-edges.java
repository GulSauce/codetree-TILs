import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
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

    int START = 1;
    int END;
    int nodeCount;
    List<GraphMakeInfo> graphMakeInfos;
    List<List<Edge>> graph = new ArrayList<>();

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

        DijkstraReturn result = dijkstra(new EdgeToRemove(-1, -1));
        int[] prevOf = result.prevOf;

        List<Integer> path = new ArrayList<>();
        Stack<Integer> pathStack = new Stack<>();
        int cur = END;
        pathStack.add(cur);
        while (cur != START) {
            int prev = prevOf[cur];
            pathStack.add(prev);
            cur = prev;
        }

        while (!pathStack.isEmpty()) {
            path.add(pathStack.pop());
        }

        int answer = 0;
        int normalDist = result.dist[END];
        for (int i = 1; i < path.size(); i++) {
            DijkstraReturn curResult = dijkstra(new EdgeToRemove(path.get(i), path.get(i - 1)));
            int curDist = curResult.dist[END];
            if (normalDist != curDist) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private DijkstraReturn dijkstra(EdgeToRemove edgeToRemove) {
        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        int[] prevOf = new int[nodeCount + 1];
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
                if (edgeToRemove.equals(new EdgeToRemove(cur.to, next.to))) {
                    continue;
                }
                int nextWeight = next.weight + dist[cur.to];
                if (dist[next.to] <= nextWeight) {
                    continue;
                }
                prevOf[next.to] = cur.to;
                dist[next.to] = nextWeight;
                pq.add(new Edge(next.to, nextWeight));
            }
        }

        return new DijkstraReturn(prevOf, dist);
    }

    private void insertEdge(int start, int end, int weight) {
        graph.get(start).add(new Edge(end, weight));
        graph.get(end).add(new Edge(start, weight));
    }

    private class DijkstraReturn {

        int[] prevOf;
        int[] dist;

        public DijkstraReturn(int[] prevOf, int[] dist) {
            this.prevOf = prevOf;
            this.dist = dist;
        }
    }

    private class EdgeToRemove {

        int a;
        int b;

        public EdgeToRemove(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof EdgeToRemove)) {
                return false;
            }
            EdgeToRemove other = (EdgeToRemove) o;
            if (this.a == other.a && this.b == other.b) {
                return true;
            }
            if (this.a == other.b && this.b == other.a) {
                return true;
            }

            return false;
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
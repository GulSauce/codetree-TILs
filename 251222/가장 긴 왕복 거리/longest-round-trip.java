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
        int N, M, X;
        List<Input> inputs = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);
        X = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            inputs.add(new Input(toInt(st), toInt(st), toInt(st)));
        }

        br.close();

        new Solver(N, X, inputs).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int nodeCount;
    int destNodeNumber;
    List<Input> inputs;

    public Solver(int nodeCount, int destNodeNumber, List<Input> inputs) {
        this.nodeCount = nodeCount;
        this.destNodeNumber = destNodeNumber;
        this.inputs = inputs;
    }

    public void solve() {
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (Input input : inputs) {
            graph.get(input.start).add(new Edge(input.end, input.weight));
        }

        int[] fromDestDist = dijkstra(graph, destNodeNumber);

        List<List<Edge>> reverseGraph = new ArrayList<>();

        for (int i = 0; i <= nodeCount; i++) {
            reverseGraph.add(new ArrayList<>());
        }

        for (Input input : inputs) {
            reverseGraph.get(input.end).add(new Edge(input.start, input.weight));
        }

        int[] toDestDist = dijkstra(reverseGraph, destNodeNumber);

        int answer = 0;
        for (int i = 1; i <= nodeCount; i++) {
            answer = Math.max(answer, fromDestDist[i] + toDestDist[i]);
        }
        System.out.println(answer);
    }

    private int[] dijkstra(List<List<Edge>> graph, int start) {

        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) ->
            Integer.compare(a.weight, b.weight));
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.weight != dist[cur.to]) {
                continue;
            }

            List<Edge> nextEdges = graph.get(cur.to);
            for (Edge next : nextEdges) {
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
}

class Edge {

    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class Input {

    int start;
    int end;
    int weight;

    public Input(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
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
        List<Integer> redNumbers = new ArrayList<>();
        List<Input> inputs = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);

        st = new StringTokenizer(br.readLine());
        redNumbers.add(toInt(st));
        redNumbers.add(toInt(st));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            inputs.add(new Input(toInt(st), toInt(st), toInt(st)));
        }

        br.close();

        new Solver(N, inputs, redNumbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int nodeCount;
    List<Input> inputs;
    List<Integer> redNumbers;
    List<List<Edge>> graph = new ArrayList<>();

    public Solver(int nodeCount, List<Input> inputs, List<Integer> redNumbers) {
        this.nodeCount = nodeCount;
        this.inputs = inputs;
        this.redNumbers = redNumbers;
    }

    public void solve() {

        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (Input input : inputs) {
            graph.get(input.start).add(new Edge(input.end, input.weight));
            graph.get(input.end).add(new Edge(input.start, input.weight));
        }

        int red1Number = redNumbers.get(0);
        int red2Number = redNumbers.get(1);

        int[] red1Dist = dijkstra(red1Number);
        int[] red2Dist = dijkstra(red2Number);

        int answer = NOT_ALLOCATED;
        for (int i = 1; i <= nodeCount; i++) {
            if (redNumbers.contains(i)) {
                continue;
            }
            if (red1Dist[i] == NOT_ALLOCATED || red1Dist[red2Number] == NOT_ALLOCATED
                || red2Dist[i] == NOT_ALLOCATED) {
                continue;
            }
            int candidateDist = red1Dist[i] + red1Dist[red2Number] + red2Dist[i];
            answer = Math.min(answer, candidateDist);
        }

        System.out.println(answer == NOT_ALLOCATED ? -1 : answer);
    }

    private int[] dijkstra(int start) {

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
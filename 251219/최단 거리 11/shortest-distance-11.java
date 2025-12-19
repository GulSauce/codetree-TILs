import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Edge> edges = new ArrayList<>();
        int start;
        int end;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }
        st = new StringTokenizer(br.readLine());
        start = toInt(st);
        end = toInt(st);

        new Solver(N, edges, start, end).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NO_EDGE = 0;
    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int nodeCount;

    List<Edge> edges;
    int start;
    int end;

    public Solver(int nodeCount, List<Edge> edges, int start, int end) {
        this.nodeCount = nodeCount;
        this.edges = edges;
        this.start = start;
        this.end = end;
    }

    public void solve() {
        int[][] graph = new int[nodeCount + 1][nodeCount + 1];
        for (int[] array : graph) {
            Arrays.fill(array, NO_EDGE);
        }

        for (Edge edge : edges) {
            graph[edge.start][edge.end] = edge.weight;
            graph[edge.end][edge.start] = edge.weight;
        }

        int startNode = end;
        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[startNode] = 0;

        boolean[] visited = new boolean[nodeCount + 1];
        int[] path = new int[nodeCount + 1];
        for (int i = 0; i < nodeCount; i++) {
            Node cur = new Node(-1, NOT_ALLOCATED);
            for (int v = 1; v <= nodeCount; v++) {
                if (cur.weight <= dist[v]) {
                    continue;
                }
                if (visited[v]) {
                    continue;
                }
                cur = new Node(v, dist[v]);
            }

            if (cur.number == -1) {
                break;
            }
            visited[cur.number] = true;

            for (int v = 1; v <= nodeCount; v++) {
                if (graph[cur.number][v] == NO_EDGE) {
                    continue;
                }
                int nextWeight = graph[cur.number][v] + dist[cur.number];
                if (dist[v] <= nextWeight) {
                    continue;
                }
                dist[v] = nextWeight;
                path[v] = cur.number;
            }
        }

        int cur = start;
        Queue<Integer> answer = new LinkedList<>();
        answer.add(cur);
        while (cur != end) {
            for (int v = 1; v <= nodeCount; v++) {
                if (graph[v][cur] == NO_EDGE) {
                    continue;
                }
                int candidateWeight = dist[v] + graph[v][cur];
                if (candidateWeight != dist[cur]) {
                    continue;
                }
                answer.add(v);
                cur = v;
                break;
            }
        }
        System.out.println(dist[start]);
        while (!answer.isEmpty()) {
            System.out.print(answer.poll() + " ");
        }
    }
}

class Node {

    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}


class Edge {

    int start;
    int end;
    int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
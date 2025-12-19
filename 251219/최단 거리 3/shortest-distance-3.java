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

    final int NO_EDGE = Integer.MAX_VALUE;
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
            graph[edge.start][edge.end] = Math.min(graph[edge.start][edge.end], edge.weight);
            graph[edge.end][edge.start] = Math.min(graph[edge.start][edge.end], edge.weight);
        }

        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[start] = 0;

        boolean[] visited = new boolean[nodeCount + 1];
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
                dist[v] = Math.min(dist[v], graph[cur.number][v] + dist[cur.number]);
            }
        }

        System.out.println(dist[end]);
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
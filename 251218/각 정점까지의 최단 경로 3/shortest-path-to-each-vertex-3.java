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

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }

        br.close();

        new Solver(N, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int nodeCount;
    List<Edge> edges;

    int[][] graph;

    public Solver(int nodeCount, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.edges = edges;
        this.graph = new int[nodeCount + 1][nodeCount + 1];
    }

    public void solve() {
        for (Edge edge : edges) {
            graph[edge.start][edge.end] = edge.weight;
        }

        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[1] = 0;

        boolean[] visited = new boolean[nodeCount + 1];

        // i를 직접 사용하지 않음, 단지 한 loop마다 한 점이 결정된다
        for (int i = 0; i < nodeCount; i++) {
            Node next = new Node(-1, NOT_ALLOCATED);
            for (int nodeNumber = 1; nodeNumber <= nodeCount; nodeNumber++) {
                if (next.weight <= dist[nodeNumber]) {
                    continue;
                }
                // 이미 확정된 정점이였다
                if (visited[nodeNumber]) {
                    continue;
                }
                next = new Node(nodeNumber, dist[nodeNumber]);
            }

            // 모든 정점을 다 봤지만 진행할 수 없다
            if (next.number == -1) {
                break;
            }

            // 이 번호까지는 최소거리 확정
            visited[next.number] = true;

            // 확정된 번호 주변 정점까지의 거리 갱신
            for (int j = 1; j <= nodeCount; j++) {
                if (graph[next.number][j] == 0) {
                    continue;
                }
                int newDist = dist[next.number] + graph[next.number][j];
                dist[j] = Math.min(dist[j], newDist);
            }
        }

        for (int i = 2; i <= nodeCount; i++) {
            System.out.println(dist[i] == NOT_ALLOCATED ? -1 : dist[i]);
        }
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

class Node {

    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}
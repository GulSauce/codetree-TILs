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
        int[][] graph;
        List<Query> queries = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = toInt(st);
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            queries.add(new Query(toInt(st), toInt(st)));
        }

        new Solver(graph, queries).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int[][] graph;
    List<Query> queries = new ArrayList<>();

    public Solver(int[][] graph, List<Query> queries) {
        this.graph = graph;
        this.queries = queries;
    }

    public void solve() {
        int NODE_COUNT = graph.length - 1;

        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        int[][] dist = new int[NODE_COUNT + 1][NODE_COUNT + 1];
        for (int[] array : dist) {
            Arrays.fill(array, NOT_ALLOCATED);
        }

        for (int i = 1; i <= NODE_COUNT; i++) {
            dist[i][i] = 0;
        }
        for (int i = 1; i <= NODE_COUNT; i++) {
            for (int j = 1; j <= NODE_COUNT; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (int k = 1; k <= NODE_COUNT; k++) {
            for (int i = 1; i <= NODE_COUNT; i++) {
                for (int j = 1; j <= NODE_COUNT; j++) {
                    if (dist[i][k] == NOT_ALLOCATED || dist[k][j] == NOT_ALLOCATED) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (Query query : queries) {
            System.out.println(dist[query.start][query.end]);
        }
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
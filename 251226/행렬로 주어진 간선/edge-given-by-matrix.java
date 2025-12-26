import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        int[][] graph;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = toInt(st);
            }
        }

        new Solver(graph).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int[][] graph;

    public Solver(int[][] graph) {
        this.graph = graph;
    }

    public void solve() {
        final int NO_EDGE = 0;
        final int NODE_COUNT = graph.length - 1;

        boolean[][] edgeExist = new boolean[graph.length][graph[0].length];

        for (int i = 1; i <= NODE_COUNT; i++) {
            edgeExist[i][i] = true;
        }
        for (int i = 1; i <= NODE_COUNT; i++) {
            for (int j = 1; j <= NODE_COUNT; j++) {
                if (graph[i][j] == NO_EDGE) {
                    continue;
                }
                edgeExist[i][j] = true;
            }
        }
        
        for (int k = 1; k <= NODE_COUNT; k++) {
            for (int i = 1; i <= NODE_COUNT; i++) {
                for (int j = 1; j <= NODE_COUNT; j++) {
                    if (edgeExist[i][k] == false || edgeExist[k][j] == false) {
                        continue;
                    }
                    edgeExist[i][j] = true;
                }
            }
        }

        for (int i = 1; i <= NODE_COUNT; i++) {
            for (int j = 1; j <= NODE_COUNT; j++) {
                System.out.print(edgeExist[i][j] ? 1 : 0);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
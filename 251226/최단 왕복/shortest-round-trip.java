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
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
    }

    public void solve() {
        int[][] dist = new int[nodeCount + 1][nodeCount + 1];
        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        for (int[] array : dist) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        for (int v = 1; v <= nodeCount; v++) {
            dist[v][v] = 0;
        }
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            dist[graphMakeInfo.start][graphMakeInfo.end] = graphMakeInfo.weight;
        }

        for (int k = 1; k <= nodeCount; k++) {
            for (int i = 1; i <= nodeCount; i++) {
                for (int j = 1; j <= nodeCount; j++) {
                    if (dist[i][k] == NOT_ALLOCATED || dist[k][j] == NOT_ALLOCATED) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int v = 1; v <= nodeCount; v++) {
            for (int other = v + 1; other <= nodeCount; other++) {
                if(dist[v][other] == NOT_ALLOCATED || dist[other][v] == NOT_ALLOCATED){
                    continue;
                }
                answer = Math.min(answer, dist[v][other] + dist[other][v]);
            }
        }
        System.out.println(answer);
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
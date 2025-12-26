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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);

        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();
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
        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        int[][] dist = new int[nodeCount + 1][nodeCount + 1];
        for (int[] array : dist) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        for (int i = 1; i <= nodeCount; i++) {
            dist[i][i] = 0;
        }
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            dist[graphMakeInfo.start][graphMakeInfo.end] = Math.min(
                dist[graphMakeInfo.start][graphMakeInfo.end], graphMakeInfo.weight);
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

        for (int i = 1; i <= nodeCount; i++) {
            for (int j = 1; j <= nodeCount; j++) {
                System.out.print(dist[i][j] == NOT_ALLOCATED ? -1 : dist[i][j] + " ");
            }
            System.out.println();
        }
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
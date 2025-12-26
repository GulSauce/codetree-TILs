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
        TwoPeopleInfo twoPeopleInfo;
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        st = new StringTokenizer(br.readLine());
        twoPeopleInfo = new TwoPeopleInfo(toInt(st), toInt(st), toInt(st));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, twoPeopleInfo, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    TwoPeopleInfo twoPeopleInfo;
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(int nodeCount, TwoPeopleInfo twoPeopleInfo, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
        this.twoPeopleInfo = twoPeopleInfo;
    }

    public void solve() {
        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        int[][] dist = new int[nodeCount + 1][nodeCount + 1];
        for (int[] array : dist) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        for (int v = 1; v <= nodeCount; v++) {
            dist[v][v] = 0;
        }
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            dist[graphMakeInfo.start][graphMakeInfo.end] = graphMakeInfo.weight;
            dist[graphMakeInfo.end][graphMakeInfo.start] = graphMakeInfo.weight;
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
        int cost = Integer.MAX_VALUE;
        int aStart = twoPeopleInfo.aStart;
        int bStart = twoPeopleInfo.bStart;
        int end = twoPeopleInfo.end;
        for (int meetV = 1; meetV <= nodeCount; meetV++) {
            if (dist[aStart][meetV] == NOT_ALLOCATED || dist[bStart][meetV] == NOT_ALLOCATED
                || dist[meetV][end] == NOT_ALLOCATED) {
                continue;
            }
            int curCost = dist[aStart][meetV] + dist[bStart][meetV] + dist[meetV][end];
            cost = Math.min(cost, curCost);
        }
        System.out.println(cost == NOT_ALLOCATED ? -1 : cost);
    }
}

class TwoPeopleInfo {

    int aStart;
    int bStart;
    int end;

    public TwoPeopleInfo(int aStart, int bStart, int end) {
        this.aStart = aStart;
        this.bStart = bStart;
        this.end = end;
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
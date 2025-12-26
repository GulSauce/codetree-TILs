import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M, P, Q;
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();
        List<Query> queries = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        P = toInt(st);
        Q = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toInt(st), toInt(st), toInt(st)));
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            queries.add(new Query(toInt(st), toInt(st)));
        }

        new Solver(N, P, graphMakeInfos, queries).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    int redNodeEnd;
    List<GraphMakeInfo> graphMakeInfos;
    List<Query> queries;

    public Solver(int nodeCount, int redNodeEnd, List<GraphMakeInfo> graphMakeInfos,
        List<Query> queries) {
        this.nodeCount = nodeCount;
        this.redNodeEnd = redNodeEnd;
        this.graphMakeInfos = graphMakeInfos;
        this.queries = queries;
    }

    public void solve() {
        HashSet<Integer> redNodeHashSet = new HashSet<>();
        for (int v = 1; v <= redNodeEnd; v++) {
            redNodeHashSet.add(v);
        }

        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        int[][] dist = new int[nodeCount + 1][nodeCount + 1];
        for (int[] array : dist) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        for (int i = 1; i <= nodeCount; i++) {
            dist[i][i] = 0;
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
                    if (redNodeHashSet.contains(i) || redNodeHashSet.contains(j)
                        || redNodeHashSet.contains(k)) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        int reachableCount = 0;
        long costSum = 0;
        for (Query query : queries) {
            if (dist[query.start][query.end] == NOT_ALLOCATED) {
                continue;
            }
            reachableCount++;
            costSum += dist[query.start][query.end];
        }
        System.out.println(reachableCount);
        System.out.println(costSum);
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
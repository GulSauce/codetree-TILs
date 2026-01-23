import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Edge> edges = new ArrayList<>();
        int Q;
        List<Query> queries = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(N, edges, queries).solve();

        st = new StringTokenizer(br.readLine());
        Q = toInt(st);
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            queries.add(new Query(toInt(st), toInt(st)));
        }

        new Solver(N, edges, queries).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Query {

    int v1;
    int v2;

    public Query(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}

class Edge {

    int start;
    int end;

    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }
}


class Solver {

    int endNodeNumber;
    List<Edge> edges;
    List<Query> queries;

    List<List<Integer>> graph = new ArrayList<>();

    int MAX_POWER2 = 20;
    int[] depth;
    int[][] sparseTable;

    public Solver(int n, List<Edge> edges, List<Query> queries) {
        endNodeNumber = n;
        this.edges = edges;
        this.queries = queries;
        this.depth = new int[endNodeNumber + 1];
        this.sparseTable = new int[MAX_POWER2 + 1][endNodeNumber + 1];
    }

    public void solve() {
        for (int v = 0; v <= endNodeNumber; v++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
            graph.get(edge.end).add(edge.start);
        }
        setParentOfDFS(1, 0, 0);

        for (int h = 1; h <= MAX_POWER2; h++) {
            for (int v = 1; v <= endNodeNumber; v++) {
                int midNode = sparseTable[h - 1][v];
                sparseTable[h][v] = sparseTable[h - 1][midNode];
            }
        }

        for (Query query : queries) {
            int v1 = query.v1;
            int v2 = query.v2;

            lca(v1, v2);
        }
    }

    private void lca(int v1, int v2) {
        int v1Depth = depth[v1];
        int v2Depth = depth[v2];

        if (v1Depth < v2Depth) {
            lca(v2, v1);
            return;
        }
        
        int curPower2 = MAX_POWER2;
        int diff = v1Depth - v2Depth;
        while (curPower2 >= 0) {
            int height = 1 << curPower2;
            if (diff - height >= 0) {
                diff -= height;
                v1Depth -= height;
                v1 = sparseTable[curPower2][v1];
            }
            curPower2--;
        }

        curPower2 = MAX_POWER2;
        diff = v2Depth - v1Depth;
        while (curPower2 >= 0) {
            int height = 1 << curPower2;
            if (diff - height >= 0) {
                diff -= height;
                v2Depth -= height;
                v2 = sparseTable[curPower2][v2];
            }
            curPower2--;
        }

        for (curPower2 = MAX_POWER2; curPower2 >= 0; curPower2--) {
            if (sparseTable[curPower2][v1] != sparseTable[curPower2][v2]) {
                v1 = sparseTable[curPower2][v1];
                v2 = sparseTable[curPower2][v2];
            }
        }

        System.out.println(v1 == v2 ? v1 : sparseTable[0][v1]);
    }


    private void setParentOfDFS(int cur, int parent, int parentHeight) {
        for (int child : graph.get(cur)) {
            if (child == parent) {
                continue;
            }
            sparseTable[0][child] = cur;
            int curHeight = parentHeight + 1;
            depth[child] = curHeight;
            setParentOfDFS(child, cur, curHeight);
        }
    }
}

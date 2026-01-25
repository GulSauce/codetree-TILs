import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        List<Edge> edgeList = new ArrayList<>();
        int q;
        List<Query> queryList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList.add(new Edge(toInt(st), toInt(st)));
        }

        st = new StringTokenizer(br.readLine());
        q = toInt(st);
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            queryList.add(new Query(toInt(st), toInt(st)));
        }

        new Solver(n, edgeList, queryList).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    final int MAX_POWER_2 = 20;
    List<Edge> edgeList;
    List<Query> queryList;
    int[] height;
    int[][] parentOf;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int n, List<Edge> edgeList, List<Query> queryList) {
        this.edgeList = edgeList;
        this.queryList = queryList;
        this.endNodeNumber = n;
        this.height = new int[n + 1];
        this.parentOf = new int[MAX_POWER_2 + 1][n + 1];
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edgeList) {
            graph.get(edge.start).add(edge.end);
            graph.get(edge.end).add(edge.start);
        }
        setParentOfDFS(1, -1, 0);
        for (int i = 1; i <= MAX_POWER_2; i++) {
            for (int v = 1; v <= endNodeNumber; v++) {
                int mid = parentOf[i - 1][v];
                parentOf[i][v] = parentOf[i - 1][mid];
            }
        }

        for (Query query : queryList) {
            int lca = getLca(query.v1, query.v2);
            System.out.println(lca);
        }
    }

    private int getLca(int a, int b) {
        int heightA = height[a];
        int heightB = height[b];

        if (heightA < heightB) {
            return getLca(b, a);
        }

        int diff = heightA - heightB;
        for (int i = MAX_POWER_2; i >= 0; i--) {
            int value = (1 << i);
            if (diff - value >= 0) {
                diff -= value;
                a = parentOf[i][a];
            }
        }

        if (a == b) {
            return a;
        }

        for (int i = MAX_POWER_2; i >= 0; i--) {
            if (parentOf[i][a] != parentOf[i][b]) {
                a = parentOf[i][a];
                b = parentOf[i][b];
            }
        }

        return parentOf[0][a];
    }

    private void setParentOfDFS(int cur, int parent, int prevHeight) {
        for (int child : graph.get(cur)) {
            if (child == parent) {
                continue;
            }
            parentOf[0][child] = cur;
            height[child] = prevHeight + 1;
            setParentOfDFS(child, cur, height[child]);
        }
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

class Query {

    int v1;
    int v2;

    public Query(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}
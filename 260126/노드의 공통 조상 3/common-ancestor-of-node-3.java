import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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
            queryList.add(new Query(toInt(st), toInt(st), toInt(st)));
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
    int[] height;
    int[][] parentOf;

    List<Edge> edgeList;
    List<Query> queryList;
    List<List<Integer>> graph = new ArrayList<>();
    List<NodeInfo> nodeInfos = new ArrayList<>();

    public Solver(int n, List<Edge> edgeList, List<Query> queryList) {
        this.endNodeNumber = n;
        this.edgeList = edgeList;
        this.queryList = queryList;
        this.height = new int[endNodeNumber + 1];
        this.parentOf = new int[MAX_POWER_2 + 1][endNodeNumber + 1];
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edgeList) {
            graph.get(edge.v1).add(edge.v2);
            graph.get(edge.v2).add(edge.v1);
        }
        setParentOfDFS(1, 0, -1);
        for (int i = 1; i <= MAX_POWER_2; i++) {
            for (int v = 1; v <= endNodeNumber; v++) {
                int mid = parentOf[i - 1][v];
                parentOf[i][v] = parentOf[i - 1][mid];
            }
        }

        nodeInfos.add(new NodeInfo(0, 0));
        nodeInfos.add(new NodeInfo(0, 0));
        nodeInfos.add(new NodeInfo(0, 0));

        for (Query query : queryList) {
            int a = query.v1;
            int b = query.v2;
            int c = query.v3;

            nodeInfos.get(0).setValues(a, height[a]);
            nodeInfos.get(1).setValues(b, height[b]);
            nodeInfos.get(2).setValues(c, height[c]);

            Collections.sort(nodeInfos, (NodeInfo prev, NodeInfo cur) -> {
                return Integer.compare(cur.height, prev.height);
            });

            int newA = nodeInfos.get(0).v;
            int newB = nodeInfos.get(1).v;
            int newC = nodeInfos.get(2).v;
            int lca = getLca(newA, newB, newC);
            System.out.println(lca);
        }
    }

    private int getLca(int a, int b, int c) {
        int aHeight = height[a];
        int bHeight = height[b];
        int cHeight = height[c];

        int diffA = aHeight - cHeight;
        for (int i = MAX_POWER_2; i >= 0; i--) {
            int height = 1 << i;
            if (diffA - height >= 0) {
                diffA -= height;
                a = parentOf[i][a];
            }
        }

        int diffB = bHeight - cHeight;
        for (int i = MAX_POWER_2; i >= 0; i--) {
            int height = 1 << i;
            if (diffB - height >= 0) {
                diffB -= height;
                b = parentOf[i][b];
            }
        }
        if (a == b && b == c) {
            return a;
        }

        for (int i = MAX_POWER_2; i >= 0; i--) {
            if (!(parentOf[i][a] == parentOf[i][b] && parentOf[i][b] == parentOf[i][c])) {
                a = parentOf[i][a];
                b = parentOf[i][b];
                c = parentOf[i][c];
            }
        }

        return parentOf[0][a];
    }

    private void setParentOfDFS(int cur, int parent, int prevH) {
        int curH = prevH + 1;
        height[cur] = curH;
        for (int child : graph.get(cur)) {
            if (child == parent) {
                continue;
            }
            parentOf[0][child] = cur;
            setParentOfDFS(child, cur, curH);
        }
    }
}

class NodeInfo {

    int v;
    int height;

    public NodeInfo(int v, int height) {
        this.v = v;
        this.height = height;
    }

    public void setValues(int v, int height) {
        this.v = v;
        this.height = height;
    }
}

class Edge {

    int v1;
    int v2;

    public Edge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}

class Query {

    int v1;
    int v2;
    int v3;

    public Query(int v1, int v2, int v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }
}
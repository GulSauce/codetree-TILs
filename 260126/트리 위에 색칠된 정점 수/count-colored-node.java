import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        List<Edge> edgeList = new ArrayList<>();
        int k;
        List<Integer> coloredNodes = new ArrayList<>();
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
        k = toInt(st);
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            coloredNodes.add(toInt(st));
        }

        st = new StringTokenizer(br.readLine());
        q = toInt(st);
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            queryList.add(new Query(toInt(st), toInt(st)));
        }

        new Solver(n, edgeList, coloredNodes, queryList).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    final int MAX_POWER_2 = 20;

    int[] height;
    int[] colorNodeCountFromRoot;
    int[][] parentOf;
    List<Edge> edgeList;
    List<Integer> coloredNodes;
    HashSet<Integer> colorNodeHashSet;
    List<Query> queryList;

    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int endNodeNumber, List<Edge> edgeList, List<Integer> coloredNodes,
        List<Query> queryList) {
        this.endNodeNumber = endNodeNumber;
        this.edgeList = edgeList;
        this.queryList = queryList;
        this.coloredNodes = coloredNodes;
        this.colorNodeCountFromRoot = new int[endNodeNumber + 1];
        this.height = new int[endNodeNumber + 1];
        this.parentOf = new int[MAX_POWER_2 + 1][endNodeNumber + 1];
    }

    public void solve() {
        this.colorNodeHashSet = new HashSet<>(coloredNodes);
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edgeList) {
            graph.get(edge.v1).add(edge.v2);
            graph.get(edge.v2).add(edge.v1);
        }

        setParentOfDFS(1, 0, -1);
        if (colorNodeHashSet.contains(1)) {
            colorNodeCountFromRoot[1] = 1;
        }
        setColorNodeCountFromRootDFS(1, 0);
        for (int i = 1; i <= MAX_POWER_2; i++) {
            for (int v = 1; v <= endNodeNumber; v++) {
                int mid = parentOf[i - 1][v];
                parentOf[i][v] = parentOf[i - 1][mid];
            }
        }
        for (Query query : queryList) {
            lcaDist(query.v1, query.v2);
        }
    }

    private void setColorNodeCountFromRootDFS(int cur, int parent) {
        for (int child : graph.get(cur)) {
            if (child == parent) {
                continue;
            }
            int count = colorNodeHashSet.contains(child) ? 1 : 0;
            colorNodeCountFromRoot[child] = colorNodeCountFromRoot[cur] + count;
            setColorNodeCountFromRootDFS(child, cur);
        }
    }

    private void lcaDist(int a, int b) {
        int aLoad = a;
        int bLoad = b;

        int aHeight = height[aLoad];
        int bHeight = height[bLoad];

        if (aHeight < bHeight) {
            lcaDist(bLoad, aLoad);
            return;
        }

        int diff = aHeight - bHeight;
        for (int i = MAX_POWER_2; i >= 0; i--) {
            int value = 1 << i;
            if (diff - value >= 0) {
                diff -= value;
                aLoad = parentOf[i][aLoad];
            }
        }

        int lca;
        if (aLoad == bLoad) {
            lca = aLoad;
        } else {
            for (int i = MAX_POWER_2; i >= 0; i--) {
                if (parentOf[i][aLoad] != parentOf[i][bLoad]) {
                    aLoad = parentOf[i][aLoad];
                    bLoad = parentOf[i][bLoad];
                }
            }
            lca = parentOf[0][aLoad];
        }

        int diffA = colorNodeCountFromRoot[a] - height[parentOf[0][lca]];
        int diffB = colorNodeCountFromRoot[b] - height[parentOf[0][lca]];
        int overflow = colorNodeHashSet.contains(lca) ? 1 : 0;
        System.out.println(diffA + diffB - overflow);
    }

    private void setParentOfDFS(int cur, int parent, int prevH) {
        height[cur] = prevH + 1;
        for (int child : graph.get(cur)) {
            if (child == parent) {
                continue;
            }
            parentOf[0][child] = cur;
            setParentOfDFS(child, cur, height[cur]);
        }
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

    int v1;
    int v2;

    public Edge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}
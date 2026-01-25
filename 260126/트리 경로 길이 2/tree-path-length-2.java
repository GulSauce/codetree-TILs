import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        List<EdgeInfo> edgeInfoList = new ArrayList<>();
        int q;
        List<Query> queryList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edgeInfoList.add(new EdgeInfo(toInt(st), toInt(st), toInt(st)));
        }

        st = new StringTokenizer(br.readLine());
        q = toInt(st);
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            queryList.add(new Query(toInt(st), toInt(st)));
        }

        new Solver(n, edgeInfoList, queryList).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    final int MAX_POWER_2 = 20;

    int[] fromRootWeightSum;
    int[] height;
    int[][] parentOf;
    List<EdgeInfo> edgeInfoList;
    List<Query> queryList;

    List<List<Edge>> graph = new ArrayList<>();

    public Solver(int endNodeNumber, List<EdgeInfo> edgeInfoList, List<Query> queryList) {
        this.endNodeNumber = endNodeNumber;
        this.edgeInfoList = edgeInfoList;
        this.queryList = queryList;

        this.fromRootWeightSum = new int[endNodeNumber + 1];
        this.height = new int[endNodeNumber + 1];
        this.parentOf = new int[MAX_POWER_2 + 1][endNodeNumber + 1];
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }
        for (EdgeInfo edgeInfo : edgeInfoList) {
            graph.get(edgeInfo.v1).add(new Edge(edgeInfo.v2, edgeInfo.weight));
            graph.get(edgeInfo.v2).add(new Edge(edgeInfo.v1, edgeInfo.weight));
        }

        setParentOfDFS(1, 0, -1);
        setFromRootWeightSum(1, 0);
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

        int sum = fromRootWeightSum[a] - fromRootWeightSum[lca] + fromRootWeightSum[b]
            - fromRootWeightSum[lca];
        System.out.println(sum);
    }

    private void setFromRootWeightSum(int cur, int parent) {
        for (Edge edge : graph.get(cur)) {
            if (edge.number == parent) {
                continue;
            }
            fromRootWeightSum[edge.number] = fromRootWeightSum[cur] + edge.weight;
            setFromRootWeightSum(edge.number, cur);
        }
    }

    private void setParentOfDFS(int cur, int parent, int prevH) {
        height[cur] = prevH + 1;
        for (Edge edge : graph.get(cur)) {
            int child = edge.number;
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

    int number;
    int weight;

    public Edge(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}

class EdgeInfo {

    int v1;
    int v2;
    int weight;

    public EdgeInfo(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
}
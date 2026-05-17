import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M, K;
        List<Integer> nodeValues = new ArrayList<>();
        nodeValues.add(-1);
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        K = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nodeValues.add(toInt(st));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(N, K, nodeValues, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    int cost;
    List<Integer> nodeValues;
    List<Edge> edges;

    int[] rootOf;
    int[] minValue;

    public Solver(int nodeCount, int cost, List<Integer> nodeValues, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.cost = cost;
        this.nodeValues = nodeValues;
        this.edges = edges;
        this.rootOf = new int[100_001];
        this.minValue = new int[100_001];
    }

    public void solve() {
        for (int i = 1; i <= nodeCount; i++) {
            rootOf[i] = i;
            minValue[i] = nodeValues.get(i);
        }

        for (Edge edge : edges) {
            union(edge.v1, edge.v2);
        }

        List<Integer> sortedGroupMinValue = new ArrayList<>();
        Set<Integer> alreadyCheck = new HashSet<>();
        for (int i = 1; i <= nodeCount; i++) {
            if (alreadyCheck.contains(findWithCompact(i))) {
                continue;
            }
            sortedGroupMinValue.add(minValue[i]);
            alreadyCheck.add(findWithCompact(i));
        }

        Collections.sort(sortedGroupMinValue);

        int realCost = 0;
        for (int i = 1; i < sortedGroupMinValue.size(); i++) {
            realCost += sortedGroupMinValue.get(0) + sortedGroupMinValue.get(i);
        }

        System.out.println(realCost > cost ? "NO" : realCost);
    }

    private void union(int v1, int v2) {
        int leftRoot = findWithCompact(v1);
        int rightRoot = findWithCompact(v2);

        if (leftRoot <= rightRoot) {
            rootOf[rightRoot] = leftRoot;
            minValue[leftRoot] = Math.min(minValue[leftRoot], minValue[rightRoot]);
        } else {
            rootOf[leftRoot] = rightRoot;
            minValue[rightRoot] = Math.min(minValue[leftRoot], minValue[rightRoot]);
        }
    }

    private int findWithCompact(int cur) {
        int parent = rootOf[cur];
        if (parent == cur) {
            return cur;
        }

        rootOf[cur] = findWithCompact(parent);
        return rootOf[cur];
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
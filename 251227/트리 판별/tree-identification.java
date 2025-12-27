import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int M;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        new Solver(edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    boolean isTree = true;
    List<List<Integer>> graph = new ArrayList<>();
    HashSet<Integer> visitedHashSet = new HashSet<>();
    HashMap<Integer, Integer> inputCountHashMap = new HashMap<>();
    int nodeCount;
    List<Edge> edges;

    public Solver(List<Edge> edges) {
        this.edges = edges;
    }

    public void solve() {
        for (Edge edge : edges) {
            nodeCount = Math.max(nodeCount, edge.start);
            nodeCount = Math.max(nodeCount, edge.end);
        }
        init();

        int rootNode = checkRootOnlyOneReturningRoot();
        if (!isTree) {
            printNotTree();
            return;
        }

        checkInputOnlyOne(rootNode);
        if (!isTree) {
            printNotTree();
            return;
        }

        dfs(rootNode);
        checkPathFromRootExist();
        if (!isTree) {
            printNotTree();
            return;
        }

        System.out.println(1);
    }

    private void checkPathFromRootExist() {
        for (Entry<Integer, Integer> entry : inputCountHashMap.entrySet()) {
            if (visitedHashSet.contains(entry.getKey())) {
                continue;
            }
            isTree = false;
            return;
        }
    }

    private int checkRootOnlyOneReturningRoot() {
        int rootNode = -1;
        int rootCount = 0;
        for (Entry<Integer, Integer> entry : inputCountHashMap.entrySet()) {
            if (entry.getValue() == 0) {
                rootNode = entry.getKey();
                rootCount++;
            }
        }
        if (rootCount != 1) {
            isTree = false;
            return -1;
        }
        return rootNode;
    }

    private void checkInputOnlyOne(int rootNode) {
        for (Entry<Integer, Integer> entry : inputCountHashMap.entrySet()) {
            if (entry.getKey() == rootNode) {
                continue;
            }
            if (entry.getValue() != 1) {
                isTree = false;
                return;
            }
        }
    }

    private void dfs(int cur) {
        visitedHashSet.add(cur);

        List<Integer> nextEdges = graph.get(cur);
        for (int nextEdge : nextEdges) {
            dfs(nextEdge);
        }
    }

    private void init() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
            inputCountHashMap.put(edge.start, 0);
            inputCountHashMap.put(edge.end, 0);
        }
        for (Edge edge : edges) {
            inputCountHashMap.compute(edge.end, (key, val) -> val + 1);
        }
    }

    private void printNotTree() {
        System.out.println(0);
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
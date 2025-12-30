import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toString(st), toString(st), toString(st)));
        }

        new Solver(N, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }

    private static String toString(StringTokenizer st) {
        return st.nextToken();
    }
}

class Solver {

    final String NO_NODE = ".";
    String[] graph;
    int nodeCount;
    HashMap<String, Integer> nodeIndexHashMap = new HashMap<>();
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
        this.graph = new String[5 * nodeCount];
    }

    public void solve() {
        nodeIndexHashMap.put("A", 1);
        graph[1] = "A";
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            makeChild(graphMakeInfo);
        }

        printPreorderDFS(1);
        System.out.println();
        printInorderDFS(1);
        System.out.println();
        printPostorderDFS(1);
    }

    private void printPreorderDFS(int parentIndex) {
        if (graph[parentIndex] == null) {
            return;
        }
        System.out.print(graph[parentIndex]);
        printPreorderDFS(parentIndex * 2);
        printPreorderDFS(parentIndex * 2 + 1);
    }

    private void printInorderDFS(int parentIndex) {
        if (graph[parentIndex] == null) {
            return;
        }
        printInorderDFS(parentIndex * 2);
        System.out.print(graph[parentIndex]);
        printInorderDFS(parentIndex * 2 + 1);
    }

    private void printPostorderDFS(int parentIndex) {
        if (graph[parentIndex] == null) {
            return;
        }
        printPostorderDFS(parentIndex * 2);
        printPostorderDFS(parentIndex * 2 + 1);
        System.out.print(graph[parentIndex]);
    }

    private void makeChild(GraphMakeInfo graphMakeInfo) {
        int parentIndex = nodeIndexHashMap.get(graphMakeInfo.parent);
        if (!graphMakeInfo.left.equals(NO_NODE)) {
            String left = graphMakeInfo.left;
            int leftIndex = parentIndex * 2;
            graph[leftIndex] = left;
            nodeIndexHashMap.put(left, leftIndex);
        }
        if (!graphMakeInfo.right.equals(NO_NODE)) {
            String right = graphMakeInfo.right;
            int rightIndex = parentIndex * 2 + 1;
            graph[rightIndex] = right;
            nodeIndexHashMap.put(right, rightIndex);
        }
    }
}

class GraphMakeInfo {

    String parent;
    String left;
    String right;

    public GraphMakeInfo(String parent, String left, String right) {
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
}
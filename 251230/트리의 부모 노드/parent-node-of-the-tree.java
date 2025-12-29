import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toInt(st), toInt(st)));
        }

        new Solver(N, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    boolean[] visited;
    int[] parentNumberOf;
    List<GraphMakeInfo> graphMakeInfos;
    List<List<Integer>> tree = new ArrayList<>();

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
        this.visited = new boolean[nodeCount + 1];
        this.parentNumberOf = new int[nodeCount + 1];
    }

    public void solve() {
        final int ROOT_NODE = 1;

        for (int i = 0; i <= nodeCount; i++) {
            tree.add(new ArrayList<>());
        }
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            tree.get(graphMakeInfo.start).add(graphMakeInfo.end);
            tree.get(graphMakeInfo.end).add(graphMakeInfo.start);
        }

        setParentNumberDFS(ROOT_NODE);
        for (int v = 2; v <= nodeCount; v++) {
            System.out.println(parentNumberOf[v]);
        }
    }

    private void setParentNumberDFS(int parent) {
        visited[parent] = true;
        List<Integer> children = tree.get(parent);
        for (int child : children) {
            if (visited[child]) {
                continue;
            }
            parentNumberOf[child] = parent;
            setParentNumberDFS(child);
        }
    }
}

class GraphMakeInfo {

    int start;
    int end;

    public GraphMakeInfo(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
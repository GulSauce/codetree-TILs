import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    int[] dist;
    HashSet<Integer> leafNodeHashSet = new HashSet<>();
    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
    }

    public void solve() {
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            graph.computeIfAbsent(graphMakeInfo.start, (k) -> new ArrayList<>())
                .add(graphMakeInfo.end);
        }

        dist = new int[nodeCount + 1];
        calcDistFindLeafDFS(1);

        long leafNodeDistSum = 0;
        for (int leafNode : leafNodeHashSet) {
            leafNodeDistSum += dist[leafNode];
        }
        System.out.println(leafNodeDistSum % 2 == 0 ? 0 : 1);
    }

    private void calcDistFindLeafDFS(int cur) {
        List<Integer> nextEdges = graph.get(cur);
        if (nextEdges == null) {
            leafNodeHashSet.add(cur);
            return;
        }
        for (int nextEdge : nextEdges) {
            dist[nextEdge] = dist[cur] + 1;
            calcDistFindLeafDFS(nextEdge);
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        for (int i = 0; i < M; i++) {
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

    int[] inEdgeCount;
    boolean[] visited;
    int endNodeNumber;
    List<GraphMakeInfo> graphMakeInfos;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int endNodeNumber, List<GraphMakeInfo> graphMakeInfos) {
        this.endNodeNumber = endNodeNumber;
        this.graphMakeInfos = graphMakeInfos;
        this.inEdgeCount = new int[endNodeNumber + 1];
        this.visited = new boolean[endNodeNumber + 1];
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
        }

        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            graph.get(graphMakeInfo.start).add(graphMakeInfo.end);
            graph.get(graphMakeInfo.end).add(graphMakeInfo.start);
        }

        int answer = 0;
        for (int rootNodeNumber = 1; rootNodeNumber <= endNodeNumber; rootNodeNumber++) {
            if (visited[rootNodeNumber]) {
                continue;
            }
            List<Integer> curGraphNumbers = new ArrayList<>();
            setInEdgeCountDFS(rootNodeNumber, curGraphNumbers);
            int edgeCount = 0;
            for (int number : curGraphNumbers) {
                edgeCount += graph.get(number).size();
            }
            if (edgeCount / 2 == curGraphNumbers.size() - 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private void setInEdgeCountDFS(int cur, List<Integer> curGraphNumbers) {
        visited[cur] = true;
        curGraphNumbers.add(cur);
        List<Integer> toNextNodes = graph.get(cur);
        for (int toNextNode : toNextNodes) {
            if (visited[toNextNode]) {
                continue;
            }
            setInEdgeCountDFS(toNextNode, curGraphNumbers);
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
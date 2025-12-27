import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<TreeInfo> treeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            treeInfos.add(new TreeInfo(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, treeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;

    boolean[] visited;
    int[] dist;

    List<TreeInfo> treeInfos;
    List<List<Edge>> tree = new ArrayList<>();

    public Solver(int nodeCount, List<TreeInfo> treeInfos) {
        this.nodeCount = nodeCount;
        this.treeInfos = treeInfos;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            tree.add(new ArrayList<>());
        }
        for (TreeInfo treeInfo : treeInfos) {
            tree.get(treeInfo.start).add(new Edge(treeInfo.end, treeInfo.weight));
            tree.get(treeInfo.end).add(new Edge(treeInfo.start, treeInfo.weight));
        }
        visited = new boolean[nodeCount + 1];
        dist = new int[nodeCount + 1];

        int firstFarest = findFarest(1);
        int secondFarest = findFarest(firstFarest);

        System.out.println(dist[secondFarest]);
    }

    private int findFarest(int start) {
        Arrays.fill(visited, false);
        Arrays.fill(dist, 0);
        dfs(start);
        int farest = start;
        int curDist = dist[start];
        for (int v = 1; v <= nodeCount; v++) {
            if (dist[v] <= curDist) {
                continue;
            }
            farest = v;
            curDist = dist[v];
        }
        return farest;
    }

    private void dfs(int cur) {
        visited[cur] = true;
        List<Edge> myEdges = tree.get(cur);
        for (Edge next : myEdges) {
            if (visited[next.to]) {
                continue;
            }
            dist[next.to] = dist[cur] + next.weight;
            dfs(next.to);
        }
    }
}

class Edge {

    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class TreeInfo {

    int start;
    int end;
    int weight;

    public TreeInfo(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
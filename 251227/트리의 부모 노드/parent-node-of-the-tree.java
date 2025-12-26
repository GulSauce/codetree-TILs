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
        List<TreeInfo> treeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            treeInfos.add(new TreeInfo(toInt(st), toInt(st)));
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
    HashMap<Integer, Integer> parentHashMap = new HashMap<>();
    List<TreeInfo> treeInfos;
    List<List<Integer>> tree = new ArrayList<>();

    public Solver(int nodeCount, List<TreeInfo> treeInfos) {
        this.nodeCount = nodeCount;
        this.treeInfos = treeInfos;
    }

    public void solve() {
        final int ROOT_NODE = 1;

        for (int i = 0; i <= nodeCount; i++) {
            tree.add(new ArrayList<>());
        }
        for (TreeInfo treeInfo : treeInfos) {
            tree.get(treeInfo.start).add(treeInfo.end);
            tree.get(treeInfo.end).add(treeInfo.start);
        }

        visited = new boolean[nodeCount + 1];
        dfs(ROOT_NODE);

        for (int v = 2; v <= nodeCount; v++) {
            System.out.println(parentHashMap.get(v));
        }
    }

    private void dfs(int parent) {
        visited[parent] = true;
        List<Integer> children = tree.get(parent);
        if (children.isEmpty()) {
            return;
        }
        for (int child : children) {
            if (visited[child]) {
                continue;
            }
            parentHashMap.put(child, parent);
            dfs(child);
        }
    }
}

class TreeInfo {

    int start;
    int end;

    public TreeInfo(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
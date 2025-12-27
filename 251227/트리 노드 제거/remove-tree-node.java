import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        int[] parentNumbers;
        int nodeNumberToDelete;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);

        st = new StringTokenizer(br.readLine());
        parentNumbers = new int[N];
        for (int i = 0; i <= N - 1; i++) {
            parentNumbers[i] = toInt(st);
        }
        st = new StringTokenizer(br.readLine());
        nodeNumberToDelete = toInt(st);

        new Solver(N, nodeNumberToDelete, parentNumbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int leafNodeCount;
    int nodeCount;
    int nodeNumberToDelete;
    HashSet<Integer> delteNodeHashSet = new HashSet<>();
    int[] parentNumbers;

    List<List<Integer>> tree = new ArrayList<>();

    public Solver(int nodeCount, int nodeNumberToDelete, int[] parentNumbers) {
        this.nodeCount = nodeCount;
        this.nodeNumberToDelete = nodeNumberToDelete;
        this.parentNumbers = parentNumbers;
    }

    public void solve() {
        for (int i = 0; i < nodeCount; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < parentNumbers.length; i++) {
            int parent = parentNumbers[i];
            if (parent == -1) {
                continue;
            }
            tree.get(parent).add(i);
        }

        markDeleteNodeDFS(nodeNumberToDelete);

        final int ROOT_NODE = findRoot();
        findLeafDFS(ROOT_NODE);
        System.out.println(leafNodeCount);
    }

    private void markDeleteNodeDFS(int cur) {
        delteNodeHashSet.add(cur);
        List<Integer> children = tree.get(cur);

        for (int child : children) {
            markDeleteNodeDFS(child);
        }
    }

    private void findLeafDFS(int cur) {
        if (delteNodeHashSet.contains(cur)) {
            return;
        }
        List<Integer> children = tree.get(cur);

        int childCount = 0;
        for (int child : children) {
            if (delteNodeHashSet.contains(child)) {
                continue;
            }
            childCount++;
            findLeafDFS(child);
        }
        if (childCount == 0) {
            leafNodeCount++;
        }
    }

    private int findRoot() {
        for (int i = 0; i < parentNumbers.length; i++) {
            if (parentNumbers[i] == -1) {
                return i;
            }
        }
        return -1;
    }
}
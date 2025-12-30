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
        List<NodeInfo> nodeInfos = new ArrayList<>();
        long K;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int v = 1; v <= N; v++) {
            st = new StringTokenizer(br.readLine());
            nodeInfos.add(new NodeInfo(v, toInt(st), toInt(st)));
        }
        st = new StringTokenizer(br.readLine());
        K = Long.parseLong(st.nextToken());

        new Solver(nodeInfos, K).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<NodeInfo> nodeInfos;
    long targetNumber;
    HashMap<Integer, Node> numberToNodeHashMap = new HashMap<>();

    public Solver(List<NodeInfo> nodeInfos, long targetNumber) {
        this.nodeInfos = nodeInfos;
        this.targetNumber = targetNumber;
    }

    int distNodeNumber = -1;

    public void solve() {
        for (NodeInfo nodeInfo : nodeInfos) {
            makeChild(nodeInfo);
        }
        final int ROOT_NODE = 1;
        setDestNumberDFS(numberToNodeHashMap.get(ROOT_NODE), targetNumber);
        System.out.println(distNodeNumber);
    }

    private void setDestNumberDFS(Node cur, long targetNumber) {
        if (cur.left == null && cur.right == null) {
            distNodeNumber = cur.number;
            return;
        }
        if (cur.left != null && cur.right == null) {
            setDestNumberDFS(cur.left, targetNumber);
        }
        if (cur.left == null && cur.right != null) {
            setDestNumberDFS(cur.right, targetNumber);
        }

        boolean isOdd = targetNumber % 2 == 1;
        if (isOdd) {
            setDestNumberDFS(cur.left, (targetNumber + 1) / 2);
        } else {
            setDestNumberDFS(cur.right, targetNumber / 2);
        }
    }

    private void makeChild(NodeInfo nodeInfo) {
        final int NO_NODE = -1;
        Node cur = numberToNodeHashMap.computeIfAbsent(nodeInfo.parent,
            (key) -> new Node(nodeInfo.parent));
        if (nodeInfo.left != NO_NODE) {
            cur.left = numberToNodeHashMap.computeIfAbsent(nodeInfo.left,
                (key) -> new Node(nodeInfo.left));
        }
        if (nodeInfo.right != NO_NODE) {
            cur.right = numberToNodeHashMap.computeIfAbsent(nodeInfo.right,
                (key) -> new Node(nodeInfo.right));
        }
    }
}

class Node {

    int number;
    Node left;
    Node right;

    public Node(int number) {
        this.number = number;
    }
}

class NodeInfo {

    int parent;
    int left;
    int right;

    public NodeInfo(int parent, int left, int right) {
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
}
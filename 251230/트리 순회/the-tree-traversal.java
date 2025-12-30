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

    int nodeCount;

    HashMap<String, Node> valueToNodeHashMap = new HashMap<>();
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
    }

    public void solve() {
        final String NO_NODE = ".";
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            Node node = makeNode(graphMakeInfo.parent);
            if (!graphMakeInfo.left.equals(NO_NODE)) {
                node.setLeft(makeNode(graphMakeInfo.left));
            }
            if (!graphMakeInfo.right.equals(NO_NODE)) {
                node.setRight(makeNode(graphMakeInfo.right));
            }
        }

        Node rootNode = valueToNodeHashMap.get("A");
        printPreorderDFS(rootNode);
        System.out.println();

        printInorderDFS(rootNode);
        System.out.println();

        printPostorderDFS(rootNode);
    }

    private void printPreorderDFS(Node cur) {
        if (cur == null) {
            return;
        }
        System.out.print(cur.value);
        printPreorderDFS(cur.left);
        printPreorderDFS(cur.right);
    }

    private void printInorderDFS(Node cur) {
        if (cur == null) {
            return;
        }
        printInorderDFS(cur.left);
        System.out.print(cur.value);
        printInorderDFS(cur.right);
    }

    private void printPostorderDFS(Node cur) {
        if (cur == null) {
            return;
        }
        printPostorderDFS(cur.left);
        printPostorderDFS(cur.right);
        System.out.print(cur.value);
    }

    private Node makeNode(String value) {
        return valueToNodeHashMap.computeIfAbsent(value, (key) -> new Node(value));
    }
}

class Node {

    String value;
    Node left;
    Node right;

    public Node(String value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
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
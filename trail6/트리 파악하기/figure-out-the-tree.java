import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        int k;
        List<List<String>> treeTravelResults = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);

        for (int i = 0; i < N; i++) {
            treeTravelResults.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            k = toInt(st);
            for (int j = 0; j < k; j++) {
                treeTravelResults.get(i).add(st.nextToken());
            }
        }

        new Solver(treeTravelResults).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    Node root = new Node();
    List<List<String>> treeTravelResults;

    public Solver(List<List<String>> treeTravelResults) {
        this.treeTravelResults = treeTravelResults;
    }

    public void solve() {
        for (List<String> words : treeTravelResults) {
            root.insert(words);
        }
        root.printTree(root, 0);
    }
}

class Node {

    TreeMap<String, Node> children = new TreeMap<>();

    public Node() {
    }

    public void printTree(Node cur, int depth) {
        for (Entry<String, Node> child : cur.children.entrySet()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("--");
            }
            System.out.println(child.getKey());
            printTree(child.getValue(), depth + 1);
        }
    }

    public void insert(List<String> target) {
        Node cur = this;
        for (String string : target) {
            Node child = cur.children.get(string);
            if (child == null) {
                child = new Node();
                cur.children.put(string, child);
            }
            cur = child;
        }
    }
}
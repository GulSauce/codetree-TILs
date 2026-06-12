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
        int n;
        List<List<Character>> treeTravelResults = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);

        for (int i = 0; i < n; i++) {
            treeTravelResults.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int count = toInt(st);
            for (int j = 0; j < count; j++) {
                treeTravelResults.get(i).add(st.nextToken().charAt(0));
            }
        }

        new Solver(treeTravelResults).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<List<Character>> treeTravelResults;

    Node root = new Node();

    public Solver(List<List<Character>> treeTravelResults) {
        this.treeTravelResults = treeTravelResults;
    }

    public void solve() {
        for (List<Character> treeTravelResult : treeTravelResults) {
            StringBuilder sb = new StringBuilder();
            for (Character ch : treeTravelResult) {
                sb.append(ch);
            }
            String target = sb.toString();
            root.insert(target);
        }

        root.printTree(root, 0);
    }
}

class Node {

    TreeMap<Character, Node> children = new TreeMap<>();

    public Node() {
    }

    public void insert(String target) {
        Node cur = this;
        for (Character letter : target.toCharArray()) {
            Node child = cur.children.get(letter);
            if (child == null) {
                child = new Node();
                cur.children.put(letter, child);
            }
            cur = child;
        }
    }


    public void printTree(Node cur, int depth) {
        TreeMap<Character, Node> children = cur.children;
        for (Entry<Character, Node> child : children.entrySet()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("--");
            }
            System.out.println(child.getKey());
            printTree(child.getValue(), depth + 1);
        }
    }
}
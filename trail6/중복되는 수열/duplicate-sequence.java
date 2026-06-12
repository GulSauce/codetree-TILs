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
        List<String> sequences = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sequences.add(st.nextToken());
        }

        new Solver(sequences).solve();
    }
}

class Solver {

    List<String> sequences;
    Node root = new Node();

    public Solver(List<String> sequences) {
        this.sequences = sequences;
    }

    public void solve() {
        for (String sequence : sequences) {
            root.insert(sequence);
        }
        for (String sequence : sequences) {
            boolean find = root.startsWith(sequence);
            if (find) {
                System.out.println(0);
                System.exit(0);
            }
        }
        System.out.println(1);
    }
}

class Node {

    private boolean isEnd;
    private HashMap<Character, Node> children = new HashMap<>();

    public Node() {
        isEnd = false;
    }

    public void insert(String target) {
        Node cur = this;
        for (char letter : target.toCharArray()) {
            Node child = cur.children.get(letter);
            if (child == null) {
                child = new Node();
                cur.children.put(letter, child);
            }
            cur = child;
        }
        cur.isEnd = true;
    }

    public boolean search(String target) {
        Node find = walk(target);
        if (find == null) {
            return false;
        }
        return find.isEnd;
    }

    public boolean startsWith(String prefix) {
        Node find = walk(prefix);
        if (find == null) {
            return false;
        }
        return find.children.isEmpty() ? false : true;
    }

    private Node walk(String target) {
        Node cur = this;
        for (char letter : target.toCharArray()) {
            Node child = cur.children.get(letter);
            if (child == null) {
                return null;
            }
            cur = child;
        }
        return cur;
    }
}
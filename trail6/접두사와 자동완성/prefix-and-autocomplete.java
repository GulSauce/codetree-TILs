import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        List<String> words = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            words.add(st.nextToken());
        }

        new Solver(words).solve();
    }
}

class Solver {

    Node root = new Node();
    List<String> words;

    public Solver(List<String> words) {
        this.words = words;
    }

    public void solve() {
        for (String word : words) {
            root.insert(word);
        }
        for (String word : words) {
            int inputCount = root.findInputCount(word);
            System.out.print(inputCount + " ");
        }
    }
}

class Node {

    boolean isEnd = false;
    int wordCount = 0;
    HashMap<Character, Node> children = new HashMap<>();

    public Node() {
    }

    public void insert(String target) {
        Node cur = this;
        for (Character letter : target.toCharArray()) {
            wordCount++;
            Node child = cur.children.get(letter);
            if (child == null) {
                child = new Node();
                cur.children.put(letter, child);
            }
            cur = child;
        }
        cur.isEnd = true;
        cur.wordCount++;
    }

    public int findInputCount(String target) {
        Node cur = this.children.get(target.charAt(0));
        int inputCount = 1;
        for (int i = 1; i < target.length(); i++) {
            if (cur.children.size() > 1 || cur.isEnd) {
                inputCount++;
            }
            Character letter = target.charAt(i);
            Node child = cur.children.get(letter);
            cur = child;
        }
        return inputCount;
    }
}
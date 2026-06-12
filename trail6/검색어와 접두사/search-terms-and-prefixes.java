import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m;
        List<String> dictWords = new ArrayList<>();
        String keyword;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        m = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            dictWords.add(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        keyword = st.nextToken();

        new Solver(dictWords, keyword).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<String> dictWords;
    String keyword;
    Node root;

    public Solver(List<String> dictWords, String keyword) {
        this.dictWords = dictWords;
        this.keyword = keyword;
    }

    public void solve() {
        root = new Node();
        for (String dictWord : dictWords) {
            root.insert(dictWord);
        }
        root.walk(keyword);
    }
}

class Node {

    int wordCount = 0;
    HashMap<Character, Node> children = new HashMap<>();

    public Node() {
    }

    public void insert(String target) {
        Node cur = this;
        for (Character letter : target.toCharArray()) {
            Node child = cur.children.get(letter);
            cur.wordCount++;
            if (child == null) {
                child = new Node();
                cur.children.put(letter, child);
            }
            cur = child;
        }
        cur.wordCount++;
    }

    public void walk(String target) {
        Node cur = this;
        int count = 0;
        for (Character letter : target.toCharArray()) {
            Node child = cur.children.get(letter);
            if (child == null) {
                break;
            }
            System.out.print(child.wordCount + " ");
            count++;
            cur = child;
        }
        for (int i = 0; i < target.length() - count; i++) {
            System.out.print(0 + " ");
        }
    }
}
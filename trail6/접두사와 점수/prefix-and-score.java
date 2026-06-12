import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<String> words = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
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

        long answer = 0;
        Queue<BFSInfo> bfsInfoQueue = new LinkedList<>();
        bfsInfoQueue.add(new BFSInfo(root, 0));
        while (!bfsInfoQueue.isEmpty()) {
            BFSInfo curBfsInfo = bfsInfoQueue.poll();
            Node cur = curBfsInfo.cur;
            answer = Math.max(answer, curBfsInfo.depth * (long) cur.wordCount);
            for (Entry<Character, Node> child : curBfsInfo.cur.children.entrySet()) {
                bfsInfoQueue.add(new BFSInfo(child.getValue(), curBfsInfo.depth + 1));
            }
        }
        System.out.println(answer);
    }
}

class BFSInfo {

    Node cur;
    int depth;

    public BFSInfo(Node cur, int depth) {
        this.cur = cur;
        this.depth = depth;
    }
}

class Node {

    int wordCount;
    final HashMap<Character, Node> children = new HashMap<>();

    public Node() {
    }

    public int countWordHasPrefixOf(String prefix) {
        Node cur = this;
        for (char letter : prefix.toCharArray()) {
            Node child = cur.children.get(letter);
            if (child == null) {
                return 0;
            }
            cur = child;
        }
        return cur.wordCount;
    }

    public void insert(String target) {
        Node cur = this;
        for (char letter : target.toCharArray()) {
            cur.wordCount++;
            Node child = cur.children.get(letter);
            if (child == null) {
                child = new Node();
                cur.children.put(letter, child);
            }
            cur = child;
        }
        cur.wordCount++;
    }
}
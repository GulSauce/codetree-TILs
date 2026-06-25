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
        List<String> wordList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            wordList.add(st.nextToken());
        }

        new Solver(wordList).solve();
    }
}

class Solver {

    Node root = new Node();
    List<String> wordList;

    public Solver(List<String> wordList) {
        this.wordList = wordList;
    }

    public void solve() {
        for (int i = 0; i < wordList.size(); i++) {
            root.insert(i, wordList.get(i));
        }
        int answer = 0;
        for (int i = 0; i < wordList.size(); i++) {
            answer = Math.max(answer, root.walk(i, wordList.get(i)));
        }
        System.out.println(answer);
    }
}

class Node {

    int maxPalCount = 0;
    int index = -1;
    boolean end = false;
    HashMap<Character, Node> childNodes = new HashMap<>();

    public Node() {
    }

    public void insert(int index, String target) {
        Node cur = this;
        int pointer = -1;
        target = new StringBuilder(target).reverse().toString();
        for (char letter : target.toCharArray()) {
            Node child = cur.childNodes.get(letter);
            if (child == null) {
                child = new Node();
                cur.childNodes.put(letter, child);
            }
            pointer++;
            StringBuilder sb = new StringBuilder();
            for (int i = pointer + 1; i < target.length(); i++) {
                sb.append(target.charAt(i));
            }
            if (isPalin(sb.toString())) {
                child.maxPalCount = Math.max(child.maxPalCount, sb.length());
            }
            cur = child;
        }
        cur.index = index;
        cur.end = true;
    }

    public int walk(int index, String target) {
        Node cur = this;
        int pointer = -1;
        int maxLength = 0;
        for (char letter : target.toCharArray()) {
            Node child = cur.childNodes.get(letter);
            if (child == null) {
                break;
            }
            pointer++;
            if (child.index != index && child.end) {
                StringBuilder sb = new StringBuilder();
                for (int i = pointer + 1; i < target.length(); i++) {
                    sb.append(target.charAt(i));
                }
                if (isPalin(sb.toString())) {
                    maxLength = Math.max(maxLength, target.length() + pointer + 1);
                }
            }
            cur = child;
        }
        if (pointer == target.length() - 1) {
            if (cur.end && cur.index != index) {
                maxLength = Math.max(maxLength, 2 * target.length());
            }
            if (cur.maxPalCount > 0) {
                maxLength = Math.max(maxLength, 2 * target.length() + cur.maxPalCount);
            }
        }
        return maxLength;
    }

    private boolean isPalin(String target) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char letter : target.toCharArray()) {
            sb.append(letter);
            sb.append('#');
        }
        String pattern = sb.toString();
        int mid = pattern.length() / 2;
        int left = mid - 1;
        int right = mid + 1;
        while (true) {
            if (left < 0) {
                return true;
            }
            if (pattern.charAt(left) != pattern.charAt(right)) {
                return false;
            }
            left--;
            right++;
        }
    }
}
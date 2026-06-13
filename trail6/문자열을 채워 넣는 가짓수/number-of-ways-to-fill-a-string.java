import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String T;
        int M;
        List<String> patterns = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = st.nextToken();
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            patterns.add(st.nextToken());
        }

        new Solver(T, patterns).solve();
    }
}

class Solver {

    int[] dp;
    String article;
    Node root = new Node();
    List<String> patterns;

    public Solver(String article, List<String> patterns) {
        this.dp = new int[article.length() + 1];
        this.article = article;
        this.patterns = patterns;
    }

    public void solve() {
        for (String pattern : patterns) {
            root.insert(pattern);
        }

        this.article = appendPadding(article);
        dp[0] = 1;
        for (int i = 1; i < article.length(); i++) {
            String subStr = article.substring(i);
            root.walk(dp, subStr, i);
        }

        System.out.println(dp[article.length() - 1]);
    }

    private String appendPadding(String origin) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(origin);
        return sb.toString();
    }
}

class Node {

    final int R = 1_000_000_007;
    boolean isEnd = false;
    HashMap<Character, Node> children = new HashMap<>();

    public Node() {
    }

    public void insert(String pattern) {
        Node cur = this;
        for (Character letter : pattern.toCharArray()) {
            Node child = cur.children.get(letter);
            if (child == null) {
                child = new Node();
                cur.children.put(letter, child);
            }
            cur = child;
        }
        cur.isEnd = true;
    }

    public void walk(int[] dp, String target, int startIndex) {
        Node cur = this;
        int curIndex = startIndex;
        for (Character letter : target.toCharArray()) {
            Node child = cur.children.get(letter);
            if (child == null) {
                return;
            }
            if (child.isEnd) {
                dp[curIndex] += dp[startIndex - 1];
                dp[curIndex] %= R;
            }
            curIndex++;
            cur = child;
        }
    }
}
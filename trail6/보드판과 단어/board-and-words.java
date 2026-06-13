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
        List<String> gridStrings = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            words.add(st.nextToken());
        }

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            gridStrings.add(st.nextToken());
        }

        new Solver(words, gridStrings).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    Node root = new Node();

    int answer = 0;
    final int LENGTH = 4;

    int[] dY = {1, 0, -1, 0, 1, 1, -1, -1};
    int[] dX = {0, -1, 0, 1, 1, -1, -1, 1};

    List<String> words;
    List<String> gridStrings;
    char[][] grid;
    boolean[][] visited;
    List<Character> wordStack = new ArrayList<>();

    public Solver(List<String> words, List<String> gridStrings) {
        this.words = words;
        this.gridStrings = gridStrings;
        this.grid = new char[LENGTH + 1][LENGTH + 1];
        this.visited = new boolean[LENGTH + 1][LENGTH + 1];
    }

    public void solve() {
        for (String word : words) {
            root.insert(word);
        }
        for (int y = 0; y < LENGTH; y++) {
            String gridString = gridStrings.get(y);
            for (int x = 0; x < gridString.length(); x++) {
                grid[y + 1][x + 1] = gridString.charAt(x);
            }
        }
        for (int y = 1; y <= LENGTH; y++) {
            for (int x = 1; x <= LENGTH; x++) {
                visited[y][x] = true;
                wordStack.add(grid[y][x]);
                dfs(x, y);
                wordStack.remove(wordStack.size() - 1);
                visited[y][x] = false;
            }
        }
        System.out.println(answer);
    }

    private void dfs(int x, int y) {
        if (root.search(wordStack)) {
            answer = Math.max(answer, wordStack.size());
        }
        if (wordStack.size() == 8) {
            return;
        }
        for (int i = 0; i < 8; i++) {
            int nY = y + dY[i];
            int nX = x + dX[i];
            if (nY <= 0 || nY > LENGTH || nX <= 0 || nX > LENGTH) {
                continue;
            }
            if (visited[nY][nX]) {
                continue;
            }
            visited[nY][nX] = true;
            wordStack.add(grid[nY][nX]);
            dfs(nX, nY);
            wordStack.remove(wordStack.size() - 1);
            visited[nY][nX] = false;
        }
    }
}

class Node {

    boolean isEnd;
    HashMap<Character, Node> children = new HashMap<>();

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
        cur.isEnd = true;
    }

    public boolean search(List<Character> target) {
        Node cur = this;
        for (Character letter : target) {
            Node child = cur.children.get(letter);
            if (child == null) {
                return false;
            }
            cur = child;
        }
        return cur.isEnd;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        List<String> parens = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            parens.add(st.nextToken());
        }

        new Solver(n, parens).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int numberCount;
    int[] originalIndegree;

    List<String> parens;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int numberCount, List<String> parens) {
        this.numberCount = numberCount;
        this.originalIndegree = new int[numberCount + 1];
        this.parens = parens;
    }

    public void solve() {
        for (int i = 0; i <= numberCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int v = 1; v <= parens.size(); v++) {
            String paren = parens.get(v - 1);
            if (paren.equals("<")) {
                graph.get(v).add(v + 1);
                originalIndegree[v + 1]++;
            }
            if (paren.equals(">")) {
                graph.get(v + 1).add(v);
                originalIndegree[v]++;
            }
        }

        printSorted(getAnswer(new PriorityQueue<Integer>((a, b) -> Integer.compare(a, b))));
        System.out.println();
        printSorted(getAnswer(new PriorityQueue<Integer>((a, b) -> Integer.compare(b, a))));
    }

    private HashMap<Integer, Integer> getAnswer(PriorityQueue<Integer> q) {
        int[] indegree = Arrays.copyOf(originalIndegree, originalIndegree.length);

        for (int v = 1; v <= numberCount; v++) {
            if (indegree[v] == 0) {
                q.add(v);
            }
        }

        HashMap<Integer, Integer> lexSmallest = new HashMap<>();
        while (!q.isEmpty()) {
            int cur = q.poll();
            lexSmallest.put(cur, lexSmallest.size() + 1);
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }

        return lexSmallest;
    }

    private void printSorted(HashMap<Integer, Integer> answer) {
        List<Entry<Integer, Integer>> sorted = new ArrayList<>(answer.entrySet());
        sorted.sort((a, b) -> Integer.compare(a.getKey(), b.getKey()));
        for (Entry<Integer, Integer> value : sorted) {
            System.out.print(formatAnswer(value.getValue()));
        }
    }

    private String formatAnswer(int value) {
        String origin = String.valueOf(value);
        int leftPadding = 3 - origin.length();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < leftPadding; i++) {
            sb.append("0");
        }
        sb.append(origin);
        return sb.toString();
    }
}
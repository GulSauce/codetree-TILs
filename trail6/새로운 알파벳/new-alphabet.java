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
        int n;
        List<String> words = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            words.add(st.nextToken());
        }

        new Solver(words).solve();
    }
}

class Solver {

    List<String> words;
    HashMap<String, List<String>> graph = new HashMap<>();
    HashMap<String, Integer> indegree = new HashMap<>();

    public Solver(List<String> words) {
        this.words = words;
    }

    public void solve() {
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                String char1 = String.valueOf(word.charAt(i));
                graph.put(char1, new ArrayList<>());
                indegree.put(char1, 0);
            }
        }

        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                String word1 = words.get(i);
                String word2 = words.get(j);

                int endIndex = Math.min(word1.length(), word2.length()) - 1;
                for (int k = 0; k <= endIndex; k++) {
                    String char1 = String.valueOf(word1.charAt(k));
                    String char2 = String.valueOf(word2.charAt(k));
                    if (char1.equals(char2)) {
                        continue;
                    }
                    graph.get(char1).add(char2);
                    indegree.compute(char2, (str, value) -> value + 1);
                    break;
                }
            }
        }

        Queue<String> q = new LinkedList<>();
        for (Entry<String, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        }

        List<String> answer = new ArrayList<>();
        boolean multiplePath = false;
        while (!q.isEmpty()) {
            if (q.size() > 1) {
                multiplePath = true;
            }
            String cur = q.poll();
            answer.add(cur);

            List<String> nears = graph.get(cur);
            for (String near : nears) {
                indegree.compute(near, (k, v) -> v - 1);
                if (indegree.get(near) == 0) {
                    q.add(near);
                }
            }
        }

        if (graph.size() != answer.size()) {
            System.out.println(-1);
            System.exit(0);
        }
        if (multiplePath) {
            System.out.println("inf");
            System.exit(0);
        }
        for (String value : answer) {
            System.out.print(value);
        }
    }
}
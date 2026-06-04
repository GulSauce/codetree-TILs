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
        int n, m;
        List<CombineInfo> combineInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        m = toInt(st);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            combineInfos.add(new CombineInfo(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(n, combineInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int pieceCount;
    int[] indegree;

    List<CombineInfo> combineInfos;
    List<List<Node>> graph = new ArrayList<>();
    List<HashMap<Integer, Integer>> dp = new ArrayList<>();

    public Solver(int pieceCount, List<CombineInfo> combineInfos) {
        this.pieceCount = pieceCount;
        this.indegree = new int[pieceCount + 1];
        this.combineInfos = combineInfos;
    }

    public void solve() {
        for (int i = 0; i <= pieceCount; i++) {
            graph.add(new ArrayList<>());
            dp.add(new HashMap<>());
        }
        for (CombineInfo combineInfo : combineInfos) {
            indegree[combineInfo.middleNumber]++;
            graph.get(combineInfo.ingredient).add(
                new Node(combineInfo.middleNumber, combineInfo.count)
            );
        }

        Queue<Integer> q = new LinkedList<>();
        for (int v = 1; v <= pieceCount; v++) {
            if (indegree[v] == 0) {
                q.add(v);
                dp.get(v).put(v, 1);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Node> nears = graph.get(cur);
            for (Node near : nears) {

                HashMap<Integer, Integer> curDP = dp.get(cur);
                HashMap<Integer, Integer> nearDP = dp.get(near.number);

                for (Entry<Integer, Integer> entry : curDP.entrySet()) {
                    nearDP.compute(entry.getKey(), (k, v) -> {
                        int additional = entry.getValue() * near.w;
                        return (v == null ? 0 : v) + additional;
                    });
                }

                indegree[near.number]--;
                if (indegree[near.number] == 0) {
                    q.add(near.number);
                }
            }
        }

        List<Entry<Integer, Integer>> answer = new ArrayList<>(dp.get(pieceCount).entrySet());
        answer.sort((a, b) -> {
            return Integer.compare(a.getKey(), b.getKey());
        });

        for (Entry<Integer, Integer> entry : answer) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

class Node {

    int number;
    int w;

    public Node(int number, int w) {
        this.number = number;
        this.w = w;
    }
}

class CombineInfo {

    int middleNumber;
    int ingredient;
    int count;

    public CombineInfo(int middleNumber, int ingredient, int count) {
        this.middleNumber = middleNumber;
        this.ingredient = ingredient;
        this.count = count;
    }
}
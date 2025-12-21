import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, A, B;
        List<String> parens = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        A = toInt(st);
        B = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            parens.add(st.nextToken());
        }

        new Solver(N, A, B, parens).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int LENGTH;
    final int SAME_COST;
    final int DIFF_COST;
    final List<String> PARENS;
    final int NODE_COUNT;
    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public Solver(int LENGTH, int SAME_COST, int DIFF_COST, List<String> PARENS) {
        this.LENGTH = LENGTH;
        this.NODE_COUNT = LENGTH * LENGTH;
        this.SAME_COST = SAME_COST;
        this.DIFF_COST = DIFF_COST;
        this.PARENS = PARENS;
    }

    public void solve() {

        for (int i = 0; i <= NODE_COUNT; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < PARENS.size(); i++) {
            String cur = PARENS.get(i);
            for (int j = 0; j < cur.length(); j++) {
                int nodeNumber = getNodeNumber(i, j);
                if (i + 1 < PARENS.size()) {
                    String next = PARENS.get(i + 1);
                    int value = cur.charAt(j) == next.charAt(j) ? SAME_COST : DIFF_COST;
                    int downNodeNumber = getNodeNumber(i + 1, j);
                    graph.get(nodeNumber).add(new Node(downNodeNumber, value));
                    graph.get(downNodeNumber).add(new Node(nodeNumber, value));
                }
                if (j + 1 < cur.length()) {
                    int value = cur.charAt(j) == cur.charAt(j + 1) ? SAME_COST : DIFF_COST;
                    int rightNodeNumber = getNodeNumber(i, j + 1);
                    graph.get(nodeNumber).add(new Node(rightNodeNumber, value));
                    graph.get(rightNodeNumber).add(new Node(nodeNumber, value));
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= NODE_COUNT; i++) {
            int[] dist = dijkstra(i);
            for (int value : dist) {
                if (value == NOT_ALLOCATED) {
                    continue;
                }
                answer = Math.max(answer, value);
            }
        }
        System.out.println(answer);
    }

    private int[] dijkstra(int start) {
        int[] dist = new int[NODE_COUNT + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.weight != dist[cur.number]) {
                continue;
            }

            ArrayList<Node> near = graph.get(cur.number);
            for (Node next : near) {
                int nextWeight = next.weight + dist[cur.number];
                if (dist[next.number] <= nextWeight) {
                    continue;
                }
                dist[next.number] = nextWeight;
                pq.add(new Node(next.number, nextWeight));
            }
        }
        return dist;
    }

    private int getNodeNumber(int i, int j) {
        return i * LENGTH + (j + 1);
    }
}

class Node {

    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}

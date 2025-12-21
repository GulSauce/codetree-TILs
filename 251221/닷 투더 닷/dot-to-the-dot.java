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
        int N, M, X;
        int I, J, L, C;
        List<Input> inputs = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        X = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            I = toInt(st);
            J = toInt(st);
            L = toInt(st);
            C = toInt(st);
            inputs.add(new Input(I, J, L, C));
        }

        new Solver(N, X, inputs).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    final int START;
    final int END;
    List<List<Node>> graph = new ArrayList<>();
    int nodeCount;
    int X;
    List<Input> inputs;

    public Solver(int nodeCount, int x, List<Input> inputs) {
        this.nodeCount = nodeCount;
        X = x;
        this.inputs = inputs;
        this.START = 1;
        this.END = nodeCount;
    }

    public void solve() {

        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (Input input : inputs) {
            graph.get(input.node1).add(new Node(input.node2, input.L, input.C));
            graph.get(input.node2).add(new Node(input.node1, input.L, input.C));
        }

        int answer = Integer.MAX_VALUE;
        for (Input input : inputs) {
            int minC = input.C;
            int[] dist = dijkstra(minC);
            if (dist[END] == NOT_ALLOCATED) {
                continue;
            }
            int result = dist[END] + X / minC;
            answer = Math.min(answer, result);
        }
        System.out.println(answer);
    }

    public int[] dijkstra(int minC) {
        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[START] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.add(new Node(START, 0, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.number] != cur.weight) {
                continue;
            }
            List<Node> nearNodes = graph.get(cur.number);
            for (Node next : nearNodes) {
                if (next.C < minC) {
                    continue;
                }
                int nextWeight = dist[cur.number] + next.weight;
                if (dist[next.number] < nextWeight) {
                    continue;
                }
                dist[next.number] = nextWeight;
                pq.add(new Node(next.number, nextWeight, 0));
            }
        }
        return dist;
    }
}

class Node {

    int number;
    int weight;
    int C;

    public Node(int number, int weight, int c) {
        this.number = number;
        this.weight = weight;
        C = c;
    }
}

class Input {

    int node1;
    int node2;
    int L;
    int C;

    public Input(int node1, int node2, int l, int c) {
        this.node1 = node1;
        this.node2 = node2;
        L = l;
        C = c;
    }
}
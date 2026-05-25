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
        int N;
        List<Integer> putCosts = new ArrayList<>();
        putCosts.add(-1);
        int[][] costMatrix;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        costMatrix = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            putCosts.add(toInt(st));
        }

        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; x++) {
                costMatrix[y][x] = toInt(st);
            }
        }

        new Solver(N, putCosts, costMatrix).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    boolean[] inMST;

    int matrixLength;
    int[] dist;
    int[][] costMatrix;

    final int NOT_ALLOCATED = Integer.MAX_VALUE;

    List<Integer> putCosts;
    List<List<Node>> graph = new ArrayList<>();

    public Solver(int matrixLength, List<Integer> putCosts, int[][] costMatrix) {
        this.matrixLength = matrixLength;
        this.putCosts = putCosts;
        this.costMatrix = costMatrix;
        this.inMST = new boolean[matrixLength + 1];
        this.dist = new int[matrixLength + 1];
    }

    public void solve() {
        for (int i = 0; i <= matrixLength; i++) {
            graph.add(new ArrayList<>());
        }
        for (int y = 1; y <= matrixLength; y++) {
            for (int x = 1; x <= matrixLength; x++) {
                if (y == x) {
                    continue;
                }
                graph.get(y).add(new Node(x, costMatrix[y][x]));
                graph.get(x).add(new Node(y, costMatrix[y][x]));
            }
        }
        for (int v = 1; v <= matrixLength; v++) {
            graph.get(0).add(new Node(v, putCosts.get(v)));
            graph.get(v).add(new Node(0, putCosts.get(v)));
        }

        PriorityQueue<Node> q = new PriorityQueue<>((a, b) ->
            Integer.compare(a.weight, b.weight)
        );

        q.add(new Node(1, 0));
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[1] = 0;
        while (!q.isEmpty()) {
            Node selected = q.poll();
            if (inMST[selected.number]) {
                continue;
            }
            inMST[selected.number] = true;
            List<Node> nears = graph.get(selected.number);
            for (Node near : nears) {
                if (inMST[near.number]) {
                    continue;
                }
                int nearWeight = near.weight;
                if (dist[near.number] < nearWeight) {
                    continue;
                }
                dist[near.number] = nearWeight;
                q.add(new Node(near.number, nearWeight));
            }
        }

        int answer = 0;
        for (int v = 0; v <= matrixLength; v++) {
            answer += dist[v];
        }
        System.out.println(answer);
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
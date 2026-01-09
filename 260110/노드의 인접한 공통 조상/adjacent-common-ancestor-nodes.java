import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Edge> edges = new ArrayList<>();
        int targetA, targetB;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st)));
        }

        st = new StringTokenizer(br.readLine());
        targetA = toInt(st);
        targetB = toInt(st);

        new Solver(N, edges, targetA, targetB).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    List<Edge> edges;

    List<List<Integer>> graph = new ArrayList<>();
    int targetA;
    int targetB;

    boolean[] isChild;

    public Solver(int nodeCount, List<Edge> edges, int targetA, int targetB) {
        this.nodeCount = nodeCount;
        this.edges = edges;
        this.targetA = targetA;
        this.targetB = targetB;
        this.dist = new int[nodeCount + 1];
        this.parentOf = new int[nodeCount + 1];
        this.isChild = new boolean[nodeCount + 1];
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
            isChild[edge.end] = true;
        }

        int rootNodeNumber = -1;
        for (int i = 1; i <= nodeCount; i++) {
            if (isChild[i]) {
                continue;
            }
            rootNodeNumber = i;
            break;
        }
        makeDistParentDFS(rootNodeNumber);

        int aDist = dist[targetA];
        int bDist = dist[targetB];

        if (aDist < bDist) {
            int temp = targetA;
            targetA = targetB;
            targetB = temp;

            int temp2 = aDist;
            aDist = bDist;
            bDist = temp2;
        }

        int diff = aDist - bDist;
        for (int i = 0; i < diff; i++) {
            targetA = parentOf[targetA];
        }

        int ancestor = targetA;
        while (targetA != targetB) {
            targetA = parentOf[targetA];
            targetB = parentOf[targetB];
            ancestor = targetA;
        }
        System.out.println(ancestor);
    }

    int[] dist;
    int[] parentOf;

    private void makeDistParentDFS(int cur) {
        for (int child : graph.get(cur)) {

            dist[child] = dist[cur] + 1;
            parentOf[child] = cur;
            makeDistParentDFS(child);
        }
    }
}

class Edge {

    int start;
    int end;

    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
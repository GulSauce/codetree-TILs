import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        m = toInt(st);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(n, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    final int NOT_ALLOCATED = Integer.MIN_VALUE;
    List<Element> dp = new ArrayList<>();
    int[] indegree;
    int[] prevNumberOf;
    List<Edge> edges;
    List<List<Node>> graph = new ArrayList<>();

    public Solver(int endNodeNumber, List<Edge> edges) {
        this.endNodeNumber = endNodeNumber;
        this.indegree = new int[endNodeNumber + 1];
        this.prevNumberOf = new int[endNodeNumber + 1];
        this.edges = edges;
    }

    public void solve() {
        for (int i = 0; i <= endNodeNumber; i++) {
            graph.add(new ArrayList<>());
            dp.add(new Element(NOT_ALLOCATED, 0));
        }
        for (Edge edge : edges) {
            indegree[edge.y]++;
            graph.get(edge.x).add(new Node(edge.y, edge.weight));
        }

        Queue<Integer> q = new LinkedList<>();
        for (int v = 1; v <= endNodeNumber; v++) {
            if (indegree[v] == 0) {
                q.add(v);
            }
        }

        dp.get(1).weight = 0;
        dp.get(1).count = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Node> nears = graph.get(cur);
            for (Node near : nears) {
                int maxWeight = dp.get(cur).weight;
                int nearNumber = near.number;
                int nearWeight = near.weight;
                if (dp.get(nearNumber).weight < maxWeight + nearWeight) {
                    dp.get(nearNumber).weight = maxWeight + nearWeight;
                    dp.get(nearNumber).count = dp.get(cur).count + 1;
                } else if (dp.get(nearNumber).weight == maxWeight + nearWeight) {
                    dp.get(nearNumber).count += dp.get(cur).count + 1;
                }
                indegree[nearNumber]--;
                if (indegree[nearNumber] == 0) {
                    q.add(nearNumber);
                }
            }
        }

        System.out.println(dp.get(endNodeNumber).weight + " " + dp.get(endNodeNumber).count);
    }
}

class Element {

    int weight;
    int count;

    public Element(int weight, int count) {
        this.weight = weight;
        this.count = count;
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

class Edge {

    int x;
    int y;
    int weight;

    public Edge(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }
}

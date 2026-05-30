import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<String> nodeNames = new ArrayList<>();
        int M;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nodeNames.add(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(st.nextToken(), st.nextToken()));
        }

        new Solver(N, nodeNames, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    List<Edge> edges;
    List<String> nodeNames;
    HashMap<String, Integer> indegree = new HashMap<>();
    HashMap<String, List<String>> mixedGraph = new HashMap<>();
    HashMap<String, List<String>> graph = new HashMap<>();


    public Solver(int nodeCount, List<String> nodeNames, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.nodeNames = nodeNames;
        this.edges = edges;
    }

    public void solve() {
        for (String nodeName : nodeNames) {
            mixedGraph.put(nodeName, new ArrayList<>());
            graph.put(nodeName, new ArrayList<>());
            indegree.put(nodeName, 0);
        }

        for (Edge edge : edges) {
            mixedGraph.get(edge.b).add(edge.a);
            indegree.compute(edge.a, (k, v) -> v + 1);
        }

        int treeCount = 0;
        List<String> rootNodes = new ArrayList<>();
        PriorityQueue<String> q = new PriorityQueue<>();
        for (Entry<String, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                treeCount++;
                rootNodes.add(entry.getKey());
                q.add(entry.getKey());
            }
        }

        while (!q.isEmpty()) {
            String cur = q.poll();
            List<String> nears = mixedGraph.get(cur);
            for (String near : nears) {
                indegree.compute(near, (k, v) -> v - 1);
                if (indegree.get(near) == 0) {
                    graph.get(cur).add(near);
                    q.add(near);
                }
            }
        }

        List<Entry<String, List<String>>> graphList = new ArrayList<>(graph.entrySet());
        graphList.sort((a, b) -> a.getKey().compareTo(b.getKey()));

        System.out.println(treeCount);
        Collections.sort(rootNodes);
        for (String rootNode : rootNodes) {
            System.out.print(rootNode + " ");
        }
        System.out.println();
        for (Entry<String, List<String>> entry : graphList) {
            System.out.print(entry.getKey() + " " + entry.getValue().size() + " ");
            List<String> values = entry.getValue();
            Collections.sort(values);
            for (String value : values) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}

class Edge {

    String a;
    String b;


    public Edge(String a, String b) {
        this.a = a;
        this.b = b;
    }
}
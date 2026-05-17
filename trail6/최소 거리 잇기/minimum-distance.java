import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Point> points = new ArrayList<>();
        points.add(new Point(-1, -1));
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(new Point(toInt(st), toInt(st)));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), 0));
        }

        new Solver(points, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int[] rootOf;
    List<Point> points;
    List<Edge> edges;

    public Solver(List<Point> points, List<Edge> edges) {
        this.points = points;
        this.edges = edges;
        this.rootOf = new int[201];
    }

    public void solve() {
        for (int i = 1; i < points.size(); i++) {
            rootOf[i] = i;
        }
        for (int i = 1; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                double weight = Math.sqrt(
                    (double) (p1.x - p2.x) * (p1.x - p2.x) + (double) (p1.y - p2.y) * (p1.y - p2.y)
                );
                edges.add(new Edge(i, j, weight));
            }
        }
        edges.sort((a, b) -> Double.compare(a.w, b.w));

        double answer = 0;
        for (Edge edge : edges) {
            if (findWithCompact(edge.v1) == findWithCompact(edge.v2)) {
                continue;
            }
            union(edge.v1, edge.v2);
            answer += edge.w;
        }

        System.out.printf("%.2f", answer);
    }

    private void union(int v1, int v2) {
        int left = findWithCompact(v1);
        int right = findWithCompact(v2);

        rootOf[left] = right;
    }

    private int findWithCompact(int cur) {
        int parent = rootOf[cur];
        if (parent == cur) {
            return cur;
        }
        rootOf[cur] = findWithCompact(parent);
        return rootOf[cur];
    }
}

class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Edge {

    int v1;
    int v2;
    double w;

    public Edge(int v1, int v2, double w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
}

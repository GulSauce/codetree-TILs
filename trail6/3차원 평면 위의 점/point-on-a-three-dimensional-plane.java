import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Point> pointList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            pointList.add(new Point(i, toInt(st), toInt(st), toInt(st)));
        }

        new Solver(pointList).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<Point> pointList;
    List<Edge> edges = new ArrayList<>();
    int[] rootOf;

    public Solver(List<Point> pointList) {
        this.pointList = pointList;
        this.rootOf = new int[100_001];
    }

    public void solve() {
        addEdges(pointList, TargetEnum.X);
        addEdges(pointList, TargetEnum.Y);
        addEdges(pointList, TargetEnum.Z);

        edges.sort((a, b) -> Integer.compare(a.w, b.w));
        for (int v = 1; v <= 100_000; v++) {
            rootOf[v] = v;
        }

        long answer = 0;
        for (Edge edge : edges) {
            if (findWithCompact(edge.v1) == findWithCompact(edge.v2)) {
                continue;
            }
            union(edge.v1, edge.v2);
            answer += edge.w;
        }
        System.out.println(answer);
    }

    private void union(int v1, int v2) {
        int left = findWithCompact(v1);
        int right = findWithCompact(v2);

        rootOf[left] = right;
    }

    private int findWithCompact(int cur) {
        int parent = rootOf[cur];
        if (parent == cur) {
            return parent;
        }

        rootOf[cur] = findWithCompact(parent);
        return rootOf[cur];
    }

    private void addEdges(List<Point> pointList, TargetEnum targetEnum) {
        switch (targetEnum) {
            case X:
                pointList.sort((a, b) -> Integer.compare(a.x, b.x));
                break;
            case Y:
                pointList.sort((a, b) -> Integer.compare(a.y, b.y));
                break;
            case Z:
                pointList.sort((a, b) -> Integer.compare(a.z, b.z));
                break;
        }
        for (int i = 0; i < pointList.size() - 1; i++) {
            Point left = pointList.get(i);
            Point right = pointList.get(i + 1);
            switch (targetEnum) {
                case X:
                    edges.add(new Edge(left.number, right.number, right.x - left.x));
                    break;
                case Y:
                    edges.add(new Edge(left.number, right.number, right.y - left.y));
                    break;
                case Z:
                    edges.add(new Edge(left.number, right.number, right.z - left.z));
                    break;
            }
        }
    }

    private enum TargetEnum {
        X, Y, Z
    }
}

class Edge {

    int v1;
    int v2;
    int w;

    public Edge(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
}

class Point {

    int number;
    int x;
    int y;
    int z;

    public Point(int number, int x, int y, int z) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
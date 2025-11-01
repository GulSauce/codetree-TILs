import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, D;
        List<Point> points = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(
                new Point(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        new Solver(D, points).solve();
    }
}

class Point implements Comparable<Point> {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point other) {
        if (this.x == other.x) {
            return Integer.compare(this.y, other.y);
        }
        return Integer.compare(this.x, other.x);
    }
}

class YFirstPoint implements Comparable<YFirstPoint> {

    int x;
    int y;

    public YFirstPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(YFirstPoint other) {
        if (this.y == other.y) {
            return Integer.compare(this.x, other.x);
        }
        return Integer.compare(this.y, other.y);
    }
}

class Solver {


    int targetDiff;
    List<Point> points;
    TreeSet<YFirstPoint> YFirstPointTreeSet = new TreeSet<>();

    public Solver(
        int D,
        List<Point> points
    ) {
        this.targetDiff = D;
        this.points = points;
    }

    public void solve() {
        Collections.sort(points);
        int i = 0;
        int j = 0;
        int answer = Integer.MAX_VALUE;
        YFirstPointTreeSet.add(new YFirstPoint(points.get(j).x, points.get(j).y));
        for (i = 0; i < points.size() - 1; i++) {
            while (true) {
                if (j < i) {
                    break;
                }
                if (targetDiff <= getMax() - getMin()) {
                    answer = Math.min(answer, points.get(j).x - points.get(i).x);
                    break;
                }
                if (j == points.size() - 1) {
                    break;
                }
                j++;
                YFirstPointTreeSet.add(new YFirstPoint(points.get(j).x, points.get(j).y));
            }
            YFirstPointTreeSet.remove(new YFirstPoint(points.get(i).x, points.get(i).y));
        }
        System.out.println(answer);
    }

    private int getMin() {
        return YFirstPointTreeSet.first().y;
    }

    private int getMax() {
        return YFirstPointTreeSet.last().y;
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<Line> lines = new ArrayList<>();
        int y, x1, x2;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken());
            x1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            lines.add(new Line(y, x1, x2));
        }
        br.close();

        new Solver(lines).solve();
    }
}

class Solver {

    ArrayList<Line> lines;
    ArrayList<Point> points = new ArrayList<>();
    TreeSet<YFirstPoint> yFirstPointSet = new TreeSet<>();
    HashSet<YFirstPoint> answer = new HashSet<>();

    public Solver(
        ArrayList<Line> lines
    ) {
        this.lines = lines;
    }


    public void solve() {
        for (Line line : lines) {
            points.add(new Point(line.y, line.start, Direction.start));
            points.add(new Point(line.y, line.end, Direction.end));
        }
        Collections.sort(points);
        for (Point point : points) {
            switch (point.direction) {
                case start:
                    yFirstPointSet.add(new YFirstPoint(point));
                    answer.add(yFirstPointSet.first());
                    break;
                case end:
                    yFirstPointSet.remove(new YFirstPoint(point));
                    if (!yFirstPointSet.isEmpty()) {
                        answer.add(yFirstPointSet.first());
                    }
                    break;
            }
        }
        System.out.println(answer.size());
    }
}

class YFirstPoint implements Comparable<YFirstPoint> {

    Point point;

    @Override
    public int compareTo(YFirstPoint other) {
        return Integer.compare(this.point.y, other.point.y);
    }

    public YFirstPoint(
        Point point
    ) {
        this.point = point;
    }
}

class Point implements Comparable<Point> {

    @Override
    public int compareTo(Point other) {
        return Integer.compare(this.x, other.x);
    }

    int y;
    int x;
    Direction direction;

    public Point(
        int y,
        int x,
        Direction direction
    ) {
        this.y = y;
        this.x = x;
        this.direction = direction;
    }

}

enum Direction {
    start,
    end
}

class Line {

    int y;
    int start;
    int end;

    public Line(
        int y,
        int x1,
        int x2
    ) {
        this.y = y;
        this.start = x1;
        this.end = x2;
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<Line> lines = new ArrayList<>();
        int a, b;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            lines.add(new Line(a, b));
        }
        br.close();

        new Solver(lines).solve();
    }
}

class Solver {

    ArrayList<Line> lines;
    ArrayList<Point> points = new ArrayList<>();
    HashSet<Integer> pointIndexSet = new HashSet<>();

    public Solver(
        ArrayList<Line> lines
    ) {
        this.lines = lines;
    }

    public void solve() {
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            points.add(new Point(line.start, i, Status.start));
            points.add(new Point(line.end, i, Status.end));
        }
        int prev = points.get(0).x;
        int answer = 0;
        Collections.sort(points);
        for (Point point : points) {
            if (!pointIndexSet.isEmpty()) {
                answer += point.x - prev;
            }
            switch (point.status) {
                case start:
                    pointIndexSet.add(point.index);
                    break;
                case end:
                    pointIndexSet.remove(point.index);
            }
            Point curPoint = point;
            prev = curPoint.x;
        }
        System.out.println(answer);
    }
}

class Point implements Comparable<Point> {

    @Override
    public int compareTo(Point other) {
        return Integer.compare(this.x, other.x);
    }

    int x;
    int index;
    Status status;

    public Point(
        int x,
        int index,
        Status status
    ) {
        this.x = x;
        this.index = index;
        this.status = status;
    }
}

class Line {

    int start;
    int end;

    public Line(
        int a,
        int b
    ) {
        this.start = a;
        this.end = b;
    }
}

enum Status {
    start,
    end
}
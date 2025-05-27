import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<Line> lines = new ArrayList<>();
        int x1, x2;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            lines.add(new Line(x1, x2));
        }
        br.close();

        new Solver(lines).solve();
    }
}


class Solver {

    ArrayList<Point> points = new ArrayList<>();
    ArrayList<Line> lines;

    public Solver(
        ArrayList<Line> lines
    ) {
        this.lines = lines;
    }

    public void solve() {
        for (Line line : lines) {
            points.add(new Point(line.start, 1));
            points.add(new Point(line.end, -1));
        }
        Collections.sort(points);

        int maxOverlap = 0;
        int curOverlap = 0;
        for (Point point : points) {
            curOverlap += point.value;
            maxOverlap = Math.max(maxOverlap, curOverlap);
        }
        System.out.println(maxOverlap);
    }
}

class Point implements Comparable<Point> {

    @Override
    public int compareTo(Point other) {
        return x - other.x;
    }

    int x;
    int value;

    public Point(
        int x,
        int value
    ) {
        this.x = x;
        this.value = value;
    }
}

class Line {

    int start;
    int end;

    public Line(
        int x1,
        int x2
    ) {
        this.start = x1;
        this.end = x2;
    }
}

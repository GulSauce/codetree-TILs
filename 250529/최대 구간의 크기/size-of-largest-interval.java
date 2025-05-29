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
    HashSet<Integer> lineIndexSet = new HashSet<>();

    public Solver(
        ArrayList<Line> lines
    ) {
        this.lines = lines;
    }

    public void solve() {
        for (int i = 0; i < lines.size(); i++) {
            points.add(new Point(lines.get(i).start, i, Direction.L));
            points.add(new Point(lines.get(i).end, i, Direction.R));
        }
        Collections.sort(points);

        int answer = 0;
        int start = Integer.MIN_VALUE;
        for (Point point : points) {
            switch (point.direction) {
                case L:
                    if (lineIndexSet.isEmpty()) {
                        start = point.x;
                    }
                    lineIndexSet.add(point.index);
                    break;
                case R:
                    lineIndexSet.remove(point.index);
                    if (lineIndexSet.isEmpty()) {
                        answer = Math.max(answer, point.x - start);
                    }
                    break;
            }
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
    Direction direction;

    public Point(
        int x,
        int index,
        Direction direction
    ) {
        this.x = x;
        this.index = index;
        this.direction = direction;
    }
}

enum Direction {
    L,
    R
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

enum Status {
    start,
    end
}

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
    HashSet<Integer> lineExist = new HashSet<>();

    public Solver(
            ArrayList<Line> lines
    ) {
        this.lines = lines;
    }

    public void solve() {
        for (int i = 0; i < lines.size(); i++) {
            Point start = new Point(lines.get(i).start, i, Status.start);
            Point end = new Point(lines.get(i).end, i, Status.end);
            points.add(start);
            points.add(end);
        }
        Collections.sort(points);

        int answer = 0;
        for (Point point : points) {
            if (point.status == Status.start) {
                if (lineExist.isEmpty()) {
                    answer++;
                }
                lineExist.add(point.lineIndex);
            }
            if (point.status == Status.end) {
                lineExist.remove(point.lineIndex);
            }
        }
        System.out.println(answer);
    }
}

class Point implements Comparable<Point> {

    int x;
    int lineIndex;
    Status status;

    public Point(
            int x,
            int lineIndex,
            Status status
    ) {
        this.x = x;
        this.lineIndex = lineIndex;
        this.status = status;
    }

    @Override
    public int compareTo(Point other) {
        return Integer.compare(x, other.x);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return lineIndex == point.lineIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lineIndex);
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

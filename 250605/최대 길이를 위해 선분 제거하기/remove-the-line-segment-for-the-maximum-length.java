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

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines.add(new Line(start, end));
        }
        br.close();

        new Solver(lines).solve();
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MIN_VALUE;
    ArrayList<Line> lines;
    ArrayList<Point> points = new ArrayList<>();
    HashSet<Integer> pointExist = new HashSet<>();
    int[] uniqueDistEachLine = new int[100001];

    public Solver(
        ArrayList<Line> lines
    ) {
        this.lines = lines;
    }

    public void solve() {
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            points.add(new Point(line.start, i, Direction.START));
            points.add(new Point(line.end, i, Direction.END));
        }
        Collections.sort(points);

        int prevX = points.get(0).x;
        int curIndex = 0;

        while (curIndex < points.size()) {
            Point standardPoint = points.get(curIndex);
            if (pointExist.size() == 1) {
                int uniqueLineIndex = pointExist.iterator().next();
                uniqueDistEachLine[uniqueLineIndex] += standardPoint.x - prevX;
            }

            Point cur = points.get(curIndex);
            while (cur.x == standardPoint.x) {
                switch (cur.direction) {
                    case START:
                        pointExist.add(cur.index);
                        break;
                    case END:
                        pointExist.remove(cur.index);
                        break;
                }

                int nextIndex = curIndex + 1;
                curIndex = nextIndex;
                if (nextIndex == points.size()) {
                    break;
                }
                Point next = points.get(nextIndex);
                cur = next;
            }
            prevX = standardPoint.x;
        }

        prevX = points.get(0).x;

        int distSum = 0;
        for (Point point : points) {
            distSum += point.x - prevX;
            prevX = point.x;
        }

        int answer = 0;

        for (int i = 0; i < lines.size(); i++) {
            answer = Math.max(answer, distSum - uniqueDistEachLine[i]);
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
    START,
    END
}

class Line {

    int start;
    int end;

    public Line(
        int start,
        int end
    ) {
        this.start = start;
        this.end = end;
    }
}
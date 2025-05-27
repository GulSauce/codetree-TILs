import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N, K;
        ArrayList<LineDrawInfo> lineDrawInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            LineDrawInfo lineDrawInfo = new LineDrawInfo(Integer.parseInt(st.nextToken()),
                Direction.valueOf(st.nextToken()));
            lineDrawInfos.add(lineDrawInfo);
        }
        br.close();

        new Solver(K, lineDrawInfos).solve();
    }
}

class Solver {

    int minIntersectCount;
    final int LEFT = 1;
    final int RIGHT = -1;
    ArrayList<Point> points = new ArrayList<>();
    ArrayList<LineDrawInfo> lineDrawInfos;

    public Solver(
        int K,
        ArrayList<LineDrawInfo> lineDrawInfos
    ) {
        this.minIntersectCount = K;
        this.lineDrawInfos = lineDrawInfos;
    }

    public void solve() {
        int curPos = 0;
        for (LineDrawInfo lineDrawInfo : lineDrawInfos) {
            switch (lineDrawInfo.direction) {
                case L:
                    points.add(new Point(curPos, RIGHT));
                    curPos -= lineDrawInfo.moveCount;
                    points.add(new Point(curPos, LEFT));
                    break;
                case R:
                    points.add(new Point(curPos, LEFT));
                    curPos += lineDrawInfo.moveCount;
                    points.add(new Point(curPos, RIGHT));
                    break;
            }
        }

        Collections.sort(points);
        int answer = 0;
        int curCount = 0;
        for (Point point : points) {
            curCount += point.value;
            if (curCount < minIntersectCount) {
                continue;
            }
            answer++;
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
    int value;

    public Point(
        int x,
        int value
    ) {
        this.x = x;
        this.value = value;
    }
}

class LineDrawInfo {

    int moveCount;
    Direction direction;

    public LineDrawInfo(
        int moveCount,
        Direction direction
    ) {
        this.moveCount = moveCount;
        this.direction = direction;
    }
}

enum Direction {
    L,
    R
}
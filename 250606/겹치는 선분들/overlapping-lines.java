import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
    HashSet<Integer> pointIndexSet = new HashSet<>();

    public Solver(
        int K,
        ArrayList<LineDrawInfo> lineDrawInfos
    ) {
        this.minIntersectCount = K;
        this.lineDrawInfos = lineDrawInfos;
    }

    public void solve() {
        int curPos = 0;
        for (int i = 0; i < lineDrawInfos.size(); i++) {
            LineDrawInfo lineDrawInfo = lineDrawInfos.get(i);
            switch (lineDrawInfo.direction) {
                case L:
                    points.add(new Point(curPos, i, RIGHT));
                    curPos -= lineDrawInfo.moveCount;
                    points.add(new Point(curPos, i, LEFT));
                    break;
                case R:
                    points.add(new Point(curPos, i, LEFT));
                    curPos += lineDrawInfo.moveCount;
                    points.add(new Point(curPos, i, RIGHT));
                    break;
            }
        }

        Collections.sort(points);

        int answer = 0;
        int curIndex = 0;
        int prevX = points.get(0).x;
        while (curIndex < points.size()) {
            Point standardPoint = points.get(curIndex);
            if (pointIndexSet.size() >= minIntersectCount) {
                answer += standardPoint.x - prevX;
            }

            Point cur = points.get(curIndex);
            while (standardPoint.x == cur.x) {
                switch (cur.value) {
                    case LEFT:
                        pointIndexSet.add(cur.index);
                        break;
                    case RIGHT:
                        pointIndexSet.remove(cur.index);
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
    int value;

    public Point(
        int x,
        int index,
        int value
    ) {
        this.x = x;
        this.index = index;
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
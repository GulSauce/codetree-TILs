import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Point> pointList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pointList.add(new Point(toInt(st), toInt(st)));
        }

        new Solver(pointList).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<Point> pointList;

    final long NOT_ALLOCATED = Long.MAX_VALUE;
    long[][] dp;

    public Solver(List<Point> pointList) {
        this.pointList = pointList;
        this.dp = new long[pointList.size()][pointList.size()];
    }

    public void solve() {
        pointList.sort((a, b) -> Integer.compare(a.x, b.x));
        for (long[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        dp[0][0] = 0;
        for (int i = 0; i < pointList.size(); i++) {
            for (int j = 0; j < pointList.size(); j++) {
                int curNumber = Math.max(i, j) + 1;
                if (curNumber == pointList.size()) {
                    continue;
                }
                if (dp[i][j] == NOT_ALLOCATED) {
                    continue;
                }
                {
                    Point prev = pointList.get(i);
                    Point cur = pointList.get(curNumber);
                    dp[curNumber][j] = Math.min(dp[i][j] + cur.getDist(prev), dp[curNumber][j]);
                }
                {
                    Point prev = pointList.get(j);
                    Point cur = pointList.get(curNumber);
                    dp[i][curNumber] = Math.min(dp[i][j] + cur.getDist(prev), dp[i][curNumber]);
                }
            }
        }
        long answer = NOT_ALLOCATED;
        for (int i = 0; i < pointList.size(); i++) {
            Point a = pointList.get(i);
            Point b = pointList.get(pointList.size() - 1);
            if (dp[pointList.size() - 1][i] != NOT_ALLOCATED) {
                answer = Math.min(answer, dp[pointList.size() - 1][i] + a.getDist(b));
            }
            if (dp[i][pointList.size() - 1] != NOT_ALLOCATED) {
                answer = Math.min(answer, dp[i][pointList.size() - 1] + b.getDist(a));
            }
        }

        System.out.println(answer);
    }
}

class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDist(Point other) {
        return (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
    }
}
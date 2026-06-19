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

    final long NOT_ALLOCATED = Long.MAX_VALUE;
    long[][] dp;

    List<Point> pointList;

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

        for (int blue = 0; blue < pointList.size(); blue++) {
            for (int green = 0; green < pointList.size(); green++) {
                int curSelected = Math.max(blue, green) + 1;
                if (curSelected >= pointList.size()) {
                    continue;
                }
                if (dp[blue][green] == NOT_ALLOCATED) {
                    continue;
                }

                dp[curSelected][green] =
                    Math.min(
                        dp[curSelected][green],
                        dp[blue][green] + pointList.get(curSelected).getDist(pointList.get(blue))
                    );

                dp[blue][curSelected] =
                    Math.min(
                        dp[blue][curSelected],
                        dp[blue][green] + pointList.get(curSelected).getDist(pointList.get(green))
                    );
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i < pointList.size() - 1; i++) {
            Point end = pointList.get(pointList.size() - 1);
            answer = Math.min(answer,
                dp[pointList.size() - 1][i] + end.getDist(pointList.get(i)));
            answer = Math.min(answer,
                dp[i][pointList.size() - 1] + end.getDist(pointList.get(i)));
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

    public long getDist(Point other) {
        return (long) (x - other.x) * (x - other.x) + (long) (y - other.y) * (y - other.y);
    }
}
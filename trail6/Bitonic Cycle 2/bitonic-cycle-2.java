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
    long[][][] dp;

    List<Point> pointList;

    public Solver(List<Point> pointList) {
        this.pointList = pointList;
        this.dp = new long[pointList.size()][pointList.size()][2];
    }

    public void solve() {
        pointList.sort((a, b) -> Integer.compare(a.x, b.x));
        for (long[][] arrays : dp) {
            for (long[] array : arrays) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
        }

        dp[0][0][0] = 0;
        for (int blue = 0; blue < pointList.size(); blue++) {
            for (int green = 0; green < pointList.size(); green++) {
                int curAppend = Math.max(blue, green) + 1;
                if (curAppend >= pointList.size()) {
                    continue;
                }
                if (dp[blue][green][0] != NOT_ALLOCATED) {
                    // blue -> curAppend
                    dp[curAppend][green][0]
                        = Math.min(
                        dp[curAppend][green][0],
                        dp[blue][green][0] + pointList.get(curAppend).getDist(pointList.get(blue))
                    );

                    dp[curAppend][green][1]
                        = Math.min(
                        dp[curAppend][green][1],
                        dp[blue][green][0]
                    );

                    // green -> curAppend
                    dp[blue][curAppend][0]
                        = Math.min(
                        dp[blue][curAppend][0],
                        dp[blue][green][0] + pointList.get(curAppend).getDist(pointList.get(green))
                    );

                    dp[blue][curAppend][1]
                        = Math.min(
                        dp[blue][curAppend][1],
                        dp[blue][green][0]
                    );
                }
                if (dp[blue][green][1] != NOT_ALLOCATED) {
                    // blue -> curAppend
                    dp[curAppend][green][1]
                        = Math.min(
                        dp[curAppend][green][1],
                        dp[blue][green][1] + pointList.get(curAppend).getDist(pointList.get(blue))
                    );

                    // green -> curAppend
                    dp[blue][curAppend][1]
                        = Math.min(
                        dp[blue][curAppend][1],
                        dp[blue][green][1] + pointList.get(curAppend).getDist(pointList.get(green))
                    );
                }
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i < pointList.size(); i++) {
            Point end = pointList.get(pointList.size() - 1);
            if (dp[i][pointList.size() - 1][0] != NOT_ALLOCATED) {
                answer = Math.min(answer, dp[i][pointList.size() - 1][0]);
                answer = Math.min(answer,
                    dp[i][pointList.size() - 1][0] + end.getDist(pointList.get(i)));
            }

            if (dp[pointList.size() - 1][i][0] != NOT_ALLOCATED) {
                answer = Math.min(answer, dp[pointList.size() - 1][i][0]);
                answer = Math.min(answer,
                    dp[pointList.size() - 1][i][0] + end.getDist(pointList.get(i)));
            }

            if (dp[i][pointList.size() - 1][1] != NOT_ALLOCATED) {
                answer = Math.min(answer,
                    dp[i][pointList.size() - 1][1] + end.getDist(pointList.get(i)));
            }

            if (dp[pointList.size() - 1][i][1] != NOT_ALLOCATED) {
                answer = Math.min(answer,
                    dp[pointList.size() - 1][i][1] + end.getDist(pointList.get(i)));
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

    public long getDist(Point other) {
        return (long) (x - other.x) * (x - other.x) + (long) (y - other.y) * (y - other.y);
    }
}
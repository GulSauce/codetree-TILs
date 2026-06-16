import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    long[][] dp;

    List<Point> pointList;

    public Solver(List<Point> pointList) {
        this.pointList = pointList;
        this.dp = new long[pointList.size()][pointList.size()];
    }

    public void solve() {
        pointList.sort((a, b) -> Integer.compare(a.x, b.x));
        dp[1][0] = pointList.get(1).getDist(pointList.get(0));
        dp[0][1] = dp[1][0];
        for (int i = 2; i < pointList.size() - 1; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (j < i - 1) {
                    dp[i][j] = dp[i - 1][j] + pointList.get(i).getDist(pointList.get(i - 1));
                } else if (j == i - 1) {
                    long value = Long.MAX_VALUE;
                    for (int k = 0; k <= j - 1; k++) {
                        long temp = dp[i - 1][k] + pointList.get(i).getDist(pointList.get(k));
                        value = Math.min(value, temp);
                    }
                    dp[i][j] = value;
                }
                dp[j][i] = dp[i][j];
            }
        }

        long answer = Long.MAX_VALUE;
        if (pointList.size() <= 2) {
            System.out.println(pointList.get(0).getDist(pointList.get(1)) * 2);
        } else {
            for (int i = 0; i < pointList.size() - 2; i++) {
                answer = Math.min(answer, dp[pointList.size() - 2][i]
                    + pointList.get(i).getDist(pointList.get(pointList.size() - 1))
                    + pointList.get(pointList.size() - 2)
                    .getDist(pointList.get(pointList.size() - 1))
                );
            }
            System.out.println(answer);
        }
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
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

    final long NOT_ALLOCATED = Long.MAX_VALUE;
    long[][] dp;

    List<Point> pointList;

    public Solver(List<Point> pointList) {
        this.pointList = pointList;
        this.dp = new long[pointList.size()][pointList.size()];
    }

    public void solve() {
        pointList.sort((a, b) -> Integer.compare(a.x, b.x));
        // 점화식을 위해 초기값 적용
        dp[1][0] = pointList.get(1).getDist(pointList.get(0));
        dp[0][1] = dp[1][0];
        for (int curSelected = 2; curSelected < pointList.size() - 1; curSelected++) {
            int blue = curSelected - 1;
            for (int green = 0; green <= blue; green++) {
                if (green < blue) {
                    dp[curSelected][green] =
                        dp[blue][green] + pointList.get(curSelected).getDist(pointList.get(blue));
                    dp[green][curSelected] = dp[curSelected][green];
                }
                // green 자리가 blue 꼬리가 된다.
                // 따라서 green 이하의 아무 것과 curSelected를 잇는다
                else if (green == blue) {
                    long value = Long.MAX_VALUE;
                    for (int subGreen = 0; subGreen < green; subGreen++) {
                        long temp =
                            dp[green][subGreen]
                                + pointList.get(curSelected).getDist(pointList.get(subGreen));
                        value = Math.min(value, temp);
                    }
                    dp[curSelected][green] = value;
                    dp[green][curSelected] = value;
                }
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
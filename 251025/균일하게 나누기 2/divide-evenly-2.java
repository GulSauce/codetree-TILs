import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        List<Point> points = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(
                new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(points);

        new Solver(points).solve();
    }

}

class Point implements Comparable<Point> {

    int x;
    int y;

    public Point(
        int x,
        int y
    ) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point other) {
        int compareResult = Integer.compare(this.x, other.x);
        if (compareResult == 0) {
            return Integer.compare(this.y, other.y);
        }
        return compareResult;
    }
}

class Solver {

    List<Point> points;

    public Solver(
        List<Point> points
    ) {
        this.points = points;
    }

    public void solve() {
        int[] prefixCounts = new int[1001];

        for (Point point : points) {
            prefixCounts[point.y + 1]++;
        }
        for (int y = 2; y <= 1000; y++) {
            prefixCounts[y] += prefixCounts[y - 2];
        }

        int answer = Integer.MAX_VALUE;
        for (int y = 0; y <= 1000; y += 2) {
            int[] sabunmyoen = new int[5];
            sabunmyoen[4] = prefixCounts[y];
            sabunmyoen[2] = points.size() - prefixCounts[y];

            int i = 0;
            while (i < points.size()) {
                int xLine = points.get(i).x + 1;
                while (points.get(i).x < xLine) {
                    if (y < points.get(i).y) {
                        sabunmyoen[1]++;
                        sabunmyoen[2]--;
                    } else {
                        sabunmyoen[3]++;
                        sabunmyoen[4]--;
                    }
                    i++;
                    if (i == points.size()) {
                        break;
                    }
                }

                int curMax = Integer.MIN_VALUE;
                for (int j = 1; j <= 4; j++) {
                    curMax = Math.max(curMax, sabunmyoen[j]);
                }
                answer = Math.min(answer, curMax);
            }
        }

        System.out.println(answer);
    }
}
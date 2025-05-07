import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Point> points = new ArrayList<>();
        int x, y;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            points.add(new Point(x, y));
        }
        sc.close();

        new Solver(points).solve();
    }
}

class Solver {
    ArrayList<Point> points;

    public Solver(
            ArrayList<Point> points
    ) {
        this.points = points;
    }

    public void solve() {
        HashMap<Integer, Integer> lowestYHashMap = new HashMap<>();
        for (Point point : points) {
            if (lowestYHashMap.containsKey(point.x)) {
                int prevLowestY = lowestYHashMap.get(point.x);
                lowestYHashMap.put(point.x, Math.min(point.y, prevLowestY));
            } else {
                lowestYHashMap.put(point.x, point.y);
            }
        }

        int answer = 0;
        for (Integer lowestY : lowestYHashMap.values()) {
            answer += lowestY;
        }
        System.out.println(answer);
    }
}

class Point {
    int x;
    int y;

    public Point(
            int x,
            int y
    ) {
        this.x = x;
        this.y = y;
    }
}
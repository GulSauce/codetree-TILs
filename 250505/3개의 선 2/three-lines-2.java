import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        List<Point> points = new ArrayList<>();
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
    List<Point> points;

    public Solver(
            List<Point> points
    ) {
        this.points = points;
    }

    public void solve() {
        int answer = 0;
        for (int i = 0; i <= 10; i++) {
            for (int j = i + 1; j <= 10; j++) {
                for (int k = j + 1; k <= 10; k++) {
                    boolean pass = true;
                    for (Point point : points) {
                        if (point.x == i || point.x == j || point.x == k) {
                            continue;
                        }
                        pass = false;
                        break;
                    }
                    if (pass) {
                        answer = 1;
                    }
                }
            }
        }

        for (int i = 0; i <= 10; i++) {
            for (int j = i + 1; j <= 10; j++) {
                for (int k = 0; k <= 10; k++) {
                    boolean pass = true;
                    for (Point point : points) {
                        if (point.x == i || point.x == j || point.y == k) {
                            continue;
                        }
                        pass = false;
                        break;
                    }
                    if (pass) {
                        answer = 1;
                    }
                }
            }
        }

        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                for (int k = j + 1; k <= 10; k++) {
                    boolean pass = true;
                    for (Point point : points) {
                        if (point.x == i || point.y == j || point.y == k) {
                            continue;
                        }
                        pass = false;
                        break;
                    }
                    if (pass) {
                        answer = 1;
                    }
                }
            }
        }
        for (int i = 0; i <= 10; i++) {
            for (int j = i + 1; j <= 10; j++) {
                for (int k = j + 1; k <= 10; k++) {
                    boolean pass = true;
                    for (Point point : points) {
                        if (point.y == i || point.y == j || point.y == k) {
                            continue;
                        }
                        pass = false;
                        break;
                    }
                    if (pass) {
                        answer = 1;
                    }
                }
            }
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
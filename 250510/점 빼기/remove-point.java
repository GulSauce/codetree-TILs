import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<Integer> queries = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            points.add(new Point(sc.nextInt(), sc.nextInt()));
        }
        for (int i = 0; i < M; i++) {
            queries.add(sc.nextInt());
        }
        sc.close();

        new Solver(points, queries).solve();
    }
}

class Solver {
    ArrayList<Point> points;
    ArrayList<Integer> queries;
    TreeSet<Point> pointsSet;

    public Solver(
            ArrayList<Point> points,
            ArrayList<Integer> queries
    ) {
        this.points = points;
        this.queries = queries;
    }

    public void solve() {
        pointsSet = new TreeSet<>(points);
        Point notFound = new Point(-1, -1);
        for (Integer query : queries) {
            Point queryPoint = new Point(query, -1);
            Point result = pointsSet.ceiling(queryPoint);
            if (result == null) {
                System.out.println(notFound);
            } else {
                pointsSet.remove(result);
                System.out.println(result);
            }
        }
    }

}

class Point implements Comparable<Point> {
    @Override
    public int compareTo(Point other) {
        if (x == other.x) {
            return y - other.y;
        }
        return x - other.x;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

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
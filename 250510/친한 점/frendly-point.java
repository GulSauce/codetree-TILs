import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<Point> targetPoints = new ArrayList<>();
        ArrayList<Point> queryPoints = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            targetPoints.add(new Point(sc.nextInt(), sc.nextInt()));
        }
        for (int i = 0; i < M; i++) {
            queryPoints.add(new Point(sc.nextInt(), sc.nextInt()));
        }
        sc.close();

        new Solver(targetPoints, queryPoints).solve();
    }
}

class Solver {
    ArrayList<Point> targetPoints;
    ArrayList<Point> queryPoints;
    TreeSet<Point> pointsSet;

    public Solver(
            ArrayList<Point> targetPoints,
            ArrayList<Point> queryPoints
    ) {
        this.targetPoints = targetPoints;
        this.pointsSet = new TreeSet<>(targetPoints);
        this.queryPoints = queryPoints;
    }

    public void solve() {
        Point noResult = new Point(-1, -1);
        for (Point qureyPoint : queryPoints) {
            Point queryResult = pointsSet.ceiling(qureyPoint);
            if (queryResult == null) {
                System.out.println(noResult);
            } else {
                System.out.println(queryResult);
            }
        }
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;

    @Override
    public int compareTo(Point other) {
        if (x == other.x) {
            return y - other.y;
        }
        return x - other.x;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
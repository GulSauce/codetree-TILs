import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<Point> points = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            points.add(new Point(sc.nextInt(), sc.nextInt()));
        }

        new Solver(M, points).solve();
    }
}

class Solver {
    int repeatCount;
    ArrayList<Point> points;
    PriorityQueue<Point> priorityQueue;

    public Solver(
            int repeatCount,
            ArrayList<Point> points
    ) {
        this.repeatCount = repeatCount;
        this.points = points;
    }

    public void solve() {
        priorityQueue = new PriorityQueue<>(points);
        for (int i = 0; i < repeatCount; i++) {
            Point nearest = priorityQueue.poll();
            nearest.add2();
            priorityQueue.add(nearest);
        }
        System.out.println(priorityQueue.peek());
    }
}

class Point implements Comparable<Point> {
    @Override
    public int compareTo(Point other) {
        if (x + y == other.x + other.y) {
            if (x == other.x) {
                return y - other.y;
            }
            return x - other.x;
        }
        return x + y - (other.x + other.y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    public Point(
            int x,
            int y
    ) {
        this.x = x;
        this.y = y;
    }

    public void add2() {
        this.x = x + 2;
        this.y = y + 2;
    }

    int x;
    int y;
}
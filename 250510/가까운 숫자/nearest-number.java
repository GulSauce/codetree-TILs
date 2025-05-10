import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Integer> points = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            points.add(sc.nextInt());
        }
        sc.close();

        new Solver(points).solve();
    }
}

class Solver {
    TreeSet<Integer> pointsSet = new TreeSet<>();
    ArrayList<Integer> points;

    public Solver(
            ArrayList<Integer> points
    ) {
        this.points = points;
    }

    public void solve() {
        pointsSet.add(0);
        int dist = Integer.MAX_VALUE;
        for (Integer point : points) {
            pointsSet.add(point);
            Integer lower = pointsSet.lower(point);
            Integer higher = pointsSet.higher(point);
            if (lower == null && higher != null) {
                dist = Math.min(dist, higher - point);
            } else if (lower != null && higher == null) {
                dist = Math.min(dist, point - lower);
            } else if (lower != null && higher != null) {
                dist = Math.min(dist, higher - point);
                dist = Math.min(dist, point - lower);
            }
            System.out.println(dist);
        }
    }
}
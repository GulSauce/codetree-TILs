import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        int N, Q;
        ArrayList<Integer> points = new ArrayList<>();
        ArrayList<Range> ranges = new ArrayList<>();
        int a, b;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 0; i < N; i++) {
            points.add(sc.nextInt());
        }
        for (int i = 0; i < Q; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            ranges.add(new Range(a, b));
        }
        sc.close();

        new Solver(points, ranges).solve();
    }
}

class Solver {

    final int MAX_COUNT = 200_000;
    boolean[] exist = new boolean[MAX_COUNT + 1];
    int[] prefixSum = new int[MAX_COUNT + 1];

    ArrayList<Integer> points;
    ArrayList<Range> ranges;

    TreeSet<Integer> pointsTreeset = new TreeSet<>();
    HashMap<Integer, Integer> realVirtualMapper = new HashMap<>();

    public Solver(
        ArrayList<Integer> points,
        ArrayList<Range> ranges
    ) {
        this.points = points;
        this.ranges = ranges;
    }

    public void solve() {
        pointsTreeset.addAll(points);
        for (Range range : ranges) {
            pointsTreeset.add(range.start);
            pointsTreeset.add(range.end);
        }
        int cur = 1;
        for (Integer point : pointsTreeset) {
            realVirtualMapper.put(point, cur);
            int next = cur + 1;
            cur = next;
        }
        changePoints();
        for (Integer point : points) {
            exist[point] = true;
        }
        for (int i = 1; i <= MAX_COUNT; i++) {
            int value = exist[i] ? 1 : 0;
            prefixSum[i] = value + prefixSum[i - 1];
        }
        for (Range range : ranges) {
            int virtualStart = realVirtualMapper.get(range.start);
            int virtualEnd = realVirtualMapper.get(range.end);
            System.out.println(prefixSum[virtualEnd] - prefixSum[virtualStart - 1]);

        }
    }

    private void changePoints() {
        ArrayList<Integer> newPoints = new ArrayList<>();
        for (Integer point : points) {
            newPoints.add(realVirtualMapper.get(point));
        }
        points = newPoints;
    }
}

class Range {

    int start;
    int end;

    public Range(
        int start,
        int end
    ) {
        this.start = start;
        this.end = end;
    }
}
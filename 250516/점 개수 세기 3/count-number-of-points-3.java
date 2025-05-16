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

    ArrayList<Integer> points;
    ArrayList<Range> ranges;
    TreeSet<Integer> pointTreeSet;
    HashMap<Integer, Integer> realVirtualMapper = new HashMap<>();
    int[] prefixSum;

    public Solver(
        ArrayList<Integer> points,
        ArrayList<Range> ranges
    ) {
        this.prefixSum = new int[points.size() + 1];
        this.points = points;
        this.ranges = ranges;
    }

    public void solve() {
        pointTreeSet = new TreeSet<>(points);
        int cur = 1;
        for (Integer point : pointTreeSet) {
            realVirtualMapper.put(point, cur);
            int next = cur + 1;
            cur = next;
        }
        for (int i = 1; i < cur; i++) {
            prefixSum[i] = 1 + prefixSum[i - 1];
        }
        for (Range range : ranges) {
            int virtualEnd = realVirtualMapper.get(range.end);
            int virtualStart = realVirtualMapper.get(range.start);
            int pointCount = prefixSum[virtualEnd] - prefixSum[virtualStart - 1];
            System.out.println(pointCount);
        }
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
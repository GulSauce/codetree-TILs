import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
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

    int[] prefixSum;

    ArrayList<Integer> points;
    ArrayList<Range> ranges;

    TreeSet<Integer> pointsTreeset;
    HashMap<Integer, Integer> realVirtualMapper = new HashMap<>();

    public Solver(
        ArrayList<Integer> points,
        ArrayList<Range> ranges
    ) {
        this.prefixSum = new int[points.size() + 1];
        this.points = points;
        this.ranges = ranges;
    }

    public void solve() {
        pointsTreeset = new TreeSet<>(points);
        int cur = 1;
        for (Integer point : pointsTreeset) {
            realVirtualMapper.put(point, cur);
            int next = cur + 1;
            cur = next;
        }
        for (Integer virtual : realVirtualMapper.values()) {
            prefixSum[virtual] = 1 + prefixSum[virtual - 1];
        }
        for (Range range : ranges) {
            int nearStart = Optional.ofNullable(pointsTreeset.ceiling(range.start)).orElse(-1);
            int nearEnd = Optional.ofNullable(pointsTreeset.floor(range.end)).orElse(-1);
            if (nearStart == -1 || nearEnd == -1) {
                continue;
            }
            int start = realVirtualMapper.get(nearStart);
            int end = realVirtualMapper.get(nearEnd);
            System.out.println(prefixSum[end] - prefixSum[start - 1]);
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
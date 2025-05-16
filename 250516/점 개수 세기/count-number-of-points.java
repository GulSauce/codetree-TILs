import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        ArrayList<Integer> points = new ArrayList<>();
        ArrayList<Range> ranges = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            points.add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ranges.add(new Range(a, b));
        }
        br.close();

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
        for (int i = 1; i < cur; i++) {
            prefixSum[i] = 1 + prefixSum[i - 1];
        }
        for (Range range : ranges) {
            int nearStart = Optional.ofNullable(pointsTreeset.ceiling(range.start)).orElse(-1);
            int nearEnd = Optional.ofNullable(pointsTreeset.floor(range.end)).orElse(-1);
            if (nearStart == -1 || nearEnd == -1) {
                System.out.println(0);
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        long N, M;
        List<Line> lines = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toLong(st);
        M = toLong(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            lines.add(new Line(toLong(st), toLong(st)));
        }

        new Solver(N, lines).solve();
    }

    private static long toLong(StringTokenizer st) {
        return Long.parseLong(st.nextToken());
    }
}

class Line implements Comparable<Line> {

    long start;
    long end;

    public Line(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Line o) {
        return Long.compare(this.start, o.start);
    }
}

class Solver {

    long targetPointCount;
    List<Line> lines;

    public Solver(long targetPointCount, List<Line> lines) {
        this.targetPointCount = targetPointCount;
        this.lines = lines;
    }

    public void solve() {
        Collections.sort(lines);

        long left = 0;
        long right = Long.MAX_VALUE;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (isPutable(mid)) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isPutable(long dist) {
        int pointCount = 0;
        long cur = lines.get(0).start;
        for (Line line : lines) {
            cur = Math.max(cur, line.start);
            while (cur <= line.end) {
                pointCount++;
                cur = cur + dist;
            }
        }
        return targetPointCount <= pointCount;
    }
}
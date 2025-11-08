import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Line> lines = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lines.add(new Line(toInt(st), toInt(st)));
        }

        new Solver(lines).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Line implements Comparable<Line> {

    int start;
    int end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Line o) {
        return Integer.compare(this.start, o.start);
    }
}

class Solver {

    List<Line> lines;

    public Solver(List<Line> lines) {
        this.lines = lines;
    }

    public void solve() {
        Collections.sort(lines);
        int left = 1;
        int right = 1_000_000_000;
        int answer = 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPutable(mid)) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isPutable(int dist) {
        int cur = lines.get(0).start;
        for (int i = 1; i < lines.size(); i++) {
            Line line = lines.get(i);
            if (line.end - cur < dist) {
                return false;
            }
            cur = Math.max(line.start, cur + dist);
        }
        return true;
    }
}
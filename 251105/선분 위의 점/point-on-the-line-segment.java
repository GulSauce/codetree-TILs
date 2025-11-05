import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Integer> points = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            points.add(toInt(st));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            lines.add(new Line(toInt(st), toInt(st)));
        }

        new Solver(points, lines).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Line {

    int start;
    int end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Solver {

    List<Integer> points;
    List<Line> lines;

    public Solver(
        List<Integer> points,
        List<Line> lines
    ) {
        this.points = points;
        this.lines = lines;
    }

    public void solve() {
        Collections.sort(points);
        for (Line line : lines) {
            int end = getCustomBound(line.end);
            int start = getLowerBound(line.start);
            int count = end < start ? 0 : end - start + 1;
            System.out.println(count);
        }
    }

    private int getCustomBound(int query) {
        int left = 0;
        int right = points.size() - 1;
        int answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int found = points.get(mid);
            if (found <= query) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
            if (query < found) {
                right = mid - 1;
            }
        }
        return answer;
    }

    private int getLowerBound(int query) {
        int left = 0;
        int right = points.size() - 1;
        int answer = points.size();

        while (left <= right) {
            int mid = (left + right) / 2;
            int found = points.get(mid);
            if (found < query) {
                left = mid + 1;
            }
            if (query <= found) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            }
        }
        return answer;
    }
}
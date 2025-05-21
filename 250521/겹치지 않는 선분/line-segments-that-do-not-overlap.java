import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<Line> lines = new ArrayList<>();
        lines.add(new Line(Integer.MIN_VALUE, Integer.MIN_VALUE));
        int x1, x2;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            lines.add(new Line(x1, x2));
        }
        br.close();

        new Solver(lines).solve();
    }
}

class Solver {

    ArrayList<Line> lines;
    int[] leftPrefixMax;
    int[] rightPrefixMax;

    public Solver(
        ArrayList<Line> lines
    ) {
        this.lines = lines;
        this.leftPrefixMax = new int[lines.size()];
        this.rightPrefixMax = new int[lines.size() + 1];
    }

    public void solve() {
        Collections.sort(lines);
        Arrays.fill(leftPrefixMax, Integer.MIN_VALUE);
        Arrays.fill(rightPrefixMax, Integer.MAX_VALUE);
        for (int i = 1; i < lines.size(); i++) {
            leftPrefixMax[i] = Math.max(leftPrefixMax[i - 1], lines.get(i).end.x);
        }
        for (int i = lines.size() - 1; i >= 1; i--) {
            rightPrefixMax[i] = Math.min(rightPrefixMax[i + 1], lines.get(i).end.x);
        }

        int answer = 0;
        for (int i = 1; i < lines.size(); i++) {
            if (1 < i && lines.get(i).end.x <= leftPrefixMax[i - 1]) {
                continue;
            }
            if (i < lines.size() - 1 && rightPrefixMax[i + 1] <= lines.get(i).end.x) {
                continue;
            }
            answer++;
        }
        System.out.println(answer);
    }
}

class Line implements Comparable<Line> {

    Coordinate start;
    Coordinate end;

    @Override
    public int compareTo(Line other) {
        return Integer.compare(start.x, other.start.x);
    }

    public Line(
        int x1,
        int x2
    ) {
        this.start = new Coordinate(x1, 0);
        this.end = new Coordinate(x2, 1);
    }
}

class Coordinate {

    int x;
    int y;

    public Coordinate(
        int x,
        int y
    ) {
        this.x = x;
        this.y = y;
    }
}
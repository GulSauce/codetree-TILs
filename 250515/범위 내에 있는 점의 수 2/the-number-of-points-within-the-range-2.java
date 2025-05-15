import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, Q;
        ArrayList<Integer> points = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();
        int A, B;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 0; i < N; i++) {
            points.add(sc.nextInt());
        }
        for (int i = 0; i < Q; i++) {
            A = sc.nextInt();
            B = sc.nextInt();
            lines.add(new Line(A, B));
        }
        sc.close();

        new Solver(points, lines).solve();
    }
}

class Solver {

    final int MAX_VALUE = 1_000_000;
    final int OFFSET = 1;
    boolean[] pointsExist = new boolean[MAX_VALUE + 1 + OFFSET];
    int[] prefixSum = new int[MAX_VALUE + 1 + OFFSET];
    ArrayList<Integer> points;
    ArrayList<Line> lines;

    public Solver(
        ArrayList<Integer> points,
        ArrayList<Line> lines
    ) {
        this.points = points;
        this.lines = lines;
    }

    public void solve() {
        for (Integer point : points) {
            pointsExist[point + OFFSET] = true;
        }
        for (int i = 0; i <= MAX_VALUE; i++) {
            int value = pointsExist[i + OFFSET] ? 1 : 0;
            prefixSum[i + OFFSET] = value + prefixSum[i - 1 + OFFSET];
        }
        for (Line line : lines) {
            System.out.println(prefixSum[line.end + OFFSET] - prefixSum[line.start - 1 + OFFSET]);
        }
    }
}

class Line {

    int start;
    int end;

    public Line(
        int start,
        int end
    ) {
        this.start = start;
        this.end = end;
    }
}
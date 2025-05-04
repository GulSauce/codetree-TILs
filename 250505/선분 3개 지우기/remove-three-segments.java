import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        List<Line> lines = new ArrayList<>();
        int a, b;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            lines.add(new Line(a, b));
        }
        sc.close();

        new Solver(lines).solve();
    }
}

class Solver {
    int linesIndex;
    List<Line> lines;

    public Solver(
            List<Line> lines
    ) {
        this.linesIndex = lines.size() - 1;
        this.lines = lines;
    }

    public void solve() {
        int answer = 0;
        for (int i = 0; i <= linesIndex; i++) {
            for (int j = i + 1; j <= linesIndex; j++) {
                for (int k = j + 1; k <= linesIndex; k++) {
                    if (isCollideExcept(i, j, k)) {
                        continue;
                    }
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private boolean isCollideExcept(int first, int second, int third) {
        final int MAX_X = 100;
        int[] lineCount = new int[MAX_X + 1];
        for (int i = 0; i <= linesIndex; i++) {
            if (i == first || i == second || i == third) {
                continue;
            }
            Line line = lines.get(i);
            for (int j = line.start; j <= line.end; j++) {
                lineCount[j]++;
            }
        }

        boolean collide = false;
        for (int i = 0; i <= MAX_X; i++) {
            if (1 < lineCount[i]) {
                collide = true;
                break;
            }
        }
        return collide;
    }
}

class Line {
    int start;
    int end;

    public Line(
            int a,
            int b
    ) {
        this.start = a;
        this.end = b;
    }
}
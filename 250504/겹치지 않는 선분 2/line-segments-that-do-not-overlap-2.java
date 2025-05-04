import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        List<Line> lines = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int x1 = sc.nextInt();
            int x2 = sc.nextInt();
            lines.add(new Line(x1, x2));
        }
        sc.close();

        new Solver(lines).solve();
    }
}

class Solver {
    List<Line> lines;

    public Solver(
            List<Line> lines
    ) {
        this.lines = lines;
    }

    public void solve() {
        int answer = 0;
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            boolean collide = false;
            for (int j = 0; j < lines.size(); j++) {
                Line other = lines.get(j);
                if (i == j) {
                    continue;
                }
                if (line.isCollide(other)) {
                    collide = true;
                    break;
                }
            }
            if (!collide) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}

class Line {
    Coordinate start;
    Coordinate end;

    public Line(
            int x1,
            int x2
    ) {
        start = new Coordinate(x1, 0);
        end = new Coordinate(x2, 1);
    }

    public boolean isCollide(Line other) {
        if (start.x < other.start.x && end.x < other.end.x) {
            return false;
        }
        if (other.start.x < start.x && other.end.x < end.x) {
            return false;
        }
        return true;
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
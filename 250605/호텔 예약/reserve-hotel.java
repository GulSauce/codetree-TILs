import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<Reservation> reservations = new ArrayList<>();
        int i, s, e;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            reservations.add(new Reservation(i, s, e));
        }
        br.close();

        new Solver(reservations).solve();
    }
}

class Solver {

    ArrayList<Reservation> reservations;
    HashSet<Integer> pointExist = new HashSet<>();
    ArrayList<Point> points = new ArrayList<>();

    public Solver(
        ArrayList<Reservation> reservations
    ) {
        this.reservations = reservations;
    }

    public void solve() {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            points.add(new Point(reservation.start, i, Direction.START));
            points.add(new Point(reservation.end, i, Direction.END));
        }
        Collections.sort(points);

        int answer = Integer.MIN_VALUE;
        for (Point point : points) {
            switch (point.direction) {
                case START:
                    pointExist.add(point.index);
                    break;

                case END:
                    pointExist.remove(point.index);
                    break;
            }
            answer = Math.max(answer, pointExist.size());
        }

        System.out.println(answer);
    }

}

class Point implements Comparable<Point> {

    @Override
    public int compareTo(Point other) {
        if (this.x == other.x) {
            if (this.direction == Direction.START && other.direction == Direction.END) {
                return 1;
            }
            return 0;
        }
        return Integer.compare(this.x, other.x);
    }

    int x;
    int index;
    Direction direction;

    public Point(
        int x,
        int index,
        Direction direction
    ) {
        this.x = x;
        this.index = index;
        this.direction = direction;
    }
}

enum Direction {
    START,
    END
}

class Reservation {

    int personNumber;
    int start;
    int end;

    public Reservation(
        int i,
        int s,
        int e
    ) {
        this.personNumber = i;
        this.start = s;
        this.end = e;
    }
}
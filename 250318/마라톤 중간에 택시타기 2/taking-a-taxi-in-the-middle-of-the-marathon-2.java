import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int coordinatesIndex;
        List<Coordinate> coordinates;

        public Solver(
            int N,
            List<Coordinate> coordinates
        ) {
            this.coordinatesIndex = N;
            this.coordinates = coordinates;
        }

        public void solve() {
            int answer = 0;
            for (int i = 2; i <= coordinatesIndex - 1; i++) {
                int taxiDistSum = getTaxiDistSumSkipIndex(i);
                answer = Math.min(answer, taxiDistSum);
            }
            System.out.println(answer);
        }

        private int getTaxiDistSumSkipIndex(int index) {
            int taxiDistSum = 0;
            for (int i = 1; i <= coordinatesIndex - 1; i++) {
                if (i + 1 == index) {
                    taxiDistSum += getTaxiDist(coordinates.get(i), coordinates.get(i + 2));
                    continue;
                }
                if (i == index) {
                    continue;
                }
                taxiDistSum += getTaxiDist(coordinates.get(i), coordinates.get(i + 1));
            }
            return taxiDistSum;
        }

        private int getTaxiDist(Coordinate first, Coordinate second) {
            return Math.abs(first.x - second.x) + Math.abs(first.y - second.y);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(-1, -1));
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            coordinates.add(new Coordinate(sc.nextInt(), sc.nextInt()));
        }
        new Solver(N, coordinates).solve();
    }

    private static class Coordinate {

        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
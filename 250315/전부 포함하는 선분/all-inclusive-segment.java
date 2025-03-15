import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        List<Coordinate> coordinates;

        public Solver(
            List<Coordinate> coordinates
        ) {
            this.coordinates = coordinates;
        }

        public void solve() {
            int leftMostX = getLeftMostX();
            int rightMostX = getRightMostX();
            int answer = Math.min(getLengthRemoved(leftMostX), getLengthRemoved(rightMostX));
            System.out.println(answer);
        }

        private int getLengthRemoved(int target) {
            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < coordinates.size(); i++) {
                Coordinate coordinate = coordinates.get(i);
                if (coordinate.x1 != target && coordinate.x2 != target) {
                    continue;
                }
                answer = Math.min(answer,
                    getRightMostXWithOutIndex(i) -
                        getLeftMostXWithOutIndex(i));
            }
            return answer;
        }

        private int getRightMostXWithOutIndex(int index) {
            int rightMostX = Integer.MIN_VALUE;
            for (int i = 0; i < coordinates.size(); i++) {
                if (i == index) {
                    continue;
                }
                rightMostX = Math.max(rightMostX, coordinates.get(i).x2);
            }
            return rightMostX;
        }

        private int getLeftMostXWithOutIndex(int index) {
            int leftMostX = Integer.MAX_VALUE;
            for (int i = 0; i < coordinates.size(); i++) {
                if (i == index) {
                    continue;
                }
                leftMostX = Math.min(leftMostX, coordinates.get(i).x1);
            }
            return leftMostX;
        }

        private int getRightMostX() {
            int rightMostX = Integer.MIN_VALUE;
            for (Coordinate coordinate : coordinates) {
                rightMostX = Math.max(rightMostX, coordinate.x2);
            }
            return rightMostX;
        }

        private int getLeftMostX() {
            int leftMostX = Integer.MAX_VALUE;
            for (Coordinate coordinate : coordinates) {
                leftMostX = Math.min(leftMostX, coordinate.x1);
            }
            return leftMostX;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            coordinates.add(new Coordinate(sc.nextInt(), sc.nextInt()));
        }
        new Solver(coordinates).solve();
    }

    private static class Coordinate {

        int x1;
        int x2;

        public Coordinate(
            int x1,
            int x2
        ) {
            this.x1 = x1;
            this.x2 = x2;
        }
    }
}
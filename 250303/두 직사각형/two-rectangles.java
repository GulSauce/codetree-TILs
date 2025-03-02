import java.util.Scanner;

public class Main {

    private static class Solver {

        Coordinate firstLeftBelow;
        Coordinate firstRightUpper;
        Coordinate secondLeftBelow;
        Coordinate secondRightUpper;

        public Solver(
            int x1,
            int y1,
            int x2,
            int y2,
            int a1,
            int b1,
            int a2,
            int b2
        ) {
            this.firstLeftBelow = new Coordinate(x1, y1);
            this.firstRightUpper = new Coordinate(x2, y2);
            this.secondLeftBelow = new Coordinate(a1, b1);
            this.secondRightUpper = new Coordinate(a2, b2);
        }

        public void solve() {
            boolean overlap = isOverlap();
            printAnswer(overlap);
        }

        private void printAnswer(boolean overlap) {
            if (overlap) {
                System.out.println("overlapping");
                return;
            }
            System.out.println("nonoverlapping");
        }

        private boolean isOverlap() {
            if (firstRightUpper.x < secondLeftBelow.x) {
                return false;
            }
            if (secondRightUpper.x < secondLeftBelow.x) {
                return false;
            }
            if (firstRightUpper.y < secondLeftBelow.y) {
                return false;
            }
            if (secondRightUpper.y < firstLeftBelow.y) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        int a1 = sc.nextInt();
        int b1 = sc.nextInt();
        int a2 = sc.nextInt();
        int b2 = sc.nextInt();
        new Solver(
            x1,
            y1,
            x2,
            y2,
            a1,
            b1,
            a2,
            b2
        ).solve();
    }

    private static class Coordinate {

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
}
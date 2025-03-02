import java.util.Scanner;

public class Main {

    private static class Solver {

        int x1;
        int x2;
        int x3;
        int x4;

        public Solver(
            int x1,
            int x2,
            int x3,
            int x4
        ) {
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
            this.x4 = x4;
        }

        public void solve() {
            boolean intersect = isIntersect();
            printResult(intersect);
        }

        private void printResult(boolean intersect) {
            if (intersect) {
                System.out.println("intersecting");
                return;
            }
            System.out.println("nonintersecting");
        }

        private boolean isIntersect() {
            if (x2 < x3 || x4 < x1) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt();
        int x2 = sc.nextInt();
        int x3 = sc.nextInt();
        int x4 = sc.nextInt();

        new Solver(x1, x2, x3, x4).solve();
    }
}
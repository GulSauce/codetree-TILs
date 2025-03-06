import java.util.Scanner;

public class Main {

    private static class Solver {

        int source;
        int dest;
        int x;
        int y;

        int noTeleportDist;
        int xYTeleportDist;
        int yXTeleportDist;

        public Solver(
            int A,
            int B,
            int x,
            int y
        ) {
            this.source = A;
            this.dest = B;
            this.x = x;
            this.y = y;
        }

        public void solve() {
            setNoTeleportDist();
            setXYTeleportDist();
            setYXTeleportDist();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(Math.min(
                Math.min(noTeleportDist, xYTeleportDist), yXTeleportDist
            ));
        }

        private void setYXTeleportDist() {
            this.yXTeleportDist = Math.abs(source - y) + Math.abs(x - dest);
        }

        private void setXYTeleportDist() {
            this.xYTeleportDist = Math.abs(source - x) + Math.abs(y - dest);
        }

        private void setNoTeleportDist() {
            this.noTeleportDist = Math.abs(source - dest);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        new Solver(A, B, x, y).solve();
    }
}
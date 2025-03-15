import java.util.Scanner;

public class Main {

    private static class Solver {

        Square A;
        Square B;
        Square M;

        int[][] grid = new int[2001][2001];

        public Solver(
            Square A,
            Square B,
            Square M
        ) {
            this.A = A;
            this.B = B;
            this.M = M;
        }

        public void solve() {
            drawSquareOnGrid(A);
            drawSquareOnGrid(B);
            eraseSquareFromGrid(M);
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int y = 0; y <= 2000; y++) {
                for (int x = 0; x <= 2000; x++) {
                    if (grid[y][x] == 1) {
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }

        private void drawSquareOnGrid(Square square) {
            for (int y = square.leftBelowY; y < square.rightUpperY; y++) {
                for (int x = square.leftBelowX; x < square.rightUpperX; x++) {
                    grid[y][x] = 1;
                }
            }
        }

        private void eraseSquareFromGrid(Square square) {
            for (int y = square.leftBelowY; y < square.rightUpperY; y++) {
                for (int x = square.leftBelowX; x < square.rightUpperX; x++) {
                    grid[y][x] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Square A = new Square(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        Square B = new Square(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        Square M = new Square(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());

        new Solver(A, B, M).solve();
    }

    private static class Square {

        int leftBelowX;
        int leftBelowY;
        int rightUpperX;
        int rightUpperY;

        final int OFFSET = 1000;

        public Square(
            int x1,
            int y1,
            int x2,
            int y2
        ) {
            this.leftBelowX = x1 + OFFSET;
            this.leftBelowY = y1 + OFFSET;
            this.rightUpperX = x2 + OFFSET;
            this.rightUpperY = y2 + OFFSET;
        }
    }
}
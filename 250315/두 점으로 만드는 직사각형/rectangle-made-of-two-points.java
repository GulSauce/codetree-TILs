import java.util.Scanner;

public class Main {

    private static class Solver {

        private Square square1;
        private Square square2;

        int leftMostX;
        int rightMostX;
        int upperMostY;
        int belowMostY;

        public Solver(
            Square square1,
            Square square2
        ) {
            this.square1 = square1;
            this.square2 = square2;
        }

        public void solve() {
            leftMostX = Math.min(square1.leftBelowX, square2.leftBelowX);
            rightMostX = Math.max(square1.rightUpperX, square2.rightUpperX);
            belowMostY = Math.min(square1.leftBelowY, square2.leftBelowY);
            upperMostY = Math.max(square1.rightUpperY, square2.rightUpperY);
            int area = (rightMostX - leftMostX) * (upperMostY - belowMostY);
            System.out.println(area);
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

        new Solver(new Square(x1, y1, x2, y2), new Square(a1, b1, a2, b2)).solve();
    }

    private static class Square {

        int leftBelowX;
        int leftBelowY;

        int rightUpperX;
        int rightUpperY;

        public Square(
            int x1,
            int y1,
            int x2,
            int y2
        ) {
            this.leftBelowX = x1;
            this.leftBelowY = y1;
            this.rightUpperX = x2;
            this.rightUpperY = y2;
        }
    }
}
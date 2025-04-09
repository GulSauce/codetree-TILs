import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M;
        int[][] grid;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        grid = new int[N + 1][M + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                grid[row][col] = sc.nextInt();
            }
        }
        sc.close();

        new Solver(N, M, grid).solve();
    }
}

class Solver {

    int answer = Integer.MIN_VALUE;
    int gridRow;
    int gridCol;
    int[][] grid;

    public Solver(
        int N,
        int M,
        int[][] grid
    ) {
        this.gridRow = N;
        this.gridCol = M;
        this.grid = grid;
    }

    public void solve() {
        for (int firstStartRow = 1; firstStartRow <= gridRow; firstStartRow++) {
            for (int firstStartCol = 1; firstStartCol <= gridCol; firstStartCol++) {
                for (int secondStartRow = 1; secondStartRow <= gridRow; secondStartRow++) {
                    for (int secondStartCol = 1; secondStartCol <= gridCol; secondStartCol++) {
                        getSumWithSetEnd(new Coordinate(firstStartRow, firstStartCol),
                            new Coordinate(secondStartRow, secondStartCol));
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public void getSumWithSetEnd(Coordinate firstStartCoordinate, Coordinate secndStartCoordinate) {
        for (int firstEndRow = firstStartCoordinate.row; firstEndRow <= gridRow;
            firstEndRow++) {
            for (int firstEndCol = firstStartCoordinate.col; firstEndCol <= gridCol;
                firstEndCol++) {
                for (int secondEndRow = secndStartCoordinate.row; secondEndRow <= gridRow;
                    secondEndRow++) {
                    for (int secondEndCol = secndStartCoordinate.col; secondEndCol <= gridCol;
                        secondEndCol++) {
                        Coordinate firstEndCoordinate = new Coordinate(firstEndRow, firstEndCol);
                        Coordinate secondEndCoordinate = new Coordinate(secondEndRow, secondEndCol);
                        Square first = new Square(firstStartCoordinate, firstEndCoordinate);
                        Square second = new Square(secndStartCoordinate, secondEndCoordinate);

                        if (isCollide(first, second)) {
                            continue;
                        }

                        int firstArea = getGridArea(first);
                        int secondArea = getGridArea(second);
                        int areaSum = firstArea + secondArea;
                        answer = Math.max(answer, areaSum);
                    }
                }
            }
        }
    }

    private boolean isCollide(Square first, Square second) {
        if (first.end.col < second.start.col) {
            return false;
        }
        if (second.end.col < first.start.col) {
            return false;
        }
        if (first.end.row < second.start.row) {
            return false;
        }
        if (second.end.row < first.start.row) {
            return false;
        }
        return true;
    }

    private int getGridArea(Square square) {
        int area = 0;
        Coordinate start = square.start;
        Coordinate end = square.end;
        for (int row = start.row; row <= end.row; row++) {
            for (int col = start.col; col <= end.col; col++) {
                area += grid[row][col];
            }
        }
        return area;
    }
}

class Square {

    Coordinate start;
    Coordinate end;

    public Square(
        Coordinate start,
        Coordinate end
    ) {
        this.start = start;
        this.end = end;
    }

}

class Coordinate {

    int row;
    int col;

    public Coordinate(
        int row,
        int col
    ) {
        this.row = row;
        this.col = col;
    }
}
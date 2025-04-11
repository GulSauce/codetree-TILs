import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        int[][] grid;
        int r, c, m1, m2, m3, m4, dir;
        SquareInfo squareInfo;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        grid = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                grid[row][col] = sc.nextInt();
            }
        }
        r = sc.nextInt();
        c = sc.nextInt();
        m1 = sc.nextInt();
        m2 = sc.nextInt();
        m3 = sc.nextInt();
        m4 = sc.nextInt();
        dir = sc.nextInt();

        squareInfo = new SquareInfo(r, c, m1, m2);
        new Solver(N, grid, squareInfo, dir).solve();
    }
}

class Solver {

    int gridIndex;
    int[][] grid;
    SquareInfo squareInfo;
    Direction direction;

    public Solver(
        int N,
        int[][] grid,
        SquareInfo squareInfo,
        int d
    ) {
        this.gridIndex = N;
        this.grid = grid;
        this.squareInfo = squareInfo;
        if (d == 0) {
            this.direction = Direction.COUNTER_CLOCK_WISE;
        }
        if (d == 1) {
            this.direction = Direction.CLOCK_WISE;
        }
    }

    public void solve() {
        if (direction == Direction.COUNTER_CLOCK_WISE) {
            rotateCounterClockWise();
        }
        if (direction == Direction.CLOCK_WISE) {
            rotateClockWise();
        }
        printAnswer();
    }

    private void printAnswer() {
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }


    private void rotateClockWise() {
        int height = squareInfo.height;
        int width = squareInfo.width;
        int startRow = squareInfo.startRow;
        int startCol = squareInfo.startCol;

        int temp = grid[startRow - height][startCol - height];

        int row = startRow - height;
        int col = startCol - height;
        for (int d = 1; d <= height; d++) {
            grid[row + d - 1][col + d - 1] = grid[row + d][col + d];
        }

        row = startRow;
        col = startCol;
        for (int d = 1; d <= width; d++) {
            grid[row - d + 1][col + d - 1] = grid[row - d][col + d];
        }

        row = startRow - width;
        col = startCol + width;
        for (int d = 1; d <= height; d++) {
            grid[row - d + 1][col - d + 1] = grid[row - d][col - d];
        }

        row = startRow - width - height;
        col = startCol + width - height;
        for (int d = 1; d <= width - 1; d++) {
            grid[row + d - 1][col - d + 1] = grid[row + d][col - d];
        }
        grid[row + width - 1][col - width + 1] = temp;
    }

    private void rotateCounterClockWise() {
        int height = squareInfo.height;
        int width = squareInfo.width;
        int startRow = squareInfo.startRow;
        int startCol = squareInfo.startCol;

        int temp = grid[startRow - width][startCol + width];

        int row = startRow - width;
        int col = startCol + width;
        for (int d = 1; d <= width; d++) {
            grid[row + d - 1][col - d + 1] = grid[row + d][col - d];
        }

        row = startRow;
        col = startCol;
        for (int d = 1; d <= height; d++) {
            grid[row - d + 1][col - d + 1] = grid[row - d][col - d];
        }

        row = startRow - height;
        col = startCol - height;
        for (int d = 1; d <= width; d++) {
            grid[row - d + 1][col + d - 1] = grid[row - d][col + d];
        }

        row = startRow - width - height;
        col = startCol + width - height;
        for (int d = 1; d <= height - 1; d++) {
            grid[row + d - 1][col + d - 1] = grid[row + d][col + d];
        }
        grid[row + height - 1][col + height - 1] = temp;
    }
}

class SquareInfo {

    int startRow;
    int startCol;
    int width;
    int height;

    public SquareInfo(
        int r,
        int c,
        int m1,
        int m2
    ) {
        this.startRow = r;
        this.startCol = c;
        this.width = m1;
        this.height = m2;
    }
}

enum Direction {
    CLOCK_WISE,
    COUNTER_CLOCK_WISE
}
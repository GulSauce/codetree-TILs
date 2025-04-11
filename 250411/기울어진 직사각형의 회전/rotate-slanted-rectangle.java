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
        int curRow = squareInfo.startRow;
        int curCol = squareInfo.startCol;

        int temp = grid[curRow][curCol];
        for (int i = 0; i < width - 1; i++) {
            grid[curRow][curCol] = grid[curRow - 1][curCol + 1];
            curRow--;
            curCol++;
        }

        for (int i = 0; i < height - 1; i++) {
            grid[curRow][curCol] = grid[curRow - 1][curCol - 1];
            curRow--;
            curCol--;
        }

        for (int i = 0; i < width - 1; i++) {
            grid[curRow][curCol] = grid[curRow + 1][curCol - 1];
            curRow++;
            curCol--;
        }

        for (int i = 0; i < height - 2; i++) {
            grid[curRow][curCol] = grid[curRow + 1][curCol + 1];
            curRow++;
            curCol++;
        }

        grid[curRow][curCol] = temp;
    }

    private void rotateCounterClockWise() {
        int height = squareInfo.height;
        int width = squareInfo.width;
        int curRow = squareInfo.startRow;
        int curCol = squareInfo.startCol;

        int temp = grid[curRow][curCol];

        for (int i = 0; i < height - 1; i++) {
            grid[curRow][curCol] = grid[curRow - 1][curCol - 1];
            curRow--;
            curCol--;
        }

        for (int i = 0; i < width - 1; i++) {
            grid[curRow][curCol] = grid[curRow - 1][curCol + 1];
            curRow--;
            curCol++;
        }

        for (int i = 0; i < height - 1; i++) {
            grid[curRow][curCol] = grid[curRow + 1][curCol + 1];
            curRow++;
            curCol++;
        }

        for (int i = 0; i < width - 2; i++) {
            grid[curRow][curCol] = grid[curRow + 1][curCol - 1];
            curRow++;
            curCol--;
        }

        grid[curRow][curCol] = temp;
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
        this.width = m1 + 1;
        this.height = m2 + 1;
    }
}

enum Direction {
    CLOCK_WISE,
    COUNTER_CLOCK_WISE
}
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        int[][] grid;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        grid = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                grid[row][col] = sc.nextInt();
            }
        }
        sc.close();

        new Solver(N, grid).solve();
    }
}

class Solver {

    int gridIndex;

    int[][] grid;

    public Solver(
        int N,
        int[][] grid
    ) {
        this.gridIndex = N;
        this.grid = grid;
    }

    public void solve() {
        int answer = 0;
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                answer = Math.max(answer, getMaxSumCounterClockWiseTravel(row, col));
            }
        }
        System.out.println(answer);
    }

    private int getMaxSumCounterClockWiseTravel(int row, int col) {
        int maxSum = 0;
        for (int h = 1; h <= gridIndex; h++) {
            for (int w = 1; w <= gridIndex; w++) {
                maxSum = Math.max(maxSum, getCurSum(row, col, h, w));
            }
        }
        return maxSum;
    }

    private int getCurSum(int row, int col, int h, int w) {
        int curSum = 0;
        int curRow = row;
        int curCol = col;

        for (int leftBelowMoveCount = 0; leftBelowMoveCount < h; leftBelowMoveCount++) {
            int nextRow = curRow + 1;
            int nextCol = curCol - 1;
            if (isOutOfGrid(nextRow, nextCol)) {
                return 0;
            }
            curRow = nextRow;
            curCol = nextCol;
            curSum += grid[nextRow][nextCol];
        }

        for (int rightBelowMoveCount = 0; rightBelowMoveCount < w; rightBelowMoveCount++) {
            int nextRow = curRow + 1;
            int nextCol = curCol + 1;
            if (isOutOfGrid(nextRow, nextCol)) {
                return 0;
            }
            curRow = nextRow;
            curCol = nextCol;
            curSum += grid[nextRow][nextCol];
        }

        for (int rightUpperMoveCount = 0; rightUpperMoveCount < h; rightUpperMoveCount++) {
            int nextRow = curRow - 1;
            int nextCol = curCol + 1;
            if (isOutOfGrid(nextRow, nextCol)) {
                return 0;
            }
            curRow = nextRow;
            curCol = nextCol;
            curSum += grid[nextRow][nextCol];
        }

        for (int leftUpperMoveCount = 0; leftUpperMoveCount < w; leftUpperMoveCount++) {
            int nextRow = curRow - 1;
            int nextCol = curCol - 1;
            if (isOutOfGrid(nextRow, nextCol)) {
                return 0;
            }
            curRow = nextRow;
            curCol = nextCol;
            curSum += grid[nextRow][nextCol];
        }

        return curSum;
    }

    private boolean isOutOfGrid(int row, int col) {
        return row < 1 || gridIndex < row || col < 1 || gridIndex < col;
    }
}
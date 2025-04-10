import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M, Q;
        int[][] grid;
        List<WindBlowInfo> windBlowInfos = new ArrayList<>();
        int r1, c1, r2, c2;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Q = sc.nextInt();
        grid = new int[N + 1][M + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                grid[row][col] = sc.nextInt();
            }
        }
        for (int i = 0; i < Q; i++) {
            r1 = sc.nextInt();
            c1 = sc.nextInt();
            r2 = sc.nextInt();
            c2 = sc.nextInt();
            WindBlowInfo windBlowInfo = new WindBlowInfo(new Coordinate(r1, c1),
                new Coordinate(r2, c2));
            windBlowInfos.add(windBlowInfo);
        }

        new Solver(N, M, Q, grid, windBlowInfos).solve();
    }
}

class Solver {

    int gridRow;
    int gridCol;
    int windBlowInfosIndex;
    int[][] grid;
    int[][] gridCopy;


    List<WindBlowInfo> windBlowInfos;

    public Solver(
        int N,
        int M,
        int Q,
        int[][] grid,
        List<WindBlowInfo> windBlowInfos
    ) {
        this.gridRow = N;
        this.gridCol = M;
        this.windBlowInfosIndex = Q - 1;
        this.windBlowInfos = windBlowInfos;
        this.grid = grid;
        this.gridCopy = new int[N + 1][M + 1];
    }

    public void solve() {
        for (WindBlowInfo windBlowInfo : windBlowInfos) {
            rotateClockWise(windBlowInfo);
            changeByAverage(windBlowInfo);
        }
        printAnswer();
    }

    private void printAnswer() {
        for (int row = 1; row <= gridRow; row++) {
            for (int col = 1; col <= gridCol; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }


    private void changeByAverage(WindBlowInfo windBlowInfo) {
        copyGrid();
        for (int row = windBlowInfo.leftUpper.row; row <= windBlowInfo.rightLower.row; row++) {
            for (int col = windBlowInfo.leftUpper.col; col <= windBlowInfo.rightLower.col; col++) {
                int nearAvg = getNearSum(row, col);
                grid[row][col] = nearAvg;
            }
        }
    }

    private void copyGrid() {
        for (int row = 1; row <= gridRow; row++) {
            for (int col = 1; col <= gridCol; col++) {
                gridCopy[row][col] = grid[row][col];
            }
        }
    }

    private int getNearSum(int row, int col) {
        int sum = gridCopy[row][col];
        int count = 1;
        if (1 <= row - 1) {
            sum += gridCopy[row - 1][col];
            count++;
        }
        if (row + 1 <= gridRow) {
            sum += gridCopy[row + 1][col];
            count++;
        }
        if (1 <= col - 1) {
            sum += gridCopy[row][col - 1];
            count++;
        }
        if (col + 1 <= gridCol) {
            sum += gridCopy[row][col + 1];
            count++;
        }
        return sum / count;
    }

    private void rotateClockWise(WindBlowInfo windBlowInfo) {
        int remain = moveRightReturningRemain(windBlowInfo.leftUpper.row,
            windBlowInfo.leftUpper.col,
            windBlowInfo.rightLower.col);

        remain = moveDownReturningRemain(remain, windBlowInfo.rightLower.col,
            windBlowInfo.leftUpper.row + 1,
            windBlowInfo.rightLower.row);

        remain = moveLeftReturningRemain(remain, windBlowInfo.rightLower.row,
            windBlowInfo.rightLower.col - 1,
            windBlowInfo.leftUpper.col);

        moveUp(remain, windBlowInfo.leftUpper.col, windBlowInfo.rightLower.row - 1,
            windBlowInfo.leftUpper.row);
    }

    private void moveUp(int pre, int col, int start, int end) {
        for (int i = end; i <= start - 1; i++) {
            grid[i][col] = grid[i + 1][col];
        }
        grid[start][col] = pre;
    }

    private int moveLeftReturningRemain(int pre, int row, int start, int end) {
        int remain = grid[row][end];
        for (int i = end; i <= start - 1; i++) {
            grid[row][i] = grid[row][i + 1];
        }
        grid[row][start] = pre;
        return remain;
    }


    private int moveDownReturningRemain(int pre, int col, int start, int end) {
        int remain = grid[end][col];
        for (int i = end; i >= start + 1; i--) {
            grid[i][col] = grid[i - 1][col];
        }
        grid[start][col] = pre;
        return remain;
    }

    private int moveRightReturningRemain(int row, int start, int end) {
        int remain = grid[row][end];
        for (int i = end; i >= start + 1; i--) {
            grid[row][i] = grid[row][i - 1];
        }
        return remain;
    }
}

class WindBlowInfo {

    Coordinate leftUpper;
    Coordinate rightLower;

    public WindBlowInfo(
        Coordinate leftUpper,
        Coordinate rightLower
    ) {
        this.leftUpper = leftUpper;
        this.rightLower = rightLower;
    }
}

class Coordinate {

    int row;
    int col;

    public Coordinate(
        int r,
        int c
    ) {
        this.row = r;
        this.col = c;
    }
}
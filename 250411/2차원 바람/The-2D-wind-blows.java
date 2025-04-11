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
        Coordinate leftUpper = windBlowInfo.leftUpper;
        Coordinate rightLower = windBlowInfo.rightLower;

        int width = rightLower.col - leftUpper.col + 1;
        int height = rightLower.row - leftUpper.row + 1;

        int temp = grid[leftUpper.row][leftUpper.col];

        int curRow = leftUpper.row;
        int curCol = leftUpper.col;
        for (int i = 0; i < height - 1; i++) {
            grid[curRow][curCol] = grid[curRow + 1][curCol];
            curRow++;
        }

        for (int i = 0; i < width - 1; i++) {
            grid[curRow][curCol] = grid[curRow][curCol + 1];
            curCol++;
        }

        for (int i = 0; i < height - 1; i++) {
            grid[curRow][curCol] = grid[curRow - 1][curCol];
            curRow--;
        }

        for (int i = 0; i < width - 2; i++) {
            grid[curRow][curCol] = grid[curRow][curCol - 1];
            curCol--;
        }

        grid[curRow][curCol] = temp;
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
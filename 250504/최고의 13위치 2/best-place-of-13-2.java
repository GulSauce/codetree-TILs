import java.util.Scanner;

class Solver {
    int[][] grid;
    int gridIndex;

    int answer = 0;

    public Solver(
            int[][] grid
    ) {
        this.grid = grid;
        this.gridIndex = grid.length - 1;
    }

    public void solve() {
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                if (isOutOfRange(col + 2)) {
                    continue;
                }
                setSecondGridWithChangeAnswer(new Coordinate(row, col));
            }
        }
        System.out.println(answer);
    }

    private void setSecondGridWithChangeAnswer(Coordinate firstGrid) {
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                if (isOutOfRange(col + 2)) {
                    continue;
                }
                Coordinate secondGrid = new Coordinate(row, col);
                if (isCollide(firstGrid, secondGrid)) {
                    continue;
                }
                answer = Math.max(answer, getCoinSum(firstGrid) + getCoinSum(secondGrid));
            }
        }
    }

    private boolean isCollide(Coordinate first, Coordinate second) {
        if (first.row != second.row) {
            return false;
        }
        int firstEndCol = first.col + 2;
        int secondEndCol = second.col + 2;
        if (firstEndCol < second.col) {
            return false;
        }
        if (secondEndCol < first.col) {
            return false;
        }
        return true;
    }

    private int getCoinSum(Coordinate coordinate) {
        int row = coordinate.row;
        int col = coordinate.col;
        return grid[row][col] + grid[row][col + 1] + grid[row][col + 2];
    }

    private boolean isOutOfRange(int col) {
        return gridIndex < col;
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

        new Solver(grid).solve();
    }
}
import java.util.Scanner;

class Solver {
    int[][] grid;
    int gridIndex;

    public Solver(
            int[][] grid
    ) {
        this.grid = grid;
        this.gridIndex = grid.length - 1;
    }

    public void solve() {
        int answer = 0;
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                if (isOutOfRange(col + 2)) {
                    continue;
                }
                int coinSum = getCoinSum(row, col);
                answer = Math.max(answer, coinSum);
            }
        }
        System.out.println(answer);
    }

    private int getCoinSum(int row, int col) {
        return grid[row][col] + grid[row][col + 1] + grid[row][col + 2];
    }

    private boolean isOutOfRange(int col) {
        return gridIndex < col;
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
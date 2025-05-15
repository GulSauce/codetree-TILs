import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, K;
        int[][] grid;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        grid = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                grid[row][col] = sc.nextInt();
            }
        }
        sc.close();

        new Solver(N, K, grid).solve();
    }
}


class Solver {

    int gridLength;
    int subGridSize;

    int[][] prefixSum;
    int[][] grid;

    public Solver(
        int gridLength,
        int subGridSize,
        int[][] grid
    ) {
        this.prefixSum = new int[gridLength + 1][gridLength + 1];
        this.subGridSize = subGridSize;
        this.gridLength = gridLength;
        this.grid = grid;
    }

    public void solve() {
        init();
        int answer = 0;
        for (int row = subGridSize; row < grid.length; row++) {
            for (int col = subGridSize; col < grid.length; col++) {
                answer = Math.max(answer, getSum(row, col));
            }
        }
        System.out.println(answer);
    }

    private int getSum(int row, int col) {
        return
            prefixSum[row][col] - prefixSum[row - subGridSize][col] - prefixSum[row][col
                - subGridSize]
                + prefixSum[row - subGridSize][col - subGridSize];
    }

    private void init() {
        for (int row = 1; row < prefixSum.length; row++) {
            for (int col = 1; col < prefixSum.length; col++) {
                prefixSum[row][col] =
                    prefixSum[row - 1][col] + prefixSum[row][col - 1] - prefixSum[row - 1][col - 1]
                        + grid[row][col];
            }
        }
    }
}
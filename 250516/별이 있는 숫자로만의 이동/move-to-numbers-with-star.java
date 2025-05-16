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

        new Solver(K, grid).solve();
    }
}

class Solver {

    int maxMove;

    int[][] prefixSum;
    int[][] grid;

    public Solver(
        int K,
        int[][] grid
    ) {
        this.prefixSum = new int[2 * (grid.length - 1)][2 * (grid.length - 1)];
        this.maxMove = K;
        this.grid = grid;
    }

    public void solve() {
        rotateGridClockwise45();
        setPrefixSum();
        int answer = 0;
        int squareLength = Math.min(2 * maxMove + 1, grid.length - 1);
        int maxSquarePrefix = squareLength - 1;
        for (int row = 1; row < grid.length - maxSquarePrefix; row++) {
            for (int col = 1; col < grid.length - maxSquarePrefix; col++) {
                int curArea =
                    prefixSum[row + maxSquarePrefix][col + maxSquarePrefix] - prefixSum[row - 1][col
                        + maxSquarePrefix]
                        - prefixSum[row + maxSquarePrefix][col - 1] + prefixSum[row - 1][col - 1];
                answer = Math.max(answer, curArea);
            }
        }
        System.out.println(answer);
    }

    private void rotateGridClockwise45() {
        int[][] rotatedGrid = new int[2 * (grid.length - 1)][2 * (grid.length - 1)];
        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid.length; col++) {
                rotatedGrid[row + col - 1][(grid.length - 1) - (row - col)] = grid[row][col];
            }
        }
        this.grid = rotatedGrid;
    }

    private void setPrefixSum() {
        for (int row = 1; row < prefixSum.length; row++) {
            for (int col = 1; col < prefixSum.length; col++) {
                prefixSum[row][col] =
                    prefixSum[row - 1][col] + prefixSum[row][col - 1] - prefixSum[row - 1][col - 1]
                        + grid[row][col];
            }
        }
    }
}
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
        this.prefixSum = new int[grid.length][grid.length];
        this.maxMove = K;
        this.grid = grid;
    }

    public void solve() {
        setPrefixSum();
        int answer = 0;
        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid.length; col++) {
                int curArea = 0;
                for (int upperMove = 0; upperMove <= maxMove; upperMove++) {
                    int curRow = row - upperMove;
                    if (curRow < 1) {
                        break;
                    }
                    int maxLeft = Math.max(1, col - maxMove + upperMove);
                    int maxRight = Math.min(grid.length - 1, col + maxMove - upperMove);
                    curArea +=
                        prefixSum[curRow][maxRight] - prefixSum[curRow][maxLeft - 1] -
                            prefixSum[curRow - 1][maxRight] + prefixSum[curRow - 1][maxLeft - 1];
                }
                for (int belowMove = 1; belowMove <= maxMove; belowMove++) {
                    int curRow = row + belowMove;
                    if (grid.length <= curRow) {
                        break;
                    }
                    int maxLeft = Math.max(1, col - maxMove + belowMove);
                    int maxRight = Math.min(grid.length - 1, col + maxMove - belowMove);
                    curArea +=
                        prefixSum[curRow][maxRight] - prefixSum[curRow][maxLeft - 1] -
                            prefixSum[curRow - 1][maxRight] + prefixSum[curRow - 1][maxLeft - 1];
                }
                answer = Math.max(answer, curArea);
            }
        }
        System.out.println(answer);
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
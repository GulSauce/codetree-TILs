import java.util.Arrays;
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

        new Solver(grid).solve();
    }
}

class Solver {

    int[][] prefixSum;
    int[] dp;
    int[][] grid;

    public Solver(
        int[][] grid
    ) {
        this.dp = new int[grid.length];
        this.prefixSum = new int[grid.length][grid.length];
        this.grid = grid;
    }

    public void solve() {
        init();
        int answer = Integer.MIN_VALUE;
        for (int startRow = 1; startRow < grid.length; startRow++) {
            for (int endRow = startRow; endRow < grid.length; endRow++) {
                answer = Math.max(answer, getMaxArea(startRow, endRow));
            }
        }
        System.out.println(answer);
    }

    private int getMaxArea(int startRow, int endRow) {
        Arrays.fill(dp, Integer.MIN_VALUE);
        for (int col = 1; col < grid.length; col++) {
            int curColSum =
                prefixSum[endRow][col] - prefixSum[startRow - 1][col] - prefixSum[endRow][col - 1]
                    + prefixSum[startRow - 1][col - 1];
            dp[col] = Math.max(dp[col - 1] + curColSum, curColSum);
        }
        int answer = Integer.MIN_VALUE;
        for (int col = 1; col < grid.length; col++) {
            answer = Math.max(answer, dp[col]);
        }
        return answer;
    }

    private void init() {
        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid.length; col++) {
                prefixSum[row][col] =
                    prefixSum[row - 1][col] + prefixSum[row][col - 1] - prefixSum[row - 1][col - 1]
                        + grid[row][col];
            }
        }
    }
}
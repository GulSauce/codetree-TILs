import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        int[][] grid;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        grid = new int[N + 1][N + 1];
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                grid[y][x] = sc.nextInt();
            }
        }
        sc.close();

        new Solver(N, grid).solve();
    }
}

class Solver {

    int gridIndex;
    final int NOT_ALLOCATED = Integer.MIN_VALUE;
    int[][] grid;
    int[][] dp;

    public Solver(
        int N,
        int[][] grid
    ) {
        this.gridIndex = N;
        this.grid = grid;
        this.dp = new int[N + 1][N + 1];
    }

    public void solve() {
        initDP();
        calcDP();
        printDP();
    }

    private void printDP() {
        System.out.println(dp[gridIndex][gridIndex]);
    }

    private void calcDP() {
        for (int y = 1; y <= gridIndex; y++) {
            for (int x = 1; x <= gridIndex; x++) {
                if (y < gridIndex) {
                    int nextMin = Math.min(grid[y + 1][x], dp[y][x]);
                    dp[y + 1][x] = Math.max(dp[y + 1][x], nextMin);
                }
                if (x < gridIndex) {
                    int nextMin = Math.min(grid[y][x + 1], dp[y][x]);
                    dp[y][x + 1] = Math.max(dp[y][x + 1], nextMin);
                }
            }
        }
    }

    public void initDP() {
        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        dp[1][1] = grid[1][1];
    }
}
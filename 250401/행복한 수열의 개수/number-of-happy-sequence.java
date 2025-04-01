import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M;
        int[][] grid;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        grid = new int[N + 1][N + 1];
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                grid[y][x] = sc.nextInt();
            }
        }
        sc.close();

        new Solver(N, M, grid).solve();
    }
}

class Solver {

    int gridIndex;
    int consecutiveCountToHappy;
    int[][] grid;

    public Solver(
        int N,
        int M,
        int[][] grid
    ) {
        this.gridIndex = N;
        this.consecutiveCountToHappy = M;
        this.grid = grid;
    }

    public void solve() {
        int answer = 0;
        for (int col = 1; col <= gridIndex; col++) {
            if (isHappyOnColumnAt(col)) {
                answer++;
            }
        }
        for (int row = 1; row <= gridIndex; row++) {
            if (isHappyOnRowAt(row)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private boolean isHappyOnColumnAt(int col) {
        int maxConsecutiveCount = 1;
        int consecutiveCount = 1;
        for (int row = 2; row <= gridIndex; row++) {
            if (grid[row - 1][col] == grid[row][col]) {
                consecutiveCount++;
            } else {
                consecutiveCount = 1;
            }
            maxConsecutiveCount = Math.max(maxConsecutiveCount, consecutiveCount);
        }
        if (consecutiveCountToHappy <= maxConsecutiveCount) {
            return true;
        }
        return false;
    }

    private boolean isHappyOnRowAt(int row) {
        int maxConsecutiveCount = 1;
        int consecutiveCount = 1;
        for (int col = 2; col <= gridIndex; col++) {
            if (grid[row][col - 1] == grid[row][col]) {
                consecutiveCount++;
            } else {
                consecutiveCount = 1;
            }
            maxConsecutiveCount = Math.max(maxConsecutiveCount, consecutiveCount);
        }
        if (consecutiveCountToHappy <= maxConsecutiveCount) {
            return true;
        }
        return false;
    }

}
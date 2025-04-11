import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M, K;
        int[][] grid;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        grid = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                grid[row][col] = sc.nextInt();
            }
        }
        sc.close();

        new Solver(N, M, K, grid).solve();
    }
}

class Solver {

    int consecutiveCount;
    int rotateCount;
    int gridIndex;
    final int EMPTY = -1;
    int[][] grid;


    public Solver(
        int N,
        int M,
        int K,
        int[][] grid
    ) {
        this.gridIndex = N;
        this.consecutiveCount = M;
        this.rotateCount = K;
        this.grid = grid;
    }

    public void solve() {
        for (int i = 0; i < rotateCount; i++) {
            while (explode()) {
                applyGravity();
            }
            rotateClockWise90();
            applyGravity();
        }
        while (explode()) {
            applyGravity();
        }
        printAnswer();
    }

    private void printAnswer() {
        int count = 0;
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                if (grid[row][col] == EMPTY) {
                    continue;
                }
                count++;
            }
        }
        System.out.println(count);
    }


    private void rotateClockWise90() {
        int[][] temp = new int[gridIndex + 1][gridIndex + 1];
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                temp[col][gridIndex - row + 1] = grid[row][col];
            }
        }
        grid = temp;
    }

    private boolean explode() {
        boolean explode = false;
        for (int col = 1; col <= gridIndex; col++) {
            int curConsecutive = 1;
            int endRow = gridIndex - 1;
            for (int row = gridIndex - 1; row >= 1; row--) {
                if (grid[row][col] == EMPTY) {
                    break;
                }
                if (grid[row][col] == grid[row + 1][col]) {
                    curConsecutive++;
                } else {
                    if (consecutiveCount <= curConsecutive) {
                        explode = true;
                        removeFrom(col, row + 1, curConsecutive);
                    }
                    curConsecutive = 1;
                }
                endRow = row;
            }
            if (consecutiveCount <= curConsecutive) {
                explode = true;
                removeFrom(col, endRow, curConsecutive);
            }
        }
        return explode;
    }

    private void applyGravity() {
        for (int col = 1; col <= gridIndex; col++) {
            int tempIndex = 0;
            int[] temp = new int[gridIndex + 1];
            Arrays.fill(temp, EMPTY);
            for (int row = gridIndex; row >= 1; row--) {
                if (grid[row][col] == EMPTY) {
                    continue;
                }
                temp[tempIndex] = grid[row][col];
                tempIndex++;
            }
            for (int row = gridIndex; row >= 1; row--) {
                grid[row][col] = temp[gridIndex - row];
            }
        }
    }

    private void removeFrom(int col, int start, int count) {
        for (int i = 0; i < count; i++) {
            grid[start][col] = EMPTY;
            start++;
        }
    }
}
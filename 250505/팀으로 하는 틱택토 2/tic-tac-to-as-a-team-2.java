import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] grid = new int[4][4];

        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= 3; i++) {
            String numbers = sc.next();
            for (int j = 0; j <= 2; j++) {
                grid[i][j + 1] = numbers.charAt(j) - '0';
            }
        }
        sc.close();

        new Solver(grid).solve();
    }
}

class Solver {
    int[][] grid;

    public Solver(
            int[][] grid
    ) {
        this.grid = grid;
    }

    public void solve() {
        int answer = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = i + 1; j <= 9; j++) {
                if (isWin(i, j)) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private boolean isWin(int first, int second) {
        for (int row = 1; row <= 3; row++) {
            int firstCount = 0;
            int secondCount = 0;
            for (int col = 1; col <= 3; col++) {
                if (grid[row][col] == first) {
                    firstCount++;
                }
                if (grid[row][col] == second) {
                    secondCount++;
                }
            }
            if (firstCount == 0) {
                continue;
            }
            if (secondCount == 0) {
                continue;
            }
            if (firstCount + secondCount < 3) {
                continue;
            }
            return true;
        }

        for (int col = 1; col <= 3; col++) {
            int firstCount = 0;
            int secondCount = 0;
            for (int row = 1; row <= 3; row++) {
                if (grid[row][col] == first) {
                    firstCount++;
                }
                if (grid[row][col] == second) {
                    secondCount++;
                }
            }
            if (firstCount == 0) {
                continue;
            }
            if (secondCount == 0) {
                continue;
            }
            if (firstCount + secondCount < 3) {
                continue;
            }
            return true;
        }

        int firstCount = 0;
        int secondCount = 0;
        for (int d = 1; d <= 3; d++) {
            if (grid[d][d] == first) {
                firstCount++;
            }
            if (grid[d][d] == second) {
                secondCount++;
            }
        }
        if (firstCount != 0 && secondCount != 0 && firstCount + secondCount == 3) {
            return true;
        }

        firstCount = 0;
        secondCount = 0;
        for (int d = 0; d <= 2; d++) {

            if (grid[3 - d][d] == first) {
                firstCount++;
            }
            if (grid[3 - d][d] == second) {
                secondCount++;
            }
        }
        if (firstCount != 0 && secondCount != 0 && firstCount + secondCount == 3) {
            return true;
        }

        return false;
    }
}


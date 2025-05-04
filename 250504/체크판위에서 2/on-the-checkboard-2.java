import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int R;
        int C;
        char[][] grid;

        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        grid = new char[R + 1][C + 1];
        for (int row = 1; row <= R; row++) {
            for (int col = 1; col <= C; col++) {
                grid[row][col] = sc.next().charAt(0);
            }
        }
        sc.close();

        new Solver(grid).solve();
    }
}

class Solver {
    char[][] grid;
    int gridRow;
    int gridCol;

    public Solver(
            char[][] grid
    ) {
        this.grid = grid;
        this.gridRow = grid.length - 1;
        this.gridCol = grid[0].length - 1;
    }

    public void solve() {
        int answer = 0;

        for (int row1 = 2; row1 <= gridRow - 1; row1++) {
            for (int col1 = 2; col1 <= gridCol - 1; col1++) {
                for (int row2 = row1 + 1; row2 <= gridRow - 1; row2++) {
                    for (int col2 = col1 + 1; col2 <= gridCol - 1; col2++) {
                        if (isJumpable(row1, col1, row2, col2)) {
                            answer++;
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private boolean isJumpable(int row1, int col1, int row2, int col2) {
        char startChar = grid[1][1];
        int colorAfterFirstJump = grid[row1][col1];
        int colorAfterSecondJump = grid[row2][col2];
        char endChar = grid[gridRow][gridCol];
        if (startChar == colorAfterFirstJump) {
            return false;
        }
        if (colorAfterFirstJump == colorAfterSecondJump) {
            return false;
        }
        if (colorAfterSecondJump == endChar) {
            return false;
        }
        return true;
    }
}
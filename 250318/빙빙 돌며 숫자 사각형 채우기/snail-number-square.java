import java.util.Scanner;

public class Main {

    private static class Solver {

        int gridRow;
        int gridCol;

        int[] dc = {1, 0, -1, 0};
        int[] dr = {0, 1, 0, -1};

        int[][] grid;

        public Solver(
            int N,
            int M
        ) {
            this.gridRow = N;
            this.gridCol = M;
            this.grid = new int[N + 1][M + 1];
        }

        public void solve() {
            memoNumber();
            printAnswer();
        }

        private void printAnswer() {
            for (int row = 1; row <= gridRow; row++) {
                for (int col = 1; col <= gridCol; col++) {
                    System.out.printf("%d ", grid[row][col]);
                }
                System.out.println();
            }
        }

        private void memoNumber() {
            int prevRow = 1;
            int prevCol = 1;
            int prevNumber = 1;
            int directionIndex = 0;
            grid[prevRow][prevCol] = prevNumber;
            while (true) {
                int currentRow = prevRow + dr[directionIndex];
                int currentCol = prevCol + dc[directionIndex];
                if (isOutOfGrid(currentRow, currentCol) || grid[currentRow][currentCol] != 0) {
                    directionIndex = (directionIndex + 1) % 4;
                    currentRow = prevRow + dr[directionIndex];
                    currentCol = prevCol + dc[directionIndex];
                    if (isOutOfGrid(currentRow, currentCol) || grid[currentRow][currentCol] != 0) {
                        break;
                    }
                }
                grid[currentRow][currentCol] = ++prevNumber;
                prevRow = currentRow;
                prevCol = currentCol;
            }
        }

        private boolean isOutOfGrid(int row, int col) {
            return 1 > row || row > gridRow || 1 > col || col > gridCol;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        new Main.Solver(N, M).solve();
    }
}
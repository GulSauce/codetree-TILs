import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M, r, c;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        sc.close();

        new Solver(N, M, new Coordinate(r, c)).solve();
    }
}

class Solver {

    int gridIndex;
    int targetTime;
    final int[] dr = {1, 0, -1, 0};
    final int[] dc = {0, 1, 0, -1};

    final boolean BOMB_NOT_EXIST = false;
    final boolean BOMB_EXIST = true;

    boolean[][] grid;
    boolean[][] copiedGrid;
    Coordinate first;

    public Solver(
        int N,
        int M,
        Coordinate first
    ) {
        this.gridIndex = N;
        this.targetTime = M;
        this.grid = new boolean[N + 1][N + 1];
        this.copiedGrid = new boolean[N + 1][N + 1];
        this.first = first;
    }

    public void solve() {
        grid[first.row][first.col] = BOMB_EXIST;
        for (int t = 1; t <= targetTime; t++) {
            setCopiedGrid();
            for (int row = 1; row <= gridIndex; row++) {
                for (int col = 1; col <= gridIndex; col++) {
                    if (copiedGrid[row][col] == BOMB_NOT_EXIST) {
                        continue;
                    }
                    int prefix = intPow(2, t - 1);
                    for (int i = 0; i < 4; i++) {
                        Coordinate next = new Coordinate(row + prefix * dr[i],
                            col + prefix * dc[i]);
                        if (isOufOfGrid(next)) {
                            continue;
                        }
                        grid[next.row][next.col] = BOMB_EXIST;
                    }
                }
            }
        }
        printAnswer();
    }

    private void setCopiedGrid() {
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                copiedGrid[row][col] = grid[row][col];
            }
        }
    }

    private void printAnswer() {
        int answer = 0;
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                if (grid[row][col] == BOMB_EXIST) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }


    private boolean isOufOfGrid(Coordinate coordinate) {
        return coordinate.row < 1 || gridIndex < coordinate.row || coordinate.col < 1
            || gridIndex < coordinate.col;
    }

    private int intPow(int number, int p) {
        int res = 1;
        for (int i = 0; i < p; i++) {
            res *= number;
        }
        return res;
    }

}

class Coordinate {

    int row;
    int col;

    public Coordinate(
        int r,
        int c
    ) {
        this.row = r;
        this.col = c;
    }
}
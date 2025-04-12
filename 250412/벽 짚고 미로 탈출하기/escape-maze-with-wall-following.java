import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        int x, y;

        char[][] grid;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        x = sc.nextInt();
        y = sc.nextInt();
        grid = new char[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            String colString = sc.next();
            for (int col = 1; col <= N; col++) {
                grid[row][col] = colString.charAt(col - 1);
            }
        }
        sc.close();

        new Solver(N, grid, new Coordinate(x, y)).solve();
    }
}

class Solver {

    int gridIndex;
    boolean[][][] isVisited;

    final char WALL = '#';
    final char NO_WALL = '.';
    char[][] grid;

    Escaper escaper;

    public Solver(
        int N,
        char[][] grid,
        Coordinate start
    ) {
        this.gridIndex = N;
        this.grid = grid;
        this.escaper = new Escaper(start);
        this.isVisited = new boolean[N + 1][N + 1][4];
    }

    public void solve() {
        int elapsedTime = 0;
        while (true) {
            if (isVisited[escaper.cur.row][escaper.cur.col][escaper.dir]) {
                elapsedTime = -1;
                break;
            }

            isVisited[escaper.cur.row][escaper.cur.col][escaper.dir] = true;
            Coordinate next = escaper.getNext();
            if (isOutOfGrid(next)) {
                escaper.move();
                elapsedTime++;
                break;
            }
            if (isWallAt(next)) {
                escaper.rotateCounterClockwise();
                continue;
            }
            if (isWallAtRightSideOfEscaperNextMove()) {
                escaper.move();
                elapsedTime++;
                continue;
            }
            escaper.move();
            elapsedTime++;
            escaper.rotateClockwise();
            escaper.move();
            elapsedTime++;
        }
        System.out.println(elapsedTime);
    }

    private boolean isWallAtRightSideOfEscaperNextMove() {
        Coordinate nextRightSide = escaper.getNextRightSide();
        return grid[nextRightSide.row][nextRightSide.col] == WALL;
    }

    private boolean isOutOfGrid(Coordinate coordinate) {
        return coordinate.row < 1 || gridIndex < coordinate.row
            || coordinate.col < 1 || gridIndex < coordinate.col;
    }

    private boolean isWallAt(Coordinate coordinate) {
        return grid[coordinate.row][coordinate.col] == WALL;
    }
}

class Escaper {

    Coordinate cur;
    int dir = 0;
    private final int[] dr = {0, -1, 0, 1};
    private final int[] dc = {1, 0, -1, 0};

    public Escaper(
        Coordinate coordinate
    ) {
        this.cur = coordinate;
    }

    public Coordinate getNextRightSide() {
        Coordinate next = new Coordinate(cur.row + dr[dir], cur.col + dc[dir]);
        int rightDir = Math.floorMod(this.dir - 1, 4);
        return new Coordinate(next.row + dr[rightDir], next.col + dc[rightDir]);
    }

    public Coordinate getNext() {
        return new Coordinate(cur.row + dr[dir], cur.col + dc[dir]);
    }

    public void move() {
        this.cur = getNext();
    }

    public void rotateCounterClockwise() {
        this.dir = (this.dir + 1) % 4;
    }

    public void rotateClockwise() {
        this.dir = Math.floorMod(this.dir - 1, 4);
    }
}

class Coordinate {

    int row;
    int col;

    public Coordinate(
        int x,
        int y
    ) {
        this.row = x;
        this.col = y;
    }
}
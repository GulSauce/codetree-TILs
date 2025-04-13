import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M, K;
        boolean[][] grid;
        List<SnakeMove> snakeMoves = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        grid = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            grid[x][y] = true;
        }
        for (int i = 0; i < K; i++) {
            String d = sc.next();
            int p = sc.nextInt();
            snakeMoves.add(new SnakeMove(d, p));
        }
        sc.close();

        new Solver(N, grid, snakeMoves).solve();
    }
}

class Solver {

    int gridIndex;
    final boolean EMPTY = false;
    final boolean APPLE = true;

    boolean[][] grid;
    List<SnakeMove> snakeMoves;

    Snake snake;

    public Solver(
        int N,
        boolean[][] grid,
        List<SnakeMove> snakeMoves
    ) {
        this.gridIndex = N;
        this.grid = grid;
        this.snakeMoves = snakeMoves;
        this.snake = new Snake(new Coordinate(1, 1));
    }

    public void solve() {
        int elapsedTime = 0;
        snakeMoveLoop:
        for (SnakeMove snakeMove : snakeMoves) {
            MoveDir moveDir = snakeMove.moveDir;
            for (int i = 0; i < snakeMove.moveCount; i++) {
                elapsedTime++;
                Coordinate nextHead = snake.getNextHead(moveDir);
                if (isOutOfGrid(nextHead)) {
                    break snakeMoveLoop;
                }

                if (grid[nextHead.row][nextHead.col] == APPLE) {
                    boolean isMoveSuccess = snake.moveWithNotRemovingTail(moveDir);
                    if (!isMoveSuccess) {
                        break snakeMoveLoop;
                    }
                    grid[nextHead.row][nextHead.col] = EMPTY;
                    continue;
                }

                boolean isMoveSuccess = snake.moveWithRemovingTail(moveDir);
                if (!isMoveSuccess) {
                    break snakeMoveLoop;
                }
            }
        }
        System.out.println(elapsedTime);
    }

    private boolean isOutOfGrid(Coordinate coordinate) {
        return coordinate.row < 1 || gridIndex < coordinate.row || coordinate.col < 1
            || gridIndex < coordinate.col;
    }
}

class Snake {

    int[] dr = {0, -1, 0, 1};
    int[] dc = {1, 0, -1, 0};
    private final Deque<Coordinate> positions = new ArrayDeque<>();


    public Snake(
        Coordinate start
    ) {
        this.positions.add(start);
    }

    public Coordinate getNextHead(MoveDir moveDir) {
        int dirIndex = getDirIndex(moveDir);
        Coordinate head = positions.getFirst();
        return new Coordinate(head.row + dr[dirIndex],
            head.col + dc[dirIndex]);
    }

    private int getDirIndex(MoveDir moveDir) {
        if (moveDir == MoveDir.R) {
            return 0;
        }
        if (moveDir == MoveDir.U) {
            return 1;
        }
        if (moveDir == MoveDir.L) {
            return 2;
        }
        if (moveDir == MoveDir.D) {
            return 3;
        }
        throw new IllegalArgumentException();
    }

    public boolean moveWithNotRemovingTail(MoveDir moveDir) {
        Coordinate next = getNextHead(moveDir);
        if (isCollideWith(next)) {
            return false;
        }
        positions.addFirst(next);
        return true;
    }

    public boolean moveWithRemovingTail(MoveDir moveDir) {
        Coordinate next = getNextHead(moveDir);
        positions.pollLast();
        if (isCollideWith(next)) {
            return false;
        }
        positions.addFirst(next);
        return true;
    }

    private boolean isCollideWith(Coordinate next) {
        for (Coordinate coordinate : positions) {
            if (coordinate.isEqual(next)) {
                return true;
            }
        }
        return false;
    }
}

class Coordinate {

    int row;
    int col;

    public Coordinate(
        int row,
        int col
    ) {
        this.row = row;
        this.col = col;
    }


    public boolean isEqual(Coordinate coordinate) {
        if (this.row != coordinate.row) {
            return false;
        }
        if (this.col != coordinate.col) {
            return false;
        }
        return true;
    }
}

class SnakeMove {

    MoveDir moveDir;
    int moveCount;

    public SnakeMove(
        String d,
        int p
    ) {
        this.moveDir = MoveDir.valueOf(d);
        this.moveCount = p;
    }


}

enum MoveDir {
    U, D, R, L
}
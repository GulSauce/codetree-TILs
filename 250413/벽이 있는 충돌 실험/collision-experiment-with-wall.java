import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int T;
        int N, M;
        List<BidInfo> bidInfos = new ArrayList<>();
        int x;
        int y;
        String d;
        Solver solver = new Solver();

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            N = sc.nextInt();
            M = sc.nextInt();
            bidInfos.clear();
            bidInfos.add(new BidInfo(-1, -1, "U"));
            for (int j = 0; j < M; j++) {
                x = sc.nextInt();
                y = sc.nextInt();
                d = sc.next();
                bidInfos.add(new BidInfo(x, y, d));
            }
            int[][] grid = new int[N + 1][N + 1];
            for (int j = 1; j < bidInfos.size(); j++) {
                BidInfo bidInfo = bidInfos.get(j);
                grid[bidInfo.pos.row][bidInfo.pos.col] = j;
            }
            solver.solve(N, grid, bidInfos);
        }
    }
}

class Solver {

    int gridIndex;
    final int EMPTY = 0;
    int[][] grid;
    boolean[][] exist;
    List<BidInfo> bidInfos;

    public void solve(int N, int[][] grid, List<BidInfo> bidInfos) {
        gridIndex = N;
        this.grid = grid;
        this.exist = new boolean[N + 1][N + 1];
        this.bidInfos = bidInfos;

        int maxTime = gridIndex * 2;
        for (int t = 0; t < maxTime; t++) {
            moveBids();
        }
        printAnswer();
    }


    private void printAnswer() {
        int answer = 0;
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                if (grid[row][col] == EMPTY) {
                    continue;
                }
                answer++;
            }
        }
        System.out.println(answer);
    }


    private void moveBids() {
        initExist();
        int[][] nextGrid = new int[gridIndex + 1][gridIndex + 1];
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                if (grid[row][col] == EMPTY) {
                    continue;
                }
                int i = grid[row][col];
                BidInfo bidInfo = bidInfos.get(i);

                Coordinate nextPos = bidInfo.getNextPos();

                if (isOutOfGrid(nextPos)) {
                    bidInfo.reverseDirection();
                } else {
                    bidInfo.move();
                }

                Coordinate curPos = bidInfo.getCurPos();
                if (exist[curPos.row][curPos.col]) {
                    nextGrid[curPos.row][curPos.col] = EMPTY;
                    continue;
                }
                nextGrid[curPos.row][curPos.col] = i;
                exist[curPos.row][curPos.col] = true;
            }
        }
        grid = nextGrid;
    }

    private boolean isOutOfGrid(Coordinate coordinate) {
        return coordinate.row < 1 || gridIndex < coordinate.row || coordinate.col < 1
            || gridIndex < coordinate.col;
    }

    private void initExist() {
        for (boolean[] array : exist) {
            Arrays.fill(array, false);
        }
    }

}

class BidInfo {

    Coordinate pos;
    Direction direction;
    Map<Direction, Integer> directionIndex = new HashMap<>();
    int[] dr = {0, -1, 0, 1};
    int[] dc = {1, 0, -1, 0};

    public BidInfo(
        int x,
        int y,
        String direcitonString
    ) {
        this.pos = new Coordinate(x, y);
        this.direction = Direction.valueOf(direcitonString);
        setDirectionIndex();
    }

    private void setDirectionIndex() {
        directionIndex.put(Direction.R, 0);
        directionIndex.put(Direction.U, 1);
        directionIndex.put(Direction.L, 2);
        directionIndex.put(Direction.D, 3);
    }

    public void reverseDirection() {
        if (direction == Direction.R) {
            direction = Direction.L;
            return;
        }
        if (direction == Direction.U) {
            direction = Direction.D;
            return;
        }
        if (direction == Direction.L) {
            direction = Direction.R;
            return;
        }
        if (direction == Direction.D) {
            direction = Direction.U;
            return;
        }
    }

    public void move() {
        int dirIndex = directionIndex.get(direction);
        pos = new Coordinate(pos.row + dr[dirIndex], pos.col + dc[dirIndex]);
    }

    public Coordinate getCurPos() {
        return pos;
    }

    public Coordinate getNextPos() {
        int dirIndex = directionIndex.get(direction);
        return new Coordinate(pos.row + dr[dirIndex], pos.col + dc[dirIndex]);
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

enum Direction {
    U, D, R, L
}
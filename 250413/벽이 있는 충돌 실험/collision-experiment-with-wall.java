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
    int[][] nextGrid;
    int[][] grid;

    boolean[][] exist;

    List<BidInfo> bidInfos;

    public void solve(int N, int[][] grid, List<BidInfo> bidInfos) {
        gridIndex = N;
        this.grid = grid;
        this.exist = new boolean[N + 1][N + 1];
        this.bidInfos = bidInfos;
        this.nextGrid = new int[N + 1][N + 1];

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
        initNextGrid();
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
        applyNextGrid();
    }

    private void applyNextGrid() {
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                grid[row][col] = nextGrid[row][col];
            }
        }
    }

    private boolean isOutOfGrid(Coordinate coordinate) {
        return coordinate.row < 1 || gridIndex < coordinate.row || coordinate.col < 1
            || gridIndex < coordinate.col;
    }

    private void initNextGrid() {
        for (int[] array : nextGrid) {
            Arrays.fill(array, EMPTY);
        }
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
    Map<Direction, Integer> directionIndexMapper = new HashMap<>();
    Map<Direction, Direction> directionReverseMapper = new HashMap<>();
    int[] dr = {0, -1, 0, 1};
    int[] dc = {1, 0, -1, 0};

    public BidInfo(
        int x,
        int y,
        String direcitonString
    ) {
        this.pos = new Coordinate(x, y);
        this.direction = Direction.valueOf(direcitonString);
        setDirectionIndexMapper();
        setDirectionReverseMapper();
    }

    private void setDirectionIndexMapper() {
        directionIndexMapper.put(Direction.R, 0);
        directionIndexMapper.put(Direction.U, 1);
        directionIndexMapper.put(Direction.L, 2);
        directionIndexMapper.put(Direction.D, 3);
    }

    private void setDirectionReverseMapper() {
        directionReverseMapper.put(Direction.R, Direction.L);
        directionReverseMapper.put(Direction.L, Direction.R);
        directionReverseMapper.put(Direction.U, Direction.D);
        directionReverseMapper.put(Direction.D, Direction.U);
    }

    public void reverseDirection() {
        direction = directionReverseMapper.get(direction);
    }

    public void move() {
        int dirIndex = directionIndexMapper.get(direction);
        pos = new Coordinate(pos.row + dr[dirIndex], pos.col + dc[dirIndex]);
    }

    public Coordinate getCurPos() {
        return pos;
    }

    public Coordinate getNextPos() {
        int dirIndex = directionIndexMapper.get(direction);
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
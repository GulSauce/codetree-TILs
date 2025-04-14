import java.util.ArrayList;
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
            solver.solve(N, bidInfos);
        }
    }
}

class Solver {

    int gridIndex;
    final int EMPTY = 0;
    int[][] grid = new int[51][51];

    List<BidInfo> bidInfos;

    public void solve(int N, List<BidInfo> bidInfos) {
        this.gridIndex = N;
        this.bidInfos = bidInfos;

        int maxTime = gridIndex * 2;
        for (int t = 0; t < maxTime; t++) {
            moveBids();
        }
        printAnswer();
    }


    private void printAnswer() {
        System.out.println(bidInfos.size() - 1);
    }


    private void moveBids() {
        List<BidInfo> nextBidInfosCandidate = new ArrayList<>();
        nextBidInfosCandidate.add(new BidInfo(-1, -1, "R"));
        for (int i = 1; i < bidInfos.size(); i++) {
            BidInfo bidInfo = bidInfos.get(i);
            Coordinate nextPos = bidInfo.getNextPos();

            if (isOutOfGrid(nextPos)) {
                bidInfo.reverseDirection();
            } else {
                bidInfo.move();
            }

            Coordinate pos = bidInfo.getCurPos();
            if (grid[pos.row][pos.col] == EMPTY) {
                grid[pos.row][pos.col] = nextBidInfosCandidate.size();
                nextBidInfosCandidate.add(bidInfo);
                continue;
            }
            int existIndex = grid[pos.row][pos.col];
            nextBidInfosCandidate.set(existIndex, RemoveMarker.of(bidInfo));
        }

        for (int i = 1; i < nextBidInfosCandidate.size(); i++) {
            BidInfo bidInfo = nextBidInfosCandidate.get(i);
            Coordinate pos = bidInfo.getCurPos();
            grid[pos.row][pos.col] = EMPTY;
        }

        List<BidInfo> nextBidInfos = new ArrayList<>();
        nextBidInfos.add(new BidInfo(-1, -1, "R"));
        for (int i = 1; i < nextBidInfosCandidate.size(); i++) {
            BidInfo bidInfo = nextBidInfosCandidate.get(i);
            if (bidInfo instanceof RemoveMarker) {
                continue;
            }
            nextBidInfos.add(bidInfo);
        }
        bidInfos = nextBidInfos;
    }

    private boolean isOutOfGrid(Coordinate coordinate) {
        return coordinate.row < 1 || gridIndex < coordinate.row || coordinate.col < 1
            || gridIndex < coordinate.col;
    }

}

class RemoveMarker extends BidInfo {

    public RemoveMarker(int x, int y, String direcitonString) {
        super(x, y, direcitonString);
    }

    public static RemoveMarker of(BidInfo bidInfo) {
        return new RemoveMarker(bidInfo.getCurPos().row, bidInfo.getCurPos().col,
            bidInfo.direction.toString());
    }
}

class BidInfo {

    private Coordinate pos;
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
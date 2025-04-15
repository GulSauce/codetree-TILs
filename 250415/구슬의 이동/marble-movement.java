import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M, T, K;
        List<Bid> bids = new ArrayList<>();
        String d;
        int r, c, v;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        T = sc.nextInt();
        K = sc.nextInt();
        for (int i = 1; i <= M; i++) {
            r = sc.nextInt();
            c = sc.nextInt();
            d = sc.next();
            v = sc.nextInt();
            bids.add(new Bid(i, r, c, d, v));
        }

        new Solver(N, T, K, bids).solve();
    }
}

class Solver {

    int girdIndex;
    int targetTime;
    int maxSurviveBidCount;
    final int EMPTY = -1;
    int[][] grid;
    List<Bid> bids;

    public Solver(
        int N,
        int T,
        int K,
        List<Bid> bids
    ) {
        this.girdIndex = N;
        this.targetTime = T;
        this.maxSurviveBidCount = K;
        this.bids = bids;
        this.grid = new int[N + 1][N + 1];
    }

    public void solve() {
        initGrid();
        for (int t = 1; t <= targetTime; t++) {
            moveBids();
        }
        System.out.println(bids.size());
    }

    private void moveBids() {
        List<List<Bid>> nextBidsCandidates = new ArrayList<>();
        for (Bid bid : bids) {
            int velocity = bid.getVelocity();
            for (int i = 0; i < velocity; i++) {
                Coordinate nextPos = bid.getNextPos();
                if (isOutOfGrid(nextPos)) {
                    bid.reverseDirection();
                }
                bid.move();
            }

            Coordinate pos = bid.getPos();
            if (grid[pos.row][pos.col] == EMPTY) {
                List<Bid> nextBidsCandidate = new ArrayList<>();
                nextBidsCandidate.add(bid);
                grid[pos.row][pos.col] = nextBidsCandidates.size();
                nextBidsCandidates.add(nextBidsCandidate);
                continue;
            }
            int existIndex = grid[pos.row][pos.col];
            List<Bid> nextBidsCandidate = nextBidsCandidates.get(existIndex);
            nextBidsCandidate.add(bid);
        }
        List<Bid> nextBids = new ArrayList<>();
        for (List<Bid> nextBidCandidate : nextBidsCandidates) {
            if (nextBidCandidate.size() <= maxSurviveBidCount) {
                nextBids.addAll(nextBidCandidate);
                continue;
            }
            Collections.sort(nextBidCandidate);
            for (int i = 0; i < maxSurviveBidCount; i++) {
                nextBids.add(nextBidCandidate.get(i));
            }
        }
        for (Bid bids : nextBids) {
            Coordinate pos = bids.getPos();
            grid[pos.row][pos.col] = EMPTY;
        }
        bids = nextBids;
    }

    private boolean isOutOfGrid(Coordinate coordinate) {
        return coordinate.row < 1 || girdIndex < coordinate.row || coordinate.col < 1
            || girdIndex < coordinate.col;
    }

    private void initGrid() {
        for (int[] array : grid) {
            Arrays.fill(array, EMPTY);
        }
    }
}

class Bid implements Comparable<Bid> {

    private int number;
    private int velocity;
    private int[] dr = {0, -1, 0, 1};
    private int[] dc = {1, 0, -1, 0};

    private Map<Direction, Integer> directionIndexMapper = new HashMap<>();
    private Map<Direction, Direction> reverseDirectionMapper = new HashMap<>();
    private Coordinate pos;
    private Direction direction;


    public Bid(
        int number,
        int r,
        int c,
        String d,
        int v
    ) {
        this.number = number;
        this.pos = new Coordinate(r, c);
        this.direction = Direction.valueOf(d);
        this.velocity = v;
        init();
    }

    private void init() {
        directionIndexMapper.put(Direction.R, 0);
        directionIndexMapper.put(Direction.U, 1);
        directionIndexMapper.put(Direction.L, 2);
        directionIndexMapper.put(Direction.D, 3);

        reverseDirectionMapper.put(Direction.R, Direction.L);
        reverseDirectionMapper.put(Direction.U, Direction.D);
        reverseDirectionMapper.put(Direction.L, Direction.R);
        reverseDirectionMapper.put(Direction.D, Direction.U);
    }

    public Coordinate getPos() {
        return pos;
    }

    public int getVelocity() {
        return velocity;
    }

    public Coordinate getNextPos() {
        int index = directionIndexMapper.get(direction);
        return new Coordinate(pos.row + dr[index], pos.col + dc[index]);
    }

    public void reverseDirection() {
        direction = reverseDirectionMapper.get(direction);
    }

    public void move() {
        pos = getNextPos();
    }

    @Override
    public int compareTo(Bid other) {
        if (this.velocity == other.velocity) {
            return other.number - this.number;
        }
        return other.velocity - this.velocity;
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

enum Direction {
    U, D, R, L
}
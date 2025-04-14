import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int T;
        int N;
        List<Bid> bids = new ArrayList<>();
        int x, y, w;
        String d;

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            N = sc.nextInt();
            bids.clear();
            bids.add(new Bid(-1, -1, -1, -1, "R"));
            for (int j = 1; j <= N; j++) {
                x = sc.nextInt();
                y = sc.nextInt();
                w = sc.nextInt();
                d = sc.next();
                bids.add(new Bid(j, x, y, w, d));
            }
            new Solver().solve(bids);
        }

    }
}

class Solver {

    final int GRID_INDEX = 4000;
    final int EMPTY = 0;
    int[][] grid = new int[GRID_INDEX + 1][GRID_INDEX + 1];
    ;
    List<Bid> bids;

    public void solve(List<Bid> bids) {
        int answer = -1;
        this.bids = bids;
        initGrid();

        for (int i = 1; i <= GRID_INDEX; i++) {
            boolean collide = moveBids();
            if (collide) {
                answer = i;
            }
        }

        System.out.println(answer);
    }

    private boolean moveBids() {
        boolean collide = false;
        List<Bid> nextBids = new ArrayList<>();
        nextBids.add(new Bid(-1, -1, -1, -1, "R"));

        for (int i = 1; i < bids.size(); i++) {
            Bid bid = bids.get(i);
            bid.move();
            Coordinate pos = bid.getPos();
            if (isOutOfGrid(pos)) {
                continue;
            }

            if (grid[pos.y][pos.x] == EMPTY) {
                grid[pos.y][pos.x] = nextBids.size();
                nextBids.add(bid);
                continue;
            }

            collide = true;
            int existBidIndex = grid[pos.y][pos.x];
            Bid existBid = bids.get(existBidIndex);
            if (existBid.weight < bid.weight) {
                nextBids.set(existBidIndex, bid);
            }
            if (existBid.weight == bid.weight && existBid.number < bid.number) {
                nextBids.set(existBidIndex, bid);
            }
        }

        for (int i = 1; i < nextBids.size(); i++) {
            Bid bid = nextBids.get(i);
            Coordinate pos = bid.pos;
            grid[pos.y][pos.x] = EMPTY;
        }

        bids = nextBids;
        return collide;
    }

    private boolean isOutOfGrid(Coordinate coordinate) {
        return coordinate.x < 0 || GRID_INDEX < coordinate.x || coordinate.y < 0
            || GRID_INDEX < coordinate.y;
    }

    private void initGrid() {
        for (int[] array : grid) {
            Arrays.fill(array, EMPTY);
        }
    }

}

class Bid {

    int number;
    int weight;
    final int GRID_INDEX = 4000;
    final int OFFSET = GRID_INDEX / 2;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    Direction direction;

    Map<Direction, Integer> directionIndexMapper = new HashMap<>();
    Coordinate pos;


    public Bid(
        int number,
        int x,
        int y,
        int weight,
        String directionString
    ) {
        this.number = number;
        this.pos = applyOffset(new Coordinate(x, y));
        this.weight = weight;
        this.direction = Direction.valueOf(directionString);
        initDirectionIndexMapper();
    }

    public Coordinate getPos() {
        return pos;
    }

    public void move() {
        int directionIndex = directionIndexMapper.get(direction);
        pos = new Coordinate(pos.x + dx[directionIndex],
            pos.y + dy[directionIndex]);
    }

    private Coordinate applyOffset(Coordinate coordinate) {
        return new Coordinate(coordinate.x * 2 + OFFSET, coordinate.y * 2 + OFFSET);
    }

    private void initDirectionIndexMapper() {
        directionIndexMapper.put(Direction.R, 0);
        directionIndexMapper.put(Direction.U, 1);
        directionIndexMapper.put(Direction.L, 2);
        directionIndexMapper.put(Direction.D, 3);
    }
}

class Coordinate {

    int x;
    int y;

    public Coordinate(
        int x,
        int y
    ) {
        this.x = x;
        this.y = y;
    }
}

enum Direction {
    U, D, R, L
}
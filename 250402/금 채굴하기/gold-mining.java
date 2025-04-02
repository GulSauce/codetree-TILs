import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M;
        int[][] grid;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        grid = new int[N + 1][N + 1];
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                grid[y][x] = sc.nextInt();
            }
        }
        sc.close();

        new Solver(N, M, grid).solve();
    }
}

class Solver {

    int gridIndex;
    int goldPrice;
    int GOLD = 1;

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, -1, 0, 1};
    int[][] grid;
    Queue<Coordinate> bfsQueue = new LinkedList<>();

    public Solver(
        int N,
        int M,
        int[][] grid
    ) {
        this.gridIndex = N;
        this.goldPrice = M;
        this.grid = grid;
    }

    public void solve() {
        int answer = 0;
        for (int y = 1; y <= gridIndex; y++) {
            for (int x = 1; x <= gridIndex; x++) {
                answer = Math.max(answer, getMaxGoldCountWithoutLoss(new Coordinate(x, y)));
            }
        }
        System.out.println(answer);
    }

    private int getMaxGoldCountWithoutLoss(Coordinate center) {
        int foundGoldCount = 0;
        int maxGoldCount = 0;
        boolean[][] visited = new boolean[gridIndex + 1][gridIndex + 1];

        bfsQueue.clear();
        bfsQueue.add(center);
        if (isGold(center)) {
            foundGoldCount = 1;
        }
        if (getMiningPrice(0) <= goldPrice * foundGoldCount) {
            maxGoldCount = 1;
        }
        visited[center.y][center.x] = true;

        while (!bfsQueue.isEmpty()) {
            Coordinate cur = bfsQueue.poll();
            for (int i = 0; i < 4; i++) {
                Coordinate next = new Coordinate(cur.x + dx[i], cur.y + dy[i]);
                if (isOutOfGrid(next)) {
                    continue;
                }
                if (visited[next.y][next.x]) {
                    continue;
                }

                visited[next.y][next.x] = true;
                bfsQueue.add(next);

                if (isGold(next)) {
                    foundGoldCount++;
                }
                int distFromCenter = getDistFromCenter(next, center);
                if (getMiningPrice(distFromCenter) <= goldPrice * foundGoldCount) {
                    maxGoldCount = foundGoldCount;
                }
            }
        }

        return maxGoldCount;
    }

    private boolean isOutOfGrid(Coordinate coordinate) {
        return coordinate.y < 1 || gridIndex < coordinate.y || coordinate.x < 1
            || gridIndex < coordinate.x;
    }

    private int getDistFromCenter(Coordinate next, Coordinate center) {
        return Math.abs(next.x - center.x) + Math.abs(next.y - center.y);
    }

    private boolean isGold(Coordinate coordinate) {
        return grid[coordinate.y][coordinate.x] == GOLD;
    }

    private int getMiningPrice(int count) {
        return count * count + (count + 1) * (count + 1);
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
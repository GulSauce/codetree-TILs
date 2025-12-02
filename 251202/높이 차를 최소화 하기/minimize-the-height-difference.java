import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m;
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        m = toInt(st);

        grid = new int[n + 1][m + 1];
        for (int y = 1; y <= n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= m; x++) {
                grid[y][x] = toInt(st);
            }
        }

        new Solver(grid).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int MAX_HEIGHT = 500;
    final int WIDTH;
    final int HEIGHT;
    int[][] grid;

    public Solver(int[][] grid) {
        this.grid = grid;
        this.WIDTH = grid[0].length - 1;
        this.HEIGHT = grid.length - 1;
    }

    public void solve() {
        int left = 0;
        int right = 500;
        int answer = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(mid)) {
                right = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isValid(int maxDiff) {
        for (int minHeight = 1; minHeight <= MAX_HEIGHT; minHeight++) {
            if (bfs(minHeight, minHeight + maxDiff)) {
                return true;
            }
        }
        return false;
    }

    private boolean bfs(int minHeight, int maxHeight) {
        Point start = new Point(1, 1);
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        if (grid[start.y][start.x] < minHeight || grid[start.y][start.x] > maxHeight) {
            return false;
        }

        HashSet<Point> visited = new HashSet<>();
        Queue<Point> bfsQueue = new LinkedList<>();
        bfsQueue.add(start);
        visited.add(start);

        while (!bfsQueue.isEmpty()) {
            Point cur = bfsQueue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (nextX < 1 || nextX > WIDTH
                    || nextY < 1 || nextY > HEIGHT) {
                    continue;
                }
                if (grid[nextY][nextX] < minHeight || grid[nextY][nextX] > maxHeight) {
                    continue;
                }
                Point next = new Point(nextX, nextY);
                if (visited.contains(next)) {
                    continue;
                }
                bfsQueue.add(next);
                visited.add(next);
            }
        }
        return visited.contains(new Point(WIDTH, HEIGHT));
    }
}

class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }
}
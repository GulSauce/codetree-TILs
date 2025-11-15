import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);

        grid = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
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

    final int TARGET_COUNT;
    int[][] grid;
    boolean[][] visited;
    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};

    public Solver(int[][] grid) {
        this.grid = grid;
        this.visited = new boolean[grid.length][grid[0].length];
        this.TARGET_COUNT = (visited.length * visited[0].length + 1) / 2;
    }

    public void solve() {
        int left = 0;
        int right = 1_000_000;
        int answer = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isPossible(int D) {
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                int count = bfs(new Point(x, y), D);
                if (TARGET_COUNT <= count) {
                    return true;
                }
            }
        }
        return false;
    }

    private int bfs(Point start, int maxDiff) {
        if (visited[start.y][start.x]) {
            return 0;
        }
        int[] count = new int[1];
        count[0] = 0;
        Queue<Point> q = new LinkedList<>();
        addQueue(q, start, count);
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = cur.y + dy[i];
                int nextX = cur.x + dx[i];
                if (nextY < 0 || nextY >= grid.length || nextX < 0 || nextX >= grid[0].length) {
                    continue;
                }
                if (visited[nextY][nextX]) {
                    continue;
                }
                int diff = Math.abs(grid[nextY][nextX] - grid[cur.y][cur.x]);
                if (maxDiff < diff) {
                    continue;
                }
                addQueue(q, new Point(nextX, nextY), count);
            }
        }
        return count[0];
    }

    private void addQueue(Queue<Point> q, Point point, int[] count) {
        count[0]++;
        visited[point.y][point.x] = true;
        q.add(point);
    }
}

class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
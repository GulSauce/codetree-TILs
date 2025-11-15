import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int M, N;
        int[][] board;
        boolean[][] colored;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = toInt(st);
        N = toInt(st);

        board = new int[M][N];
        colored = new boolean[M][N];

        for (int y = 0; y < M; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                board[y][x] = toInt(st);
            }
        }
        for (int y = 0; y < M; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                colored[y][x] = toInt(st) == 1;
            }
        }

        new Solver(board, colored).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(
            st.nextToken());
    }
}

class Solver {

    final int HEIGHT;
    final int WIDTH;
    int[][] board;
    int targetDiff;
    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};
    boolean[][] colored;
    boolean[][] visited;
    Point start;

    public Solver(int[][] board, boolean[][] colored) {
        this.board = board;
        this.colored = colored;
        this.HEIGHT = board.length;
        this.WIDTH = board[0].length;
        this.visited = new boolean[HEIGHT][WIDTH];
        loop:
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (colored[y][x]) {
                    start = new Point(x, y);
                    break loop;
                }
            }
        }
    }

    public void solve() {
        int left = 0;
        int right = 1_000_000_000;
        int answer = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isValid(int diff) {
        for (boolean[] array : visited) {
            Arrays.fill(array, false);
        }

        targetDiff = diff;
        dfs(start);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (colored[y][x] && !visited[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void dfs(Point point) {
        for (int i = 0; i < 4; i++) {
            Point next = new Point(point.x + dx[i], point.y + dy[i]);
            if (next.y < 0 || next.y >= HEIGHT || next.x < 0 || next.x >= WIDTH) {
                continue;
            }
            int diff = Math.abs(board[point.y][point.x] - board[next.y][next.x]);
            if (targetDiff < diff) {
                continue;
            }
            if (visited[next.y][next.x]) {
                continue;
            }
            visited[next.y][next.x] = true;
            dfs(next);
        }
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
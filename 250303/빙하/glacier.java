import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int gridRow;
        int gridCol;
        int[][] grid;

        public Solver(
            int N,
            int M,
            int[][] grid
        ) {
            this.gridRow = N - 1;
            this.gridCol = M - 1;
            this.grid = grid;
        }

        public void solve() {
            int elapsedTime = 1;
            int meltGlacier = bfs();
            while (true) {
                int nextMeltGlacier = bfs();
                if (nextMeltGlacier == 0) {
                    break;
                }
                elapsedTime++;
                meltGlacier = nextMeltGlacier;
            }
            System.out.println(elapsedTime + " " + meltGlacier);
        }

        private int bfs() {
            int[] dr = {0, -1, 0, 1};
            int[] dc = {1, 0, -1, 0};
            boolean[][] visited = new boolean[gridRow + 1][gridCol + 1];
            Queue<Coordinate> q = new LinkedList<>();
            Coordinate start = new Coordinate(0, 0);
            visited[start.r][start.c] = true;
            q.add(start);
            while (!q.isEmpty()) {
                Coordinate cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    Coordinate next = new Coordinate(cur.r + dr[i], cur.c + dc[i]);
                    if (isOutOfGrid(next)) {
                        continue;
                    }
                    if (visited[next.r][next.c]) {
                        continue;
                    }
                    visited[next.r][next.c] = true;
                    if (grid[next.r][next.c] == 0) {
                        q.add(next);
                    }
                }
            }
            int meltGlacier = 0;
            for (int i = 0; i < gridRow; i++) {
                for (int j = 0; j < gridCol; j++) {
                    if (!visited[i][j]) {
                        continue;
                    }
                    if (grid[i][j] == 0) {
                        continue;
                    }
                    meltGlacier++;
                    grid[i][j] = 0;
                }
            }
            return meltGlacier;
        }

        private boolean isOutOfGrid(Coordinate coordinate) {
            return coordinate.r < 0 || gridRow < coordinate.r || coordinate.c < 0
                || gridCol < coordinate.c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] grid = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                grid[r][c] = sc.nextInt();
            }
        }

        new Solver(N, M, grid).solve();
    }

    private static class Coordinate {

        int r;
        int c;

        public Coordinate(
            int r,
            int c
        ) {
            this.r = r;
            this.c = c;
        }
    }
}
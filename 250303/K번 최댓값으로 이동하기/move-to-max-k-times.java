import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int gridIndex;
        int repeatCount;
        Coordinate start;
        int[][] grid;

        public Solver(
            int N,
            int K,
            int[][] grid,
            Coordinate start
        ) {
            this.gridIndex = N;
            this.repeatCount = K;
            this.grid = grid;
            this.start = start;
        }

        public void solve() {
            Coordinate goal = repeatBfs();
            printAnswer(goal);
        }

        private void printAnswer(Coordinate goal) {
            System.out.println(goal.r + " " + goal.c);
        }

        private Coordinate repeatBfs() {
            Coordinate next = bfs(start);
            if (next.r == -1) {
                return start;
            }

            for (int i = 0; i < repeatCount - 1; i++) {
                Coordinate nextMinMaxCoordinate = bfs(next);
                if (nextMinMaxCoordinate.r == -1) {
                    return next;
                }
                next = nextMinMaxCoordinate;
            }
            return next;
        }

        private Coordinate bfs(Coordinate start) {
            int[] dr = {0, -1, 0, 1};
            int[] dc = {1, 0, -1, 0};
            List<Coordinate> candidates = new ArrayList<>();

            boolean[][] visited = new boolean[gridIndex + 1][gridIndex + 1];
            visited[start.r][start.c] = true;
            int currentNumber = grid[start.r][start.c];
            int minMax = getMinMax(start);
            Queue<Coordinate> q = new LinkedList<>();
            q.add(start);
            while (!q.isEmpty()) {
                Coordinate current = q.poll();
                for (int i = 0; i < 4; i++) {
                    Coordinate next = new Coordinate(current.r + dr[i], current.c + dc[i]);
                    if (isOutOfGrid(next)) {
                        continue;
                    }
                    if (visited[next.r][next.c]) {
                        continue;
                    }
                    if (currentNumber <= grid[next.r][next.c]) {
                        continue;
                    }
                    visited[next.r][next.c] = true;
                    if (grid[next.r][next.c] == minMax) {
                        candidates.add(next);
                    }
                    q.add(next);
                }
            }
            if (candidates.isEmpty()) {
                return new Coordinate(-1, -1);
            }
            Collections.sort(candidates);
            return candidates.get(0);
        }

        private int getMinMax(Coordinate start) {
            int[] dr = {0, -1, 0, 1};
            int[] dc = {1, 0, -1, 0};
            int minMax = 0;
            boolean[][] visited = new boolean[gridIndex + 1][gridIndex + 1];
            visited[start.r][start.c] = true;
            int currentNumber = grid[start.r][start.c];
            Queue<Coordinate> q = new LinkedList<>();
            q.add(start);
            while (!q.isEmpty()) {
                Coordinate current = q.poll();
                for (int i = 0; i < 4; i++) {
                    Coordinate next = new Coordinate(current.r + dr[i], current.c + dc[i]);
                    if (isOutOfGrid(next)) {
                        continue;
                    }
                    if (visited[next.r][next.c]) {
                        continue;
                    }
                    if (currentNumber <= grid[next.r][next.c]) {
                        continue;
                    }
                    visited[next.r][next.c] = true;
                    minMax = Math.max(minMax, grid[next.r][next.c]);
                    q.add(next);
                }
            }
            return minMax;
        }

        private boolean isOutOfGrid(Coordinate coordinate) {
            return coordinate.r < 1 || gridIndex < coordinate.r || coordinate.c < 1
                || gridIndex < coordinate.c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[][] grid = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                grid[r][c] = sc.nextInt();
            }
        }
        int r = sc.nextInt();
        int c = sc.nextInt();

        new Solver(N, K, grid, new Coordinate(r, c)).solve();
    }

    private static class Coordinate implements Comparable<Coordinate> {

        int r;
        int c;

        public Coordinate(
            int r,
            int c
        ) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Coordinate coordinate) {
            if (this.r == coordinate.r) {
                return this.c - coordinate.c;
            }
            return this.r - coordinate.r;
        }
    }
}
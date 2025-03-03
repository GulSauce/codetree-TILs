import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int gridIndex;
        int shelterCount;
        int personCount;

        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        final int CAN_MOVE = 0;
        final int WALL = 1;
        final int PERSON = 2;
        final int SHELTER = 3;

        int[][] dist;
        int[][] grid;
        int[][] answer;

        public Solver(
            int N,
            int H,
            int M,
            int[][] grid
        ) {
            this.gridIndex = N - 1;
            this.personCount = H;
            this.shelterCount = M;
            this.grid = grid;
            this.dist = new int[N][N];
            this.answer = new int[N][N];
        }

        public void solve() {
            initDist();
            List<Coordinate> shelterCoordinates = getShelterCoordinates();
            bfs(shelterCoordinates);
            printAnswer();
        }

        private void printAnswer() {
            for (int r = 0; r <= gridIndex; r++) {
                for (int c = 0; c <= gridIndex; c++) {
                    if (grid[r][c] != PERSON) {
                        answer[r][c] = 0;
                        continue;
                    }
                    if (dist[r][c] == NOT_ALLOCATED) {
                        answer[r][c] = -1;
                        continue;
                    }
                    answer[r][c] = dist[r][c];
                }
            }

            for (int[] array : answer) {
                for (int value : array) {
                    System.out.printf("%d ", value);
                }
                System.out.println();
            }
        }

        private void bfs(List<Coordinate> shelterCoordinates) {
            int[] dr = {0, -1, 0, 1};
            int[] dc = {1, 0, -1, 0};
            boolean[][] visited = new boolean[gridIndex + 1][gridIndex + 1];
            Queue<Coordinate> q = new LinkedList<>();
            for (Coordinate coordinate : shelterCoordinates) {
                visited[coordinate.r][coordinate.c] = true;
                dist[coordinate.r][coordinate.c] = 0;
                q.add(coordinate);
            }
            while (!q.isEmpty()) {
                Coordinate cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    Coordinate next = new Coordinate(cur.r + dr[i], cur.c + dc[i]);
                    if (isOutOfGrid(next)) {
                        continue;
                    }
                    if (isWall(next)) {
                        continue;
                    }
                    if (visited[next.r][next.c]) {
                        continue;
                    }
                    if (dist[next.r][next.c] != NOT_ALLOCATED) {
                        continue;
                    }
                    visited[next.r][next.c] = true;
                    dist[next.r][next.c] = dist[cur.r][cur.c] + 1;
                    q.add(next);
                }
            }
        }

        private boolean isOutOfGrid(Coordinate coordinate) {
            return coordinate.r < 0 || gridIndex < coordinate.r || coordinate.c < 0
                || gridIndex < coordinate.c;
        }

        private boolean isWall(Coordinate coordinate) {
            return grid[coordinate.r][coordinate.c] == WALL;
        }

        private List<Coordinate> getShelterCoordinates() {
            List<Coordinate> coordinates = new ArrayList<>();
            for (int r = 0; r <= gridIndex; r++) {
                for (int c = 0; c <= gridIndex; c++) {
                    if (grid[r][c] == SHELTER) {
                        coordinates.add(new Coordinate(r, c));
                    }
                }
            }
            return coordinates;
        }

        private void initDist() {
            for (int[] array : dist) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int H = sc.nextInt();
        int M = sc.nextInt();
        int[][] grid = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                grid[r][c] = sc.nextInt();
            }
        }
        new Solver(N, H, M, grid).solve();
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

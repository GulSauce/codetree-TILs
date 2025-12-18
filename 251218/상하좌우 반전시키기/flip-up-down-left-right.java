import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);

        grid = new int[n + 1][n + 1];

        for (int y = 1; y <= n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= n; x++) {
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

    final int ZERO = 0;
    final int ONE = 1;
    int pushCount = 0;
    int[][] grid;

    public Solver(int[][] grid) {
        this.grid = grid;
    }

    public void solve() {
        for (int y = 2; y < grid.length; y++) {
            for (int x = 1; x < grid[0].length; x++) {
                if (grid[y - 1][x] == ONE) {
                    continue;
                }
                pushButtonAt(x, y);
            }
        }

        boolean success = true;
        for (int y = 1; y < grid.length; y++) {
            for (int x = 1; x < grid[0].length; x++) {
                if (grid[y][x] == ZERO) {
                    success = false;
                    break;
                }
            }
        }
        System.out.println(success ? pushCount : -1);
    }

    private void pushButtonAt(int x, int y) {
        pushCount++;
        grid[y][x] = grid[y][x] ^ 1;
        if (1 <= y - 1) {
            grid[y - 1][x] = grid[y - 1][x] ^ 1;
        }
        if (1 <= x - 1) {
            grid[y][x - 1] = grid[y][x - 1] ^ 1;
        }
        if (y + 1 < grid.length) {
            grid[y + 1][x] = grid[y + 1][x] ^ 1;
        }
        if (x + 1 < grid[0].length) {
            grid[y][x + 1] = grid[y][x + 1] ^ 1;
        }
    }
}
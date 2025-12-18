import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        int[][] grid;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        grid = new int[N + 1][N + 1];
        for (int y = 1; y < grid.length; y++) {
            st = new StringTokenizer(br.readLine());
            String row = st.nextToken();
            for (int x = 0; x < row.length(); x++) {
                grid[y][x] = row.charAt(x) - '0';
            }
        }

        new Solver(grid).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int pushCount = 0;
    int[][] grid;

    public Solver(int[][] grid) {
        this.grid = grid;
    }

    public void solve() {
        for (int y = grid.length - 1; y >= 1; y--) {
            for (int x = grid[0].length - 1; x >= 1; x--) {
                if (grid[y][x] == 0) {
                    continue;
                }
                pushButtonAt(x, y);
            }
        }
        System.out.println(pushCount);
    }

    private void pushButtonAt(int endX, int endY) {
        pushCount++;
        for (int y = 1; y <= endY; y++) {
            for (int x = 1; x <= endX; x++) {
                grid[y][x] = grid[y][x] ^ 1;
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        for (int row = 1; row <= n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= m; col++) {
                grid[row][col] = toInt(st);
            }
        }

        new Solver(grid).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    final int ROW_LENGTH;
    final int COL_LENGTH;
    int pressCount = 0;
    int[][] grid;
    int[][] gridCopy;

    public Solver(int[][] grid) {
        this.grid = grid;
        this.ROW_LENGTH = grid.length - 1;
        this.COL_LENGTH = grid[0].length - 1;
        this.gridCopy = new int[ROW_LENGTH + 1][COL_LENGTH + 1];
    }

    public void solve() {
        int answer = NOT_ALLOCATED;
        for (int bitMask = 0; bitMask < (1 << (COL_LENGTH + 1)); bitMask++) {
            for (int row = 0; row <= ROW_LENGTH; row++) {
                gridCopy[row] = grid[row].clone();
            }
            pressCount = 0;
            for (int col = 1; col <= COL_LENGTH; col++) {
                if ((bitMask >> col & 1) == 1) {
                    pressNumber(1, col);
                }
            }

            for (int row = 2; row <= ROW_LENGTH; row++) {
                for (int col = 1; col <= COL_LENGTH; col++) {
                    if (gridCopy[row - 1][col] == 0) {
                        pressNumber(row, col);
                    }
                }
            }

            boolean zeroExist = false;
            for (int col = 1; col <= COL_LENGTH; col++) {
                if (gridCopy[ROW_LENGTH][col] == 0) {
                    zeroExist = true;
                    break;
                }
            }
            if (!zeroExist) {
                answer = Math.min(answer, pressCount);
            }
        }
        System.out.println(answer == NOT_ALLOCATED ? -1 : answer);
    }

    private void pressNumber(int row, int col) {
        int[] dy = {1, 0, -1, 0, 0};
        int[] dx = {0, -1, 0, 1, 0};

        for (int i = 0; i < dy.length; i++) {
            int nY = row + dy[i];
            int nX = col + dx[i];

            if (nY <= 0 || nY > ROW_LENGTH || nX <= 0 || nX > COL_LENGTH) {
                continue;
            }

            gridCopy[nY][nX] ^= 1;
        }
        pressCount++;
    }
}
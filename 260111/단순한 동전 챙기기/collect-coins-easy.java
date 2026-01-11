import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[][] grid = new char[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            String str = sc.next();
            for (int col = 1; col <= N; col++) {
                grid[row][col] = str.charAt(col - 1);
            }
        }
        new Solver(N, grid).solve();
    }
}

class Solver {

    int answer = Integer.MAX_VALUE;
    int gridIndex;

    int startRow;
    int startCol;
    int endRow;
    int endCol;

    final int MAX_COUNT = 3;

    char[][] grid;
    List<CoinInfo> coinInfos = new ArrayList<>();
    List<CoinInfo> selectedCoinInfos = new ArrayList<>();

    public Solver(int N, char[][] grid) {
        this.gridIndex = N;
        this.grid = grid;
    }

    public void solve() {
        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                if (grid[row][col] == 'S') {
                    startRow = row;
                    startCol = col;
                    continue;
                }
                if (grid[row][col] == 'E') {
                    endRow = row;
                    endCol = col;
                }
            }
        }
        Collections.sort(coinInfos);

        for (int row = 1; row <= gridIndex; row++) {
            for (int col = 1; col <= gridIndex; col++) {
                char valueCandidate = grid[row][col];
                if (valueCandidate < '0' || '9' < valueCandidate) {
                    continue;
                }
                int value = valueCandidate - '0';
                coinInfos.add(new CoinInfo(value, row, col));
            }
        }
        Collections.sort(coinInfos);

        selectCoinInfosWithMeasuringDist(0, -1);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    private void selectCoinInfosWithMeasuringDist(int prevCount, int prevIndex) {
        if (prevCount == MAX_COUNT) {
            int dist = getDist();
            answer = Math.min(answer, dist);
            return;
        }

        for (int i = prevIndex + 1; i < coinInfos.size(); i++) {
            selectedCoinInfos.add(coinInfos.get(i));
            selectCoinInfosWithMeasuringDist(prevCount + 1, i);
            selectedCoinInfos.remove(selectedCoinInfos.size() - 1);
        }
    }

    private int getDist() {
        int dist = Math.abs(selectedCoinInfos.get(0).row - startRow) + Math.abs(
            selectedCoinInfos.get(0).col - startCol);
        for (int i = 1; i < MAX_COUNT; i++) {
            dist += Math.abs(selectedCoinInfos.get(i).row - selectedCoinInfos.get(i - 1).row)
                + Math.abs(selectedCoinInfos.get(i).col - selectedCoinInfos.get(i - 1).col);
        }
        dist += Math.abs(endRow - selectedCoinInfos.get(MAX_COUNT - 1).row) + Math.abs(
            endCol - selectedCoinInfos.get(MAX_COUNT - 1).col);

        return dist;
    }
}

class CoinInfo implements Comparable<CoinInfo> {

    int value;
    int row;
    int col;

    @Override
    public int compareTo(CoinInfo coinInfo) {
        return this.value - coinInfo.value;
    }

    public CoinInfo(int value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
    }
}


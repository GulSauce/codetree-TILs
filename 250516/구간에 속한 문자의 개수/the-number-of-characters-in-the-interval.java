import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M, K;
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Square> squares = new ArrayList<>();
        int r1, c1, r2, c2;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < N; i++) {
            strings.add(sc.next());
        }
        for (int i = 0; i < K; i++) {
            r1 = sc.nextInt();
            c1 = sc.nextInt();
            r2 = sc.nextInt();
            c2 = sc.nextInt();
            squares.add(new Square(r1, c1, r2, c2));
        }
        sc.close();

        new Solver(strings, squares).solve();
    }
}

class Solver {

    ArrayList<String> strings;
    ArrayList<Square> squares;

    char[][] grid;

    char[] targets = new char[]{'a', 'b', 'c'};
    int[][][] prefixSum;

    public Solver(
        ArrayList<String> strings,
        ArrayList<Square> squares
    ) {
        this.prefixSum = new int[3][strings.size() + 1][strings.get(0).length() + 1];
        this.grid = new char[strings.size() + 1][strings.get(0).length() + 1];
        this.strings = strings;
        this.squares = squares;
    }

    public void solve() {
        init();
        for (Square square : squares) {
            for (int i = 0; i < 3; i++) {
                int count =
                    prefixSum[i][square.r2][square.c2] - prefixSum[i][square.r2][square.c1 - 1]
                        - prefixSum[i][square.r1 - 1][square.c2] + prefixSum[i][square.r1 - 1][
                        square.c1 - 1];
                System.out.print(count + " ");
            }
            System.out.println();
        }
    }

    private void init() {
        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid[0].length; col++) {
                grid[row][col] = strings.get(row - 1).charAt(col - 1);
            }
        }

        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid[0].length; col++) {
                char curChar = grid[row][col];
                for (int i = 0; i < 3; i++) {
                    int value = curChar == targets[i] ? 1 : 0;
                    prefixSum[i][row][col] =
                        prefixSum[i][row - 1][col] + prefixSum[i][row][col - 1] - prefixSum[i][row
                            - 1][col - 1] + value;
                }
            }
        }
    }
}

class Square {

    int r1;
    int c1;
    int r2;
    int c2;

    public Square(
        int r1,
        int c1,
        int r2,
        int c2
    ) {
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
    }
}
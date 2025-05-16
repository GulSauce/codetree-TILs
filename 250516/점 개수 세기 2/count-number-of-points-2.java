import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        int N, Q;
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        int x, y;
        ArrayList<Square> squares = new ArrayList<>();
        int x1, y1, x2, y2;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 0; i < N; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            coordinates.add(new Coordinate(x, y));
        }
        for (int i = 0; i < Q; i++) {
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            squares.add(new Square(new Coordinate(x1, y1), new Coordinate(x2, y2)));
        }
        sc.close();

        new Solver(coordinates, squares).solve();
    }
}

class Solver {

    final int MAX_VALUE = 2500;
    ArrayList<Coordinate> coordinates;
    ArrayList<Square> squares;
    TreeSet<Integer> xTreeSet = new TreeSet<>();
    TreeSet<Integer> yTreeSet = new TreeSet<>();
    TreeSet<Coordinate> squarePointFinder = new TreeSet<>();
    HashMap<Integer, Integer> xRealVirtualMap = new HashMap<>();
    HashMap<Integer, Integer> yRealVirtualMap = new HashMap<>();
    boolean[][] exist = new boolean[MAX_VALUE + 1][MAX_VALUE + 1];
    int[][] prefixSum = new int[MAX_VALUE + 1][MAX_VALUE + 1];

    public Solver(
        ArrayList<Coordinate> coordinates,
        ArrayList<Square> squares
    ) {
        this.coordinates = coordinates;
        this.squares = squares;
    }

    public void solve() {
        for (Coordinate coordinate : coordinates) {
            xTreeSet.add(coordinate.x);
            yTreeSet.add(coordinate.y);
        }
        int curX = 1;
        int curY = 1;
        for (Integer realX : xTreeSet) {
            xRealVirtualMap.put(realX, curX);
            int nextX = curX + 1;
            curX = nextX;
        }
        for (Integer realY : yTreeSet) {
            yRealVirtualMap.put(realY, curY);
            int nextY = curY + 1;
            curY = nextY;
        }
        changeCoordinates();
        setExist();
        setPrefixSum();

        squarePointFinder.addAll(coordinates);
        for (Square square : squares) {
            Coordinate rightUpper = getRightUpperFrom(square.rightUpper);
            Coordinate leftBelow = getLeftBelowFrom(square.leftBelow);

            if (rightUpper.x == -1 || leftBelow.x == -1) {
                System.out.println(0);
                continue;
            }
            
            int count =
                prefixSum[rightUpper.y][rightUpper.x] - prefixSum[leftBelow.y - 1][rightUpper.x]
                    - prefixSum[rightUpper.y][leftBelow.x - 1] + prefixSum[leftBelow.y - 1][
                    leftBelow.x - 1];
            System.out.println(count);
        }
    }

    private Coordinate getRightUpperFrom(Coordinate coordinate) {

        int nearestX = Optional.ofNullable(xTreeSet.floor(coordinate.x)).orElse(-1);
        int nearestY = Optional.ofNullable(yTreeSet.floor(coordinate.y)).orElse(-1);

        if (nearestX == -1 || nearestY == -1) {
            return new Coordinate(-1, -1);
        }

        int virtualX = xRealVirtualMap.get(nearestX);
        int virtualY = yRealVirtualMap.get(nearestY);

        return new Coordinate(virtualX, virtualY);
    }

    private Coordinate getLeftBelowFrom(Coordinate coordinate) {
        int nearestX = Optional.ofNullable(xTreeSet.ceiling(coordinate.x)).orElse(-1);
        int nearestY = Optional.ofNullable(yTreeSet.ceiling(coordinate.y)).orElse(-1);

        if (nearestX == -1 || nearestY == -1) {
            return new Coordinate(-1, -1);
        }

        int virtualX = xRealVirtualMap.get(nearestX);
        int virtualY = yRealVirtualMap.get(nearestY);

        return new Coordinate(virtualX, virtualY);
    }

    private void setPrefixSum() {
        for (int row = 1; row <= MAX_VALUE; row++) {
            for (int col = 1; col <= MAX_VALUE; col++) {
                int value = exist[row][col] ? 1 : 0;
                prefixSum[row][col] =
                    prefixSum[row - 1][col] + prefixSum[row][col - 1] - prefixSum[row - 1][col - 1]
                        + value;
            }
        }
    }

    private void setExist() {
        for (Coordinate coordinate : coordinates) {
            exist[coordinate.y][coordinate.x] = true;
        }
    }

    private void changeCoordinates() {
        ArrayList<Coordinate> newCoordinates = new ArrayList<>();
        for (Coordinate coordinate : coordinates) {
            int virtualX = xRealVirtualMap.get(coordinate.x);
            int virtualY = yRealVirtualMap.get(coordinate.y);
            newCoordinates.add(new Coordinate(virtualX, virtualY));
        }
        coordinates = newCoordinates;
    }
}

class Square {

    Coordinate leftBelow;
    Coordinate rightUpper;

    public Square(
        Coordinate leftBelow,
        Coordinate rightUpper
    ) {
        this.leftBelow = leftBelow;
        this.rightUpper = rightUpper;
    }
}

class Coordinate implements Comparable<Coordinate> {

    @Override
    public int compareTo(Coordinate other) {
        if (x == other.x) {
            return Integer.compare(y, other.y);
        }
        return Integer.compare(x, other.x);
    }

    int x;
    int y;

    public Coordinate(
        int x,
        int y
    ) {
        this.x = x;
        this.y = y;
    }
}
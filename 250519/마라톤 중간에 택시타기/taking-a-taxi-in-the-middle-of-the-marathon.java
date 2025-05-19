import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(-1, -1));
        int x, y;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            coordinates.add(new Coordinate(x, y));
        }
        br.close();

        new Solver(coordinates).solve();
    }
}

class Solver {

    ArrayList<Coordinate> coordinates;

    int[] leftPrefixSum;
    int[] rightPrefixSum;

    public Solver(
        ArrayList<Coordinate> coordinates
    ) {
        this.leftPrefixSum = new int[coordinates.size() + 1];
        this.rightPrefixSum = new int[coordinates.size() + 1];
        this.coordinates = coordinates;
    }

    public void solve() {
        leftPrefixSum[1] = 0;
        for (int i = 2; i < coordinates.size(); i++) {
            leftPrefixSum[i] =
                getTaxiDist(coordinates.get(i), coordinates.get(i - 1)) + leftPrefixSum[i - 1];
        }
        rightPrefixSum[coordinates.size() - 1] = 0;
        for (int i = coordinates.size() - 2; i >= 1; i--) {
            rightPrefixSum[i] = getTaxiDist(coordinates.get(i), coordinates.get(i + 1))
                + rightPrefixSum[i + 1];
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 2; i < coordinates.size() - 1; i++) {
            int currentDist =
                leftPrefixSum[i - 1] + getTaxiDist(coordinates.get(i - 1), coordinates.get(i + 1))
                    + rightPrefixSum[i + 1];
            answer = Math.min(answer, currentDist);
        }
        System.out.println(answer);
    }

    private int getTaxiDist(Coordinate a, Coordinate b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}

class Coordinate {

    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
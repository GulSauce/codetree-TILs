import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int M;
        int A, B;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = toInt(st);
        st = new StringTokenizer(br.readLine());
        A = toInt(st);
        B = toInt(st);

        new Solver(M, A, B).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Line {

    int start;
    int end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Solver {

    int maxNumberPool;
    int selectMaxNumber;
    int selectMinNumber;

    int minTime = Integer.MAX_VALUE;
    int maxTime = 0;

    public Solver(int maxNumberPool, int selectMinNumber, int selectMaxNumber) {
        this.maxNumberPool = maxNumberPool;
        this.selectMinNumber = selectMinNumber;
        this.selectMaxNumber = selectMaxNumber;
    }

    public void solve() {
        for (int selectNumber = selectMinNumber; selectNumber <= selectMaxNumber; selectNumber++) {
            int time = binarySearchWithCalcTime(selectNumber);
            minTime = Math.min(minTime, time);
            maxTime = Math.max(maxTime, time);
        }
        System.out.println(minTime + " " + maxTime);
    }

    private int binarySearchWithCalcTime(int query) {
        int left = 1;
        int right = maxNumberPool;
        int elapsedTime = 0;
        while (left <= right) {
            elapsedTime++;
            int mid = (left + right) / 2;
            if (query < mid) {
                right = mid - 1;
            }
            if (query == mid) {
                break;
            }
            if (mid < query) {
                left = mid + 1;
            }
        }
        return elapsedTime;
    }
}
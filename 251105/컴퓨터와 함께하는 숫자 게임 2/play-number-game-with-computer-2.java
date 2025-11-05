import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        long M;
        long A, B;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = toInt(st);
        st = new StringTokenizer(br.readLine());
        A = toInt(st);
        B = toInt(st);

        new Solver(M, A, B).solve();
    }

    private static long toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Line {

    long start;
    long end;

    public Line(long start, long end) {
        this.start = start;
        this.end = end;
    }
}

class Solver {

    long maxNumberPool;
    long selectMaxNumber;
    long selectMinNumber;

    long minTime = Integer.MAX_VALUE;
    long maxTime = 0;

    public Solver(long maxNumberPool, long selectMinNumber, long selectMaxNumber) {
        this.maxNumberPool = maxNumberPool;
        this.selectMinNumber = selectMinNumber;
        this.selectMaxNumber = selectMaxNumber;
    }

    public void solve() {
        for (long selectNumber = selectMinNumber; selectNumber <= selectMaxNumber; selectNumber++) {
            long time = binarySearchWithCalcTime(selectNumber);
            minTime = Math.min(minTime, time);
            maxTime = Math.max(maxTime, time);
        }
        System.out.println(minTime + " " + maxTime);
    }

    private long binarySearchWithCalcTime(long query) {
        long left = 1;
        long right = maxNumberPool;
        long elapsedTime = 0;
        while (left <= right) {
            elapsedTime++;
            long mid = (left + right) / 2;
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
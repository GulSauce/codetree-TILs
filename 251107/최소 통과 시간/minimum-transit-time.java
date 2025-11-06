import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Integer> holeTimes = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            holeTimes.add(toInt(st));
        }

        new Solver(N, holeTimes).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int boxCount;
    List<Integer> holeTimes;

    public Solver(int boxCount, List<Integer> holeTimes) {
        this.boxCount = boxCount;
        this.holeTimes = holeTimes;
    }

    public void solve() {
        Collections.sort(holeTimes);

        long left = 0;
        long right = 1_000_000_000L * 100_000;
        long answer = Long.MAX_VALUE;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (isPutable(mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isPutable(long targetTime) {
        long count = 0;
        for (Integer holeTime : holeTimes) {
            count += targetTime / (long) holeTime;
        }
        return boxCount <= count;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, K;
        List<Integer> points = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        K = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(toInt(st));
        }

        new Solver(K, points).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int bombCount;
    List<Integer> points;

    public Solver(int bombCount, List<Integer> points) {
        this.bombCount = bombCount;
        this.points = points;
    }

    public void solve() {
        Collections.sort(points);
        int left = 0;
        int right = 1_000_000_000;
        int answer = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(mid)) {
                right = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isValid(int maxDist) {
        int count = 1;
        int start = 0;
        int end = 0;
        while (end < points.size()) {
            if (points.get(end) - points.get(start) <= 2 * maxDist) {
                end++;
                continue;
            }
            start = end;
            count++;
        }
        return count <= bombCount;
    }
}
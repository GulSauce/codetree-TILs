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
        List<Integer> points = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(toInt(st));
        }

        new Solver(M, points).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int boxCount;
    List<Integer> points;

    public Solver(int boxCount, List<Integer> points) {
        this.boxCount = boxCount;
        this.points = points;
    }

    public void solve() {
        Collections.sort(points);

        int left = 0;
        int right = 1_000_000_000;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPutable(mid)) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isPutable(int dist) {
        int putCount = 1;
        int cur = points.get(0);
        for (Integer point : points) {
            int diff = point - cur;
            if (dist <= diff) {
                putCount++;
                cur = point;
            }
        }
        return boxCount <= putCount;
    }
}
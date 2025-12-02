import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, K, L;
        List<Integer> writeCounts = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        K = toInt(st);
        L = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            writeCounts.add(toInt(st));
        }
        br.close();

        new Solver(K, L, writeCounts).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int additionalPostItCount;
    int writableCountPerPostIt;
    List<Integer> writeCounts;

    public Solver(int additionalPostItCount, int writableCountPerPostIt,
        List<Integer> writeCounts) {
        this.additionalPostItCount = additionalPostItCount;
        this.writableCountPerPostIt = writableCountPerPostIt;
        this.writeCounts = writeCounts;
    }

    public void solve() {
        Collections.sort(writeCounts, Collections.reverseOrder());
        int left = 0;
        int right = 100_000;
        int answer = left;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(mid)) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isValid(int hIndex) {
        if (writeCounts.size() < hIndex) {
            return false;
        }
        long lackCount = 0;
        for (int i = 0; i < hIndex; i++) {
            if (writeCounts.get(i) >= hIndex) {
                continue;
            }
            if (additionalPostItCount < hIndex - writeCounts.get(i)) {
                return false;
            }
            lackCount += hIndex - writeCounts.get(i);
        }
        return lackCount <= (long) additionalPostItCount * writableCountPerPostIt;
    }
}
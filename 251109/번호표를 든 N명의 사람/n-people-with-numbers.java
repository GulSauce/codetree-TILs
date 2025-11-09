import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, T;
        List<Integer> neededTimes = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        T = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            neededTimes.add(toInt(st));
        }

        new Solver(T, neededTimes).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int maxLimitTime;
    List<Integer> neededTimes;

    public Solver(int maxLimitTime, List<Integer> neededTimes) {
        this.maxLimitTime = maxLimitTime;
        this.neededTimes = neededTimes;
    }

    public void solve() {
        int left = 1;
        int right = neededTimes.size();
        int answer = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isEnable(mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isEnable(int peopleCount) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i = 0;
        for (; i < peopleCount; i++) {
            pq.add(neededTimes.get(i));
        }
        int curOut = 0;
        while (!pq.isEmpty()) {
            curOut = pq.poll();
            if (i == neededTimes.size()) {
                continue;
            }
            pq.add(curOut + neededTimes.get(i));
            i++;
        }
        return maxLimitTime >= curOut;
    }
}
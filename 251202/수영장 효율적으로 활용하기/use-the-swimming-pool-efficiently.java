import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Integer> swimTimes = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            swimTimes.add(toInt(st));
        }

        new Solver(M, swimTimes).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int totalPerson;
    int totalLaneCount;
    List<Integer> swimTimes;

    public Solver(int totalLaneCount, List<Integer> swimTimes) {
        this.totalLaneCount = totalLaneCount;
        this.swimTimes = swimTimes;
        this.totalPerson = swimTimes.size() - 1;
    }

    public void solve() {
        int left = 0;
        int right = 1_440 * 100_000;
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

    private boolean isValid(int totalTime) {
        int laneCount = 1;
        int curLaneSum = swimTimes.get(0);
        if (totalTime < swimTimes.get(0)) {
            return false;
        }
        for (int i = 1; i < swimTimes.size(); i++) {
            if (totalTime < swimTimes.get(i)) {
                return false;
            }
            boolean isOutOfTime = totalTime < curLaneSum + swimTimes.get(i);
            if (isOutOfTime) {
                laneCount++;
                curLaneSum = swimTimes.get(i);
            } else {
                curLaneSum += swimTimes.get(i);
            }
        }
        return laneCount <= totalLaneCount;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);

        new Solver(N).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    long targetSequence;

    public Solver(int targetSequence) {
        this.targetSequence = targetSequence;
    }

    public void solve() {
        long left = 1;
        long right = 5L * targetSequence;
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long realNumberCount = getRealNumberCount(mid);
            if (targetSequence < realNumberCount) {
                right = mid - 1;
            }
            if (targetSequence == realNumberCount) {
                answer = mid;
                break;
            }
            if (realNumberCount < targetSequence) {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private long getRealNumberCount(long number) {
        return number - number / 5 - number / 3 + number / (5 * 3);
    }
}
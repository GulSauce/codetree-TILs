import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        long S;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Long.parseLong(st.nextToken());

        new Solver(S).solve();
    }
}

class Solver {

    long maxSum;

    public Solver(long maxSum) {
        this.maxSum = maxSum;
    }

    public void solve() {
        long left = 0;
        long right = maxSum;
        long query = maxSum;
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long found = mid * (mid + 1) / 2;
            if (query < found) {
                right = mid - 1;
            }
            if (found <= query) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
}
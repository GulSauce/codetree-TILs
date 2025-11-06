import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, K;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        st = new StringTokenizer(br.readLine());
        K = toInt(st);

        new Solver(N, K).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    long matrixSize;
    int targetSequence;

    public Solver(int matrixSize, int targetSequence) {
        this.matrixSize = matrixSize;
        this.targetSequence = targetSequence;
    }

    public void solve() {
        int left = 0;
        int right = (int) Math.min(1_000_000_000, matrixSize * matrixSize);
        int answer = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (targetSequence <= getMinCount(mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    int getMinCount(int target) {
        int count = 0;
        for (int i = 1; i <= matrixSize; i++) {
            int j = target / i;
            count += Math.min(matrixSize, j);
        }
        return count;
    }
}
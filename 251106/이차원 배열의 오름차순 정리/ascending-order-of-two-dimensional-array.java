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

    int matrixSize;
    int targetNumber;

    public Solver(int matrixSize, int targetNumber) {
        this.matrixSize = matrixSize;
        this.targetNumber = targetNumber;
    }

    public void solve() {
        int left = 0;
        int right = matrixSize * matrixSize;
        int answer = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (targetNumber <= getMinCount(mid)) {
                right = mid - 1;
                answer = Math.min(answer, mid);
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
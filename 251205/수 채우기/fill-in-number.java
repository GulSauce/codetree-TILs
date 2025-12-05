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

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    final int FIVE = 5;
    final int TWO = 2;
    int targetNumber;

    public Solver(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public void solve() {
        int answer = NOT_ALLOCATED;
        int start = targetNumber / FIVE;
        for (int fiveCount = start; fiveCount >= 0; fiveCount--) {
            int remain = targetNumber - (FIVE * fiveCount);
            if (remain % TWO == 0) {
                answer = Math.min(answer, fiveCount + remain / TWO);
            }
        }
        System.out.println(answer == NOT_ALLOCATED ? -1 : answer);
    }
}
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

    final int FIVE = 5;
    final int TWO = 2;
    int targetNumber;

    public Solver(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public void solve() {
        int answer = Integer.MAX_VALUE;
        int start = targetNumber / FIVE;
        for (int fiveCount = start; fiveCount >= 1; fiveCount--) {
            int remain = targetNumber - (FIVE * fiveCount);
            if (remain % TWO == 0) {
                answer = Math.min(answer, fiveCount + remain / TWO);
            }
        }
        if (targetNumber % TWO == 0) {
            answer = Math.min(answer, targetNumber / TWO);
        }
        System.out.println(answer);
    }
}
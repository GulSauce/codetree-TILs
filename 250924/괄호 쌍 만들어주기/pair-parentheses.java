import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String parentheses = st.nextToken();

        new Solver(parentheses).solve();
    }
}

class Solver {

    String parentheses;

    int MAX_LENGTH = 100_000;
    int[] leftCount = new int[MAX_LENGTH];
    int[] rightCount = new int[MAX_LENGTH];

    public Solver(
        String parentheses
    ) {
        this.parentheses = parentheses;
    }

    public void solve() {
        for (int i = 1; i < parentheses.length(); i++) {
            if (parentheses.substring(i - 1, i + 1).equals("((")) {
                leftCount[i] = leftCount[i - 1] + 1;
            } else {
                leftCount[i] = leftCount[i - 1];
            }
        }

        for (int i = parentheses.length() - 2; i >= 0; i--) {
            if (parentheses.substring(i, i + 2).equals("))")) {
                rightCount[i] = rightCount[i + 1] + 1;
            } else {
                rightCount[i] = rightCount[i + 1];
            }
        }

        long answer = 0;
        for (int i = 0; i < parentheses.length() - 1; i++) {
            int left = leftCount[i];
            int right = rightCount[i + 1];

            if (left <= 1) {
                continue;
            }
            if (right <= 1) {
                continue;
            }
            answer = Math.max(answer, (long) left * right);
        }

        System.out.println(answer);
    }
}
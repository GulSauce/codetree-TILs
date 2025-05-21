import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        String string;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        string = " " + st.nextToken();

        new Solver(string).solve();
    }
}

class Solver {

    String string;

    int[] leftCCount;
    int[] rightWCount;

    public Solver(
        String string
    ) {
        this.string = string;
        this.leftCCount = new int[string.length()];
        this.rightWCount = new int[string.length() + 1];
    }

    public void solve() {
        for (int i = 1; i < string.length(); i++) {
            int value = string.charAt(i) == 'C' ? 1 : 0;
            leftCCount[i] = leftCCount[i - 1] + value;
        }
        for (int i = string.length() - 1; i >= 1; i--) {
            int value = string.charAt(i) == 'W' ? 1 : 0;
            rightWCount[i] = rightWCount[i + 1] + value;
        }

        long answer = 0;
        for (int i = 2; i < string.length() - 1; i++) {
            if (string.charAt(i) != 'O') {
                continue;
            }
            answer += (long) leftCCount[i - 1] * rightWCount[i + 1];
        }
        System.out.println(answer);
    }
}
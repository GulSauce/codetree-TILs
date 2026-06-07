import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String input;
        String target;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        input = st.nextToken();

        st = new StringTokenizer(br.readLine());
        target = st.nextToken();

        new Solver(input, target).solve();
    }
}

class Solver {

    int[] pPowerOf;

    String input;
    String target;

    final int R = 1_000_000_7;
    final int P = 51;

    public Solver(String input, String target) {
        this.pPowerOf = new int[target.length()];
        this.input = input;
        this.target = target;
    }

    public void solve() {
        pPowerOf[0] = 1;
        for (int i = 1; i < pPowerOf.length; i++) {
            pPowerOf[i] = (pPowerOf[i - 1] * P) % R;
        }

        int targetHash = 0;
        for (int i = 0; i < target.length(); i++) {
            targetHash += pPowerOf[target.length() - 1 - i] * (int) target.charAt(i);
            targetHash %= R;
        }

        if (input.length() < target.length()) {
            System.out.println(-1);
            System.exit(0);
        }

        int inputHash = 0;
        for (int i = 0; i < target.length(); i++) {
            inputHash += pPowerOf[target.length() - 1 - i] * (int) input.charAt(i);
            inputHash %= R;
        }
        if (targetHash == inputHash) {
            System.out.println(0);
            System.exit(0);
        }

        int curStart = 0;
        int curEnd = target.length() - 1;
        while (true) {
            inputHash -= pPowerOf[target.length() - 1] * input.charAt(curStart);
            inputHash %= R;
            if (inputHash < 0) {
                inputHash += R;
            }

            inputHash *= pPowerOf[1];
            inputHash %= R;
            curStart++;

            curEnd++;
            if (curEnd >= input.length()) {
                break;
            }
            inputHash += pPowerOf[0] * input.charAt(curEnd);
            inputHash %= R;

            if (inputHash == targetHash) {
                boolean notSame = false;
                for (int i = curStart; i <= curEnd; i++) {
                    if (input.charAt(i) != target.charAt(i - curStart)) {
                        notSame = true;
                        break;
                    }
                }
                if (notSame) {
                    continue;
                }
                System.out.println(curStart);
                System.exit(0);
            }
        }

        System.out.println(-1);
    }
}
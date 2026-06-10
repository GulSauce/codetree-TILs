import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String T;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = st.nextToken();

        new Solver(T).solve();
    }
}

class Solver {

    int[] preSufLength;
    String target;

    public Solver(String target) {
        this.target = target;
        this.preSufLength = new int[target.length()];
    }

    public void solve() {
        int length = 0;
        int i = 1;
        while (true) {
            if (i == target.length()) {
                break;
            }
            if (target.charAt(length) == target.charAt(i)) {
                length++;
                preSufLength[i] = length;
                i++;
            } else if (length == 0) {
                i++;
            } else {
                length = preSufLength[length - 1];
            }
        }

        System.out.println(target.length() - preSufLength[target.length() - 1]);
    }
}
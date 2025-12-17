import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        String source;
        String target;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        source = st.nextToken();

        st = new StringTokenizer(br.readLine());
        target = st.nextToken();

        new Solver(source, target).solve();
    }
}

class Solver {

    String source;
    String target;

    public Solver(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public void solve() {
        int answer = 0;
        boolean rangeStarted = false;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == target.charAt(i)) {
                rangeStarted = false;
                continue;
            }
            if (rangeStarted == true) {
                continue;
            }
            if (rangeStarted == false) {
                answer++;
                rangeStarted = true;
            }
        }
        System.out.println(answer);
    }
}
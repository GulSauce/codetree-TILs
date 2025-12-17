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
    char[] sourceChars;
    char[] targetChars;

    public Solver(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public void solve() {
        sourceChars = new char[source.length()];
        targetChars = new char[source.length()];

        for (int i = 0; i < source.length(); i++) {
            sourceChars[i] = source.charAt(i);
            targetChars[i] = target.charAt(i);
        }

        int answer = 0;
        for (int i = sourceChars.length - 1; i >= 0; i--) {
            if (sourceChars[i] == targetChars[i]) {
                continue;
            }
            answer++;
            for (int j = 0; j <= i; j++) {
                if (sourceChars[j] == 'G') {
                    sourceChars[j] = 'H';
                } else if (sourceChars[j] == 'H') {
                    sourceChars[j] = 'G';
                }
            }
        }
        System.out.println(answer);
    }
}
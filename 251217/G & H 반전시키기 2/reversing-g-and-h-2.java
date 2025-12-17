import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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

    HashMap<Character, Character> reverseChar = new HashMap<>();

    public Solver(String source, String target) {
        this.source = source;
        this.target = target;
        this.reverseChar.put('G', 'H');
        this.reverseChar.put('H', 'G');
    }

    public void solve() {
        int pushCount = 0;
        for (int i = source.length() - 1; i >= 0; i--) {
            char currentChar = getCurrentChar(pushCount, source.charAt(i));
            if (currentChar == target.charAt(i)) {
                continue;
            }
            pushCount++;
        }
        System.out.println(pushCount);
    }

    private char getCurrentChar(int pushCount, char originChar) {
        if (pushCount % 2 == 0) {
            return originChar;
        }
        return reverseChar.get(originChar);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<String> parens = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            parens.add(st.nextToken());
        }

        br.close();

        new Solver(parens).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<String> parens;
    List<ParenInfo> parenInfos = new ArrayList<>();

    public Solver(List<String> parens) {
        this.parens = parens;
    }

    public void solve() {

        for (String paren : parens) {
            int openCount = 0;
            int closeCount = 0;
            for (int i = 0; i < paren.length(); i++) {
                if (paren.charAt(i) == '(') {
                    openCount++;
                } else if (paren.charAt(i) == ')') {
                    closeCount++;
                }
            }
            parenInfos.add(new ParenInfo(paren, openCount, closeCount));
        }

        Collections.sort(parenInfos, (a, b) -> {
            return Long.compare((long) b.openCount * a.closeCount,
                (long) a.openCount * b.closeCount);
        });

        long point = 0;
        long openSum = 0;
        for (ParenInfo parenInfo : parenInfos) {
            String cur = parenInfo.paren;

            long curOpenSum = 0;
            for (int i = 0; i < cur.length(); i++) {
                if (cur.charAt(i) == ')') {
                    point += curOpenSum;
                } else if (cur.charAt(i) == '(') {
                    curOpenSum++;
                }
            }

            point += openSum * parenInfo.closeCount;
            openSum += parenInfo.openCount;
        }

        System.out.println(point);
    }
}

class ParenInfo {

    String paren;
    int openCount;
    int closeCount;

    public ParenInfo(String paren, int openCount, int closeCount) {
        this.paren = paren;
        this.openCount = openCount;
        this.closeCount = closeCount;
    }
}
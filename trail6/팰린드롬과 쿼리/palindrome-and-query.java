import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, q;
        String s;
        List<Query> queries = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        q = toInt(st);

        st = new StringTokenizer(br.readLine());
        s = st.nextToken();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            queries.add(new Query(toInt(st), toInt(st)));
        }

        new Solver(s, queries).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    String target;
    List<Query> queries;

    int globalCenter = 0;
    int globalRight = 0;
    int[] palRadiusOf;
    int[] maxPalLengthOf;

    public Solver(String S, List<Query> queries) {
        this.target = insertPadding(S);
        this.queries = queries;
        this.palRadiusOf = new int[target.length()];
        this.maxPalLengthOf = new int[target.length()];
    }

    public void solve() {
        for (int i = 0; i < target.length(); i++) {
            int determinedR;
            if (globalRight < i) {
                determinedR = 0;
            } else {
                int mirrorIndex = globalCenter - (i - globalCenter);
                int mirrorR = palRadiusOf[mirrorIndex];
                determinedR = Math.min(mirrorR, globalRight - i);
            }

            int localLeft = i - determinedR;
            int localRight = i + determinedR;

            while (true) {
                if (localLeft < 0) {
                    break;
                }
                if (localRight >= target.length()) {
                    break;
                }
                if (target.charAt(localLeft) != target.charAt(localRight)) {
                    break;
                }
                localLeft--;
                localRight++;
            }
            localLeft++;
            localRight--;
            palRadiusOf[i] = localRight - i;
            if (globalRight < localRight) {
                globalRight = localRight;
                globalCenter = i;
            }
        }

        for (int i = 0; i < palRadiusOf.length; i++) {
            maxPalLengthOf[i] = (palRadiusOf[i] * 2 + 1) / 2;
        }

        for (Query query : queries) {
            int left = query.a * 2 - 1;
            int right = query.b * 2 - 1;
            int mid = (left + right) / 2;
            int length = maxPalLengthOf[mid];
            System.out.println(query.b - query.a + 1 <= length ? "Yes" : "No");
        }
    }

    private String insertPadding(String origin) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < origin.length(); i++) {
            sb.append('#');
            sb.append(origin.charAt(i));
        }
        sb.append('#');
        return sb.toString();
    }
}

class Query {

    int a;
    int b;

    public Query(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
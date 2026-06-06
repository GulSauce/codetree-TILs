import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        String c;
        String S;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = st.nextToken();

        st = new StringTokenizer(br.readLine());
        S = st.nextToken();

        new Solver(S, c).solve();
    }
}

class Solver {

    String target;
    String ignored;

    int globalCenter = 0;
    int globalRight = 0;
    int[] palRadiusOf;

    public Solver(String origin, String ignored) {
        this.target = insertPadding(origin);
        this.palRadiusOf = new int[target.length() + 1];
        this.ignored = ignored;
    }

    public void solve() {
        for (int i = 0; i < target.length(); i++) {
            int determinedRadius;
            if (i > globalRight) {
                determinedRadius = 0;
            } else {
                int mirrorIndex = globalCenter - (i - globalCenter);
                int mirrorRadius = palRadiusOf[mirrorIndex];
                determinedRadius = Math.min(mirrorRadius, globalRight - i);
            }

            int localLeft = i - determinedRadius;
            int localRight = i + determinedRadius;
            if (!(determinedRadius == 0 && target.charAt(i) == ignored.charAt(0))) {
                while (true) {
                    if (localLeft < 0) {
                        break;
                    }
                    if (localRight >= target.length()) {
                        break;
                    }
                    if (target.charAt(localLeft) == ignored.charAt(0)
                        || target.charAt(localRight) == ignored.charAt(0)) {
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
            }
            palRadiusOf[i] = localRight - i;
            if (localRight > globalRight) {
                globalRight = localRight;
                globalCenter = i;
            }
        }

        int answer = 0;
        for (int i = 0; i < target.length(); i++) {
            answer = Math.max(answer, (2 * palRadiusOf[i] + 1) / 2);
        }
        System.out.println(answer);
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
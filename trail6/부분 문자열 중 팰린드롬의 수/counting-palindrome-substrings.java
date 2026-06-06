import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String origin;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        origin = st.nextToken();

        new Solver(origin).solve();
    }
}

class Solver {

    String target;

    int globalCenter = 0;
    int globalRight = 0;
    int[] radiusOf;

    public Solver(String origin) {
        this.target = insertPadding(origin);
        this.radiusOf = new int[target.length()];
    }

    public void solve() {
        for (int i = 0; i < target.length(); i++) {
            int determineRadius;
            if (i > globalRight) {
                determineRadius = 0;
            } else {
                int mirrorIndex = globalCenter - (i - globalCenter);
                int mirrorRadius = radiusOf[mirrorIndex];
                determineRadius = Math.min(mirrorRadius, globalRight - i);
            }
            int localLeft = i - determineRadius;
            int localRight = i + determineRadius;
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
            radiusOf[i] = localRight - i;
            if (localRight > globalRight) {
                globalRight = localRight;
                globalCenter = i;
            }
        }

        long answer = 0;
        for (int i = 0; i < radiusOf.length; i++) {
            radiusOf[i] = radiusOf[i] * 2 + 1;
            radiusOf[i] /= 2;
        }
        for (int i = 0; i < radiusOf.length; i++) {
            if (i % 2 == 1) {
                answer += radiusOf[i] / 2 + 1;
            } else {
                answer += radiusOf[i] / 2;
            }
        }

        System.out.println(answer);
    }

    private String insertPadding(String target) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            sb.append('#');
            sb.append(target.charAt(i));
        }
        sb.append('#');
        return sb.toString();
    }
}
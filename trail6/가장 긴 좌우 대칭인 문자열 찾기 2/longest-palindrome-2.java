import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String target;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        target = st.nextToken();

        new Solver(target).solve();
    }
}

class Solver {

    String target;
    int centerIndex = 0;
    int rightIndex = 0;
    int[] radiusOf;

    public Solver(String original) {
        this.target = original;
        this.target = changeToOdd(original);
        this.radiusOf = new int[target.length()];
    }

    public void solve() {
        for (int i = 0; i < target.length(); i++) {
            int determinedRadius;
            if (i >= rightIndex) {
                determinedRadius = 0;
            } else {
                int mirrorIndex = centerIndex - (i - centerIndex);
                int mirrorPalRadius = radiusOf[mirrorIndex];
                determinedRadius = Math.min(mirrorPalRadius, rightIndex - i);
            }
            int localLeft = i - determinedRadius - 1;
            int localRight = i + determinedRadius + 1;
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
            radiusOf[i] = localRight - i - 1;
            rightIndex = localRight - 1;
            centerIndex = i;
        }

        int answer = 0;
        for (int i = 0; i < target.length(); i++) {
            answer = Math.max(answer, 2 * radiusOf[i] + 1);
        }
        System.out.println(answer / 2);
    }

    private String changeToOdd(String target) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            sb.append('#');
            sb.append(target.charAt(i));
        }
        sb.append('#');
        return sb.toString();
    }
}
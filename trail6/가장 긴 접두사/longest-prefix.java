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

    String targetOriginal;
    String article;

    public Solver(String targetOriginal) {
        this.targetOriginal = targetOriginal;
        this.article = reverse(targetOriginal);
    }

    public void solve() {
        int start = 0;
        int end = targetOriginal.length() - 1;

        int answer = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (isMatched(mid)) {
                answer = mid + 1;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private boolean isMatched(int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= end; i++) {
            sb.append(targetOriginal.charAt(i));
        }

        String target = sb.toString();
        int[] preSufLength = new int[targetOriginal.length()];

        int length = 0;
        int i = 1;
        while (true) {
            if (i == target.length()) {
                break;
            }
            if (target.charAt(i) == target.charAt(length)) {
                length++;
                preSufLength[i] = length;
                i++;
            } else if (length == 0) {
                i++;
            } else {
                length = preSufLength[length - 1];
            }
        }

        i = 0;
        int j = 0;
        while (true) {
            if (i == article.length()) {
                break;
            }
            if (target.charAt(j) == article.charAt(i)) {
                if (j == target.length() - 1) {
                    return true;
                }
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = preSufLength[j - 1];
            }
        }
        return false;
    }

    private String reverse(String target) {
        StringBuilder sb = new StringBuilder();
        for (int i = target.length() - 1; i >= 0; i--) {
            sb.append(target.charAt(i));
        }
        return sb.toString();
    }
}
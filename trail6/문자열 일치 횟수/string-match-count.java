import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        String T;
        String P;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        T = st.nextToken();

        st = new StringTokenizer(br.readLine());
        P = st.nextToken();

        new Solver(T, P).solve();
    }
}

class Solver {

    int[] preSufCount;
    String article;
    String pattern;

    public Solver(String article, String pattern) {
        this.article = setShiftedPattern(pattern);
        this.preSufCount = new int[article.length()];
        this.pattern = article;
    }

    public void solve() {
        int length = 0;
        int i = 1;
        while (true) {
            if (i == pattern.length()) {
                break;
            }
            if (pattern.charAt(length) == pattern.charAt(i)) {
                length++;
                preSufCount[i] = length;
                i++;
            } else if (length == 0) {
                i++;
            } else {
                length = preSufCount[length - 1];
            }
        }

        int answer = 0;
        i = 0;
        int j = 0;
        while (true) {
            if (i == article.length()) {
                break;
            }
            if (article.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    answer++;
                    i++;
                    j = preSufCount[j];
                } else {
                    i++;
                    j++;
                }
            } else if (j == 0) {
                i++;
            } else {
                j = preSufCount[j - 1];
            }
        }
        System.out.println(answer);
    }

    private String setShiftedPattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        sb.append(pattern);
        for (int i = 0; i < pattern.length() - 1; i++) {
            sb.append(pattern.charAt(i));
        }
        return sb.toString();
    }
}
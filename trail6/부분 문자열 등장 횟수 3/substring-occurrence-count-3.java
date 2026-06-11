import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String T;
        String P;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = st.nextToken();

        st = new StringTokenizer(br.readLine());
        P = st.nextToken();

        new Solver(T, P).solve();
    }
}

class Solver {

    String article;
    String pattern;
    int[] preSufLength;

    public Solver(String article, String pattern) {
        this.article = article;
        this.pattern = pattern;
        this.preSufLength = new int[pattern.length()];
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
                preSufLength[i] = length;
                i++;
            } else if (length == 0) {
                i++;
            } else {
                length = preSufLength[length - 1];
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
                    j = 0;
                } else {
                    i++;
                    j++;
                }
            } else if (j == 0) {
                i++;
            } else {
                j = preSufLength[j - 1];
            }
        }

        System.out.println(answer);
    }
}
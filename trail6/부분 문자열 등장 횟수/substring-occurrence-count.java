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

    int answer = 0;
    int[] preSufSameCount;

    String article;
    String target;

    public Solver(String article, String target) {
        this.article = article;
        this.target = target;
        this.preSufSameCount = new int[target.length()];
    }

    public void solve() {
        int length = 0;
        int i = 1;
        while (true) {
            if (i == target.length()) {
                break;
            }
            if (target.charAt(length) == target.charAt(i)) {
                length++;
                preSufSameCount[i] = length;
                i++;
            } else if (length == 0) {
                i++;
            } else {
                length = preSufSameCount[length - 1];
            }
        }

        i = 0;
        int j = 0;

        while (true) {
            if (i == article.length()) {
                break;
            }

            if (article.charAt(i) == target.charAt(j)) {
                if (j == target.length() - 1) {
                    answer++;
                    i++;
                    j = preSufSameCount[j];
                } else {
                    i++;
                    j++;
                }
            } else if (j == 0) {
                i++;
            } else {
                j = preSufSameCount[j - 1];
            }
        }

        System.out.println(answer);
    }
}
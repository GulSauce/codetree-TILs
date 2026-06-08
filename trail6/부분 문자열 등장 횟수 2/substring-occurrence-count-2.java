import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String T;
        int q;
        List<String> queries = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = st.nextToken();
        q = toInt(st);

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            queries.add(st.nextToken());
        }

        new Solver(T, queries).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int answer = 0;
    int[] preSufSameCount;

    String article;
    List<String> queries;

    public Solver(String article, List<String> queries) {
        this.article = article;
        this.preSufSameCount = new int[article.length()];
        this.queries = queries;
    }

    public void solve() {
        for (String query : queries) {
            kmp(query);
        }
    }

    private void kmp(String target) {
        Arrays.fill(preSufSameCount, 0);

        int length = 0;
        int i = 1;
        while (true) {
            if (i == article.length()) {
                break;
            }
            if (article.charAt(i) == article.charAt(length)) {
                length++;
                preSufSameCount[i] = length;
                i++;
            } else if (length == 0) {
                i++;
            } else {
                length = preSufSameCount[length - 1];
            }
        }

        int answer = 0;
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
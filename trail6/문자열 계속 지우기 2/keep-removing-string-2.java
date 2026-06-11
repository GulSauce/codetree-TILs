import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String A;
        String B;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = st.nextToken();

        st = new StringTokenizer(br.readLine());
        B = st.nextToken();

        new Solver(A, B).solve();
    }
}

class Solver {

    String article;
    String pattern;

    Stack<Status> statusStack = new Stack<>();

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

        i = 0;
        int j = 0;
        while (true) {
            if (i == article.length()) {
                break;
            }
            if (article.charAt(i) == pattern.charAt(j)) {
                statusStack.add(new Status(i, j));
                if (j == pattern.length() - 1) {
                    i++;
                    for (int index = 0; index < pattern.length(); index++) {
                        statusStack.pop();
                    }
                    if (!statusStack.isEmpty()) {
                        j = statusStack.peek().matchedPatternIndex + 1;
                    } else {
                        j = 0;
                    }
                } else {
                    i++;
                    j++;
                }
            } else if (j == 0) {
                statusStack.add(new Status(i, -1));
                i++;
            } else {
                j = preSufLength[j - 1];
            }
        }

        List<Status> reversed = new ArrayList<>();
        while (!statusStack.isEmpty()) {
            reversed.add(statusStack.pop());
        }
        reversed.sort((a, b) -> Integer.compare(a.articleIndex, b.articleIndex));
        for (Status status : reversed) {
            System.out.print(article.charAt(status.articleIndex));
        }
    }
}

class Status {

    int articleIndex;
    int matchedPatternIndex;

    public Status(int articleIndex, int matchedPatternIndex) {
        this.articleIndex = articleIndex;
        this.matchedPatternIndex = matchedPatternIndex;
    }
}
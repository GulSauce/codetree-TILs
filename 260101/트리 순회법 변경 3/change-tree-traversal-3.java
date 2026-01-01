import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Integer> inorderResult = new ArrayList<>();
        List<Integer> postorderResult = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inorderResult.add(toInt(st));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postorderResult.add(toInt(st));
        }

        new Solver(N, inorderResult, postorderResult).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int N;
    List<Integer> inorderResult;
    List<Integer> postorderResult;
    int[] numberToIndexes;

    public Solver(int n, List<Integer> inorderResult, List<Integer> postorderResult) {
        N = n;
        this.inorderResult = inorderResult;
        this.postorderResult = postorderResult;
        this.numberToIndexes = new int[n + 1];
    }

    public void solve() {
        for (int i = 0; i < N; i++) {
            numberToIndexes[inorderResult.get(i)] = i;
        }

        fillPreorderDAQ(new InorderArea(0, N - 1), new PostorderArea(0, N - 1));
        for (int number : toFillPreorderResults) {
            System.out.print(number + " ");
        }
    }

    List<Integer> toFillPreorderResults = new ArrayList<>();

    public void fillPreorderDAQ(InorderArea inorderArea, PostorderArea postorderArea) {
        if (inorderArea.end < inorderArea.start) {
            return;
        }

        int rootNumber = postorderResult.get(postorderArea.end);
        int inorderRootIndex = numberToIndexes[rootNumber];

        int leftSubtreeSize = inorderRootIndex - inorderArea.start;

        toFillPreorderResults.add(rootNumber);

        fillPreorderDAQ(
            new InorderArea(
                inorderArea.start,
                inorderRootIndex - 1
            ),
            new PostorderArea(
                postorderArea.start,
                postorderArea.start + leftSubtreeSize - 1
            )
        );

        fillPreorderDAQ(
            new InorderArea(
                inorderRootIndex + 1,
                inorderArea.end
            ),
            new PostorderArea(
                postorderArea.start + leftSubtreeSize,
                postorderArea.end - 1
            )
        );
    }
}

class InorderArea {

    int start;
    int end;

    public InorderArea(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class PostorderArea {

    int start;
    int end;

    public PostorderArea(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

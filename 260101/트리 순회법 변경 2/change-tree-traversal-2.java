import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Integer> preorderResult = new ArrayList<>();
        List<Integer> inorderResult = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            preorderResult.add(toInt(st));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inorderResult.add(toInt(st));
        }

        new Solver(N, preorderResult, inorderResult).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    List<Integer> postorderResult = new ArrayList<>();
    List<Integer> preorderResult;
    List<Integer> inorderResult;

    public Solver(int nodeCount, List<Integer> preorderResult, List<Integer> inorderResult) {
        this.nodeCount = nodeCount;
        this.preorderResult = preorderResult;
        this.inorderResult = inorderResult;
    }

    public void solve() {
        setAnswerTravelingPostorder(new PreSubtreeInfo(0, nodeCount - 1),
            new InSubtreeInfo(0, nodeCount - 1));
        for (int nodeNumber : postorderResult) {
            System.out.print(nodeNumber + " ");
        }
    }

    private void setAnswerTravelingPostorder(PreSubtreeInfo preSubtreeInfo,
        InSubtreeInfo inSubtreeInfo) {
        if (preSubtreeInfo.start > preSubtreeInfo.end) {
            return;
        }

        int rootNumber = preorderResult.get(preSubtreeInfo.start);
        int inorderRootIndex = inorderResult.indexOf(rootNumber);

        int leftSubtreeSize = inorderRootIndex - inSubtreeInfo.start;

        setAnswerTravelingPostorder(
            new PreSubtreeInfo(preSubtreeInfo.start + 1, preSubtreeInfo.start + leftSubtreeSize),
            new InSubtreeInfo(inSubtreeInfo.start, inSubtreeInfo.start + leftSubtreeSize)
        );

        setAnswerTravelingPostorder(
            new PreSubtreeInfo(preSubtreeInfo.start + leftSubtreeSize + 1, preSubtreeInfo.end),
            new InSubtreeInfo(inorderRootIndex + 1, inSubtreeInfo.end)
        );

        postorderResult.add(rootNumber);
    }
}

class InSubtreeInfo {

    int start;
    int end;

    public InSubtreeInfo(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class PreSubtreeInfo {

    int start;
    int end;


    public PreSubtreeInfo(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
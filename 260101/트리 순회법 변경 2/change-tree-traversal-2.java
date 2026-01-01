import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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
    Stack<Integer> answer = new Stack<>();
    List<Integer> preorderResult;
    List<Integer> inorderResult;

    public Solver(int nodeCount, List<Integer> preorderResult, List<Integer> inorderResult) {
        this.nodeCount = nodeCount;
        this.preorderResult = preorderResult;
        this.inorderResult = inorderResult;
    }

    public void solve() {
        setAnswerWithFindingRoot(new PreSubtreeInfo(0, nodeCount - 1),
            new InSubtreeInfo(0, nodeCount - 1));
        while (!answer.isEmpty()) {
            System.out.print(answer.pop() + " ");
        }
    }

    private void setAnswerWithFindingRoot(PreSubtreeInfo preSubtreeInfo,
        InSubtreeInfo inSubtreeInfo) {
        int rootNode = preorderResult.get(preSubtreeInfo.start);
        int inOrderRootIndex = -1;
        answer.add(rootNode);

        for (int i = inSubtreeInfo.start; i <= inSubtreeInfo.end; i++) {
            if (rootNode == inorderResult.get(i)) {
                inOrderRootIndex = i;
                break;
            }
        }
        int leftSubTreeSize = inOrderRootIndex - inSubtreeInfo.start;
        int rightSubTreeSize = inSubtreeInfo.end - inOrderRootIndex;
        int leftPreStart = preSubtreeInfo.start + 1;
        int leftPreEnd = leftPreStart + leftSubTreeSize - 1;
        PreSubtreeInfo leftPre = new PreSubtreeInfo(leftPreStart, leftPreEnd);
        PreSubtreeInfo rightPre = new PreSubtreeInfo(leftPreEnd + 1, preSubtreeInfo.end);

        int leftInStart = inSubtreeInfo.start + leftSubTreeSize - 1;
        int rightInStart = leftInStart + 2;
        InSubtreeInfo leftIn = new InSubtreeInfo(inSubtreeInfo.start, leftInStart);
        InSubtreeInfo rightIn = new InSubtreeInfo(rightInStart, inSubtreeInfo.end);
        if (rightSubTreeSize > 0) {
            setAnswerWithFindingRoot(rightPre, rightIn);
        }
        if (leftSubTreeSize > 0) {
            setAnswerWithFindingRoot(leftPre, leftIn);
        }
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
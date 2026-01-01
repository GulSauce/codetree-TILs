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

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            preorderResult.add(toInt(st));
        }

        new Solver(preorderResult).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int[] numberToIndexes;
    List<Integer> preorderResult;

    public Solver(List<Integer> preorderResult) {
        this.preorderResult = preorderResult;
        this.numberToIndexes = new int[1_000_000 + 1];
    }

    public void solve() {
        fillPostorderDAQ(0, preorderResult.size() - 1);
        for (int result : toFillPostorder) {
            System.out.println(result);
        }
    }

    List<Integer> toFillPostorder = new ArrayList<>();

    private void fillPostorderDAQ(int start, int end) {
        if (end < start) {
            return;
        }
        int rootNode = preorderResult.get(start);
        int leftSubtreeSize = 0;
        for (int i = start + 1; i <= end; i++) {
            if (preorderResult.get(i) < rootNode) {
                leftSubtreeSize++;
            }
            if (preorderResult.get(i) >= rootNode) {
                break;
            }
        }
        fillPostorderDAQ(start + 1, start + leftSubtreeSize);
        fillPostorderDAQ(start + leftSubtreeSize + 1, end);
        toFillPostorder.add(rootNode);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Integer> parentNumbers = new ArrayList<>();
        List<Operation> operations = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        parentNumbers.add(-1);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parentNumbers.add(toInt(st));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            operations.add(new Operation(toInt(st), toInt(st)));
        }

        new Solver(N, parentNumbers, operations).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    int[] dp;
    List<Integer> parentNumbers;
    List<Operation> operations;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int nodeCount, List<Integer> parentNumbers, List<Operation> operations) {
        this.nodeCount = nodeCount;
        this.parentNumbers = parentNumbers;
        this.operations = operations;
        this.dp = new int[nodeCount + 1];
    }

    public void solve() {
        initDP();
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int v = 2; v <= nodeCount; v++) {
            graph.get(parentNumbers.get(v)).add(v);
        }

        DPDFS(1);
        for (int v = 1; v <= nodeCount; v++) {
            System.out.print(dp[v] + " ");
        }
    }

    private void DPDFS(int cur) {
        int parent = parentNumbers.get(cur);
        if (parent != -1) {
            dp[cur] += dp[parent];
        }
        for (int child : graph.get(cur)) {
            DPDFS(child);
        }
    }

    private void initDP() {
        for (Operation operation : operations) {
            dp[operation.nodeNumber] = operation.value;
        }
    }
}

class Operation {

    int nodeNumber;
    int value;

    public Operation(int nodeNumber, int value) {
        this.nodeNumber = nodeNumber;
        this.value = value;
    }
}
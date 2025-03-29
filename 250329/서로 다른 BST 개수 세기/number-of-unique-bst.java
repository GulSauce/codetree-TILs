import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();

        new Solver(N).solve();
    }
}


class Solver {

    int targetNodeCount;
    int[] dp;

    public Solver(
        int N
    ) {
        this.targetNodeCount = N;
        this.dp = new int[N + 1];
    }

    public void solve() {
        initDP();
        calcDP();
        printAnswer();
    }

    private void printAnswer() {
        System.out.println(dp[targetNodeCount]);
    }

    private void calcDP() {
        for (int nodeCount = 2; nodeCount <= targetNodeCount; nodeCount++) {
            int treeCount = 0;
            int withoutRootNodeCount = nodeCount - 1;
            for (int leftNodeCount = 0; leftNodeCount <= withoutRootNodeCount; leftNodeCount++) {
                int rightNodeCount = withoutRootNodeCount - leftNodeCount;
                treeCount += dp[leftNodeCount] * dp[rightNodeCount];
            }
            dp[nodeCount] = treeCount;
        }
    }

    private void initDP() {
        dp[0] = 1;
        dp[1] = 1;
    }
}
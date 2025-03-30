import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int N, M;
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        A.add(-1);
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }
        B.add(-1);
        for (int i = 0; i < M; i++) {
            B.add(sc.nextInt());
        }
        sc.close();

        new Solver(N, M, A, B).solve();
    }
}

class Solver {

    int aIndex;
    int bIndex;

    int[][] dp;

    List<Integer> A;
    List<Integer> B;

    public Solver(
        int N,
        int M,
        List<Integer> A,
        List<Integer> B
    ) {
        this.aIndex = N;
        this.bIndex = M;
        this.A = A;
        this.B = B;
        this.dp = new int[N + 1][M + 1];
    }

    public void solve() {
        calcDP();
//        printDP();
        printAnswer();
    }

    private void printAnswer() {
        int i = aIndex;
        int j = bIndex;
        Stack<Integer> answer = new Stack<>();
        while (i > 0 && j > 0) {
            if (A.get(i).equals(B.get(j))) {
                answer.add(A.get(i));
                i--;
                j--;
                continue;
            }
            if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
                continue;
            }
            if (dp[i][j - 1] > dp[i - 1][j]) {
                j--;
                continue;
            }
            if (A.get(i) > B.get(j)) {
                i--;
                continue;
            }
            j--;
        }
        while (!answer.isEmpty()) {
            System.out.print(answer.pop() + " ");
        }
    }

    private void printDP() {
        for (int[] array : dp) {
            for (int value : array) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private void calcDP() {
        for (int i = 1; i <= aIndex; i++) {
            for (int j = 1; j <= bIndex; j++) {
                if (A.get(i).equals(B.get(j))) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
}

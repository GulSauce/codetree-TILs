import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        reverseList();
        calcDP();
        printAnswer();
    }


    private void printAnswer() {
        int i = aIndex;
        int j = bIndex;
        while (i > 0 && j > 0) {
            if (A.get(i).equals(B.get(j))) {
                System.out.print(A.get(i) + " ");
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


    private void reverseList() {
        List<Integer> reverseA = new ArrayList<>();
        reverseA.add(-1);
        List<Integer> reverseB = new ArrayList<>();
        reverseB.add(-1);

        for (int i = aIndex; i >= 1; i--) {
            reverseA.add(A.get(i));
        }
        for (int i = bIndex; i >= 1; i--) {
            reverseB.add(B.get(i));
        }
        this.A = reverseA;
        this.B = reverseB;
    }
}

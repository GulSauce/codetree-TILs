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

    NumberOriginMemo[][] numberOriginMemos;

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
        this.numberOriginMemos = new NumberOriginMemo[N + 1][M + 1];
    }

    public void solve() {
        reverse();
        initDP();
        calcDP();
        printAnswer();
    }

    private void printAnswer() {
        int i = aIndex;
        int j = bIndex;
        while (i > 0 && j > 0) {
            if (A.get(i).equals(B.get(j))) {
                System.out.print(A.get(i) + " ");
            }
            int nextI = numberOriginMemos[i][j].iOrigin;
            int nextJ = numberOriginMemos[i][j].jOrigin;
            i = nextI;
            j = nextJ;
        }
    }

    private void calcDP() {
        for (int i = 1; i <= aIndex; i++) {
            for (int j = 1; j <= bIndex; j++) {
                if (A.get(i).equals(B.get(j))) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    numberOriginMemos[i][j] = new NumberOriginMemo(A.get(i), i - 1, j - 1);
                    continue;
                }
                if (dp[i - 1][j] == dp[i][j - 1]) {
                    if (numberOriginMemos[i - 1][j].number < numberOriginMemos[i][j - 1].number) {
                        dp[i][j] = dp[i - 1][j];
                        numberOriginMemos[i][j] = new NumberOriginMemo(
                            numberOriginMemos[i - 1][j].number,
                            i - 1, j);
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        numberOriginMemos[i][j] = new NumberOriginMemo(
                            numberOriginMemos[i][j - 1].number,
                            i, j - 1);
                    }
                    continue;
                }
                if (dp[i - 1][j] < dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1];
                    numberOriginMemos[i][j] = new NumberOriginMemo(
                        numberOriginMemos[i][j - 1].number, i,
                        j - 1);
                    continue;
                }
                if (dp[i][j - 1] < dp[i - 1][j]) {
                    dp[i][j] = dp[i - 1][j];
                    numberOriginMemos[i][j] = new NumberOriginMemo(
                        numberOriginMemos[i - 1][j].number, i - 1,
                        j);
                }
            }
        }
    }

    private void initDP() {
        for (int i = 0; i <= aIndex; i++) {
            for (int j = 0; j <= bIndex; j++) {
                numberOriginMemos[i][j] = new NumberOriginMemo(-1, 0, 0);
            }
        }
    }

    private void reverse() {
        List<Integer> reverseA = new ArrayList<>();
        List<Integer> reverseB = new ArrayList<>();

        reverseA.add(-1);
        for (int i = aIndex; i >= 1; i--) {
            reverseA.add(A.get(i));
        }
        reverseB.add(-1);
        for (int i = bIndex; i >= 1; i--) {
            reverseB.add(B.get(i));
        }

        A = reverseA;
        B = reverseB;
    }
}

class NumberOriginMemo {

    int number;
    int iOrigin;
    int jOrigin;

    public NumberOriginMemo(
        int number,
        int iOrigin,
        int jOrigin
    ) {
        this.number = number;
        this.iOrigin = iOrigin;
        this.jOrigin = jOrigin;
    }
}


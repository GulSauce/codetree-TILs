import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, K;
        int[] candyCounts = new int[101];

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int candyCount = sc.nextInt();
            int basket = sc.nextInt();
            candyCounts[basket] += candyCount;
        }
        sc.close();

        new Solver(K, candyCounts).solve();
    }
}

class Solver {
    int dist;
    int[] candyCounts;

    public Solver(
            int K,
            int[] candyCounts
    ) {
        this.dist = K;
        this.candyCounts = candyCounts;
    }

    public void solve() {
        int answer = 0;
        for (int c = 0; c < candyCounts.length; c++) {
            int curAnswer = 0;
            if (isOutOfRange(c - dist)) {
                continue;
            }
            if (isOutOfRange(c + dist)) {
                continue;
            }
            for (int i = c - dist; i <= c + dist; i++) {
                curAnswer += candyCounts[i];
            }
            answer = Math.max(answer, curAnswer);
        }
        System.out.println(answer);
    }

    private boolean isOutOfRange(int index) {
        return index < 0 || candyCounts.length <= index;
    }
}
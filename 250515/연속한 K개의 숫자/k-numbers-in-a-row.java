import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, K, B;
        ArrayList<Integer> notExistNumbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        B = sc.nextInt();
        for (int i = 0; i < B; i++) {
            notExistNumbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(N, K, notExistNumbers).solve();
    }
}

class Solver {

    int maxNumber;
    int consecutiveCount;
    boolean[] exist;
    int[] prefixSum;
    ArrayList<Integer> notExistNumbers;

    public Solver(
        int N,
        int K,
        ArrayList<Integer> notExistNumbers
    ) {
        this.prefixSum = new int[N + 1];
        this.exist = new boolean[N + 1];
        this.maxNumber = N;
        this.consecutiveCount = K;
        this.notExistNumbers = notExistNumbers;
    }

    public void solve() {
        Arrays.fill(exist, true);
        for (Integer notExistNumber : notExistNumbers) {
            exist[notExistNumber] = false;
        }
        for (int i = 1; i <= maxNumber; i++) {
            if (exist[i]) {
                prefixSum[i] = prefixSum[i - 1];
            } else {
                prefixSum[i] = 1 + prefixSum[i - 1];
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = consecutiveCount; i <= maxNumber; i++) {
            answer = Math.min(answer, prefixSum[i] - prefixSum[i - consecutiveCount]);
        }
        System.out.println(answer);
    }

}
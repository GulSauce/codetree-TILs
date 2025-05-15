import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, K;
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(-1);

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(N, K, numbers).solve();
    }
}

class Solver {
    int consecutiveCount;

    int[] prefixSum;
    ArrayList<Integer> numbers;

    public Solver(
            int numberCount,
            int consecutiveCount,
            ArrayList<Integer> numbers
    ) {
        this.prefixSum = new int[numberCount + 1];
        this.consecutiveCount = consecutiveCount;
        this.numbers = numbers;
    }

    public void solve() {
        setPrefixSum();
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= numbers.size() - consecutiveCount; i++) {
            int curSum = prefixSum[i + consecutiveCount - 1] - prefixSum[i - 1];
            answer = Math.max(answer, curSum);
        }
        System.out.println(answer);
    }

    private void setPrefixSum() {
        for (int i = 1; i < numbers.size(); i++) {
            prefixSum[i] = numbers.get(i) + prefixSum[i - 1];
        }
    }
}
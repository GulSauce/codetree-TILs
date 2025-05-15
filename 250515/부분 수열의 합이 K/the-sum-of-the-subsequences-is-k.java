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

        new Solver(K, numbers).solve();
    }
}

class Solver {

    int targetSum;
    int[] prefixSum;
    ArrayList<Integer> numbers;

    public Solver(
        int K,
        ArrayList<Integer> numbers
    ) {
        this.targetSum = K;
        this.numbers = numbers;
        this.prefixSum = new int[numbers.size()];
    }

    public void solve() {
        setPrefixSum();
        int answer = 0;
        for (int i = 1; i < numbers.size(); i++) {
            for (int j = 0; j < i; j++) {
                int subSum = prefixSum[i] - prefixSum[j];
                if (subSum == targetSum) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private void setPrefixSum() {
        for (int i = 1; i < numbers.size(); i++) {
            prefixSum[i] = numbers.get(i) + prefixSum[i - 1];
        }
    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, K;
        List<Integer> numbers = new ArrayList<>();

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
    int maxDiff;
    int numbersIndex;
    List<Integer> numbers;

    public Solver(
            int K,
            List<Integer> numbers
    ) {
        this.maxDiff = K;
        this.numbersIndex = numbers.size() - 1;
        this.numbers = numbers;
    }

    public void solve() {
        Collections.sort(numbers);
        int answer = 0;
        for (int i = 0; i <= numbersIndex; i++) {
            int selectedNumber = 0;
            int curMin = numbers.get(i);
            for (int j = i; j <= numbersIndex; j++) {
                if (curMin + maxDiff < numbers.get(j)) {
                    break;
                }
                selectedNumber++;
                answer = Math.max(answer, selectedNumber);
            }
        }
        System.out.println(answer);
    }
}
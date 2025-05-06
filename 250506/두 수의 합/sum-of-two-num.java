import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, K;
        ArrayList<Integer> numbers = new ArrayList<>();

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
    ArrayList<Integer> numbers;

    public Solver(
            int K,
            ArrayList<Integer> numbers
    ) {
        this.targetSum = K;
        this.numbers = numbers;
    }

    public void solve() {
        int answer = 0;
        HashMap<Integer, Integer> numberCountMap = new HashMap<>();
        for (Integer number : numbers) {
            int targetDiff = targetSum - number;
            if (numberCountMap.containsKey(targetDiff)) {
                answer += numberCountMap.get(targetDiff);
            }

            if (numberCountMap.containsKey(number)) {
                numberCountMap.put(number, numberCountMap.get(number) + 1);
            } else {
                numberCountMap.put(number, 1);
            }
        }
        System.out.println(answer);
    }
}
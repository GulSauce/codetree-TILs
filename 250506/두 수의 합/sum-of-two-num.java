import java.util.ArrayList;
import java.util.Collections;
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
        HashMap<Integer, Integer> numberCountMap = new HashMap<>();
        ArrayList<Integer> queryNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            if (numberCountMap.containsKey(number)) {
                numberCountMap.put(number, numberCountMap.get(number) + 1);
            } else {
                queryNumbers.add(number);
                numberCountMap.put(number, 1);
            }
        }
        int answer = 0;
        Collections.sort(numbers);
        for (int queryNumber : queryNumbers) {
            int targetDiff = targetSum - queryNumber;
            if (!numberCountMap.containsKey(targetDiff)) {
                continue;
            }
            if (targetDiff < queryNumber) {
                continue;
            }
            if (queryNumber == targetDiff) {
                if (!numberCountMap.containsKey(targetDiff)) {
                    continue;
                }
                int numberCount = numberCountMap.get(targetDiff);
                if (numberCount == 1) {
                    continue;
                }
                answer += numberCount * (numberCount - 1) / 2;
                continue;
            }
            int targetDiffNumberCount = numberCountMap.get(targetDiff);
            int queryNumberCount = numberCountMap.get(queryNumber);
            answer += queryNumberCount * targetDiffNumberCount;
        }
        System.out.println(answer);
    }
}
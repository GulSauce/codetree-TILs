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

        new Solver(K, numbers).solve();
    }
}

class Solver {
    int targetSum;
    int numbersIndex;
    ArrayList<Integer> numbers;

    public Solver(
            int K,
            ArrayList<Integer> numbers
    ) {
        this.targetSum = K;
        this.numbersIndex = numbers.size() - 1;
        this.numbers = numbers;
    }

    public void solve() {
        HashMap<Integer, Integer> numberCountHashMap = new HashMap<>();

        for (Integer number : numbers) {

            if (numberCountHashMap.containsKey(number)) {
                numberCountHashMap.put(number, numberCountHashMap.get(number) + 1);
            } else {
                numberCountHashMap.put(number, 1);
            }
        }

        int answer = 0;
        for (int i = 0; i <= numbersIndex; i++) {
            numberCountHashMap.put(numbers.get(i), numberCountHashMap.get(numbers.get(i)) - 1);
            for (int j = 0; j < i; j++) {
                int targetDiff = targetSum - numbers.get(i) - numbers.get(j);
                if (numberCountHashMap.containsKey(targetDiff)) {
                    answer += numberCountHashMap.get(targetDiff);
                }
            }
        }
        System.out.println(answer);
    }
}
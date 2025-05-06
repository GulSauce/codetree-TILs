import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M;
        List<Integer> numbers = new ArrayList<>();
        List<Integer> queryNumbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        for (int i = 0; i < M; i++) {
            queryNumbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(numbers, queryNumbers).solve();
    }
}

class Solver {
    List<Integer> numbers;
    List<Integer> queryNumbers;

    public Solver(
            List<Integer> numbers,
            List<Integer> queryNumbers
    ) {
        this.numbers = numbers;
        this.queryNumbers = queryNumbers;
    }

    public void solve() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Integer number : numbers) {
            if (hashMap.containsKey(number)) {
                int numberCount = hashMap.get(number);
                hashMap.put(number, numberCount + 1);
            } else {
                hashMap.put(number, 1);
            }
        }

        for (Integer queryNumber : queryNumbers) {
            if (hashMap.containsKey(queryNumber)) {
                System.out.print(hashMap.get(queryNumber) + " ");
            } else {
                System.out.print(0 + " ");
            }
        }
    }
}
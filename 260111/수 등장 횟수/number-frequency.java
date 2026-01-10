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
            hashMap.compute(number, (key, value) -> value == null ? 1 : value + 1);
        }

        for (Integer queryNumber : queryNumbers) {
            System.out.print(hashMap.getOrDefault(queryNumber, 0) + " ");
        }
    }
}
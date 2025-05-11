import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> queryNumbers = new ArrayList<>();

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
    TreeSet<Integer> numberTreeSet;
    ArrayList<Integer> numbers;
    ArrayList<Integer> queryNumbers;

    public Solver(
            ArrayList<Integer> numbers,
            ArrayList<Integer> queryNumbers
    ) {
        this.numbers = numbers;
        this.queryNumbers = queryNumbers;
    }

    public void solve() {
        numberTreeSet = new TreeSet<>(numbers);
        for (Integer queryNumber : queryNumbers) {
            Integer floorValue = numberTreeSet.floor(queryNumber);
            if (floorValue == null) {
                System.out.println(-1);
                continue;
            }
            System.out.println(floorValue);
            numberTreeSet.remove(floorValue);
        }
    }
}
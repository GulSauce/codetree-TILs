import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        List<Integer> numbers;

        public Solver(
            List<Integer> numbers
        ) {
            this.numbers = numbers;
        }

        public void solve() {
            Collections.sort(numbers);
            int A = numbers.get(0);
            int B = numbers.get(1);
            int ABC = numbers.get(numbers.size() - 1);
            int C = ABC - A - B;
            System.out.print(A + " " + B + " " + C);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            numbers.add(sc.nextInt());
        }
        new Solver(numbers).solve();
    }
}

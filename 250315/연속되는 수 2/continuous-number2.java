import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int numbersIndex;
        List<Integer> numbers;

        public Solver(
            int N,
            List<Integer> numbers
        ) {
            this.numbersIndex = N - 1;
            this.numbers = numbers;
        }

        public void solve() {
            int maxLength = 1;
            int currentLength = 1;
            for (int i = 1; i <= numbersIndex; i++) {
                if (numbers.get(i - 1).equals(numbers.get(i))) {
                    currentLength++;
                } else {
                    currentLength = 1;
                }
                maxLength = Math.max(maxLength, currentLength);

            }
            System.out.println(maxLength);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        new Solver(N, numbers).solve();
    }
}
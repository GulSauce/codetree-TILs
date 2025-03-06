import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        List<Integer> numbers;
        List<Integer> negatives = new ArrayList<>();
        List<Integer> positives = new ArrayList<>();

        public Solver(
            List<Integer> numbers
        ) {
            this.numbers = numbers;
        }

        public void solve() {
            setNegatives();
            setPositives();
            Collections.sort(negatives);
            Collections.sort(positives);

            int answer = Integer.MIN_VALUE;
            if (3 <= negatives.size()) {
                int max3NegativesMul =
                    negatives.get(negatives.size() - 1) * negatives.get(negatives.size() - 2)
                        * negatives.get(negatives.size() - 3);
                answer = Math.max(answer, max3NegativesMul);
            }
            if (2 <= negatives.size() && 1 <= positives.size()) {
                int max2Negatives1PositiveMul =
                    negatives.get(0) * negatives.get(1)
                        * positives.get(positives.size() - 1);
                answer = Math.max(answer, max2Negatives1PositiveMul);
            }
            if (1 <= negatives.size() && 2 <= positives.size()) {
                int max1Negatives2PositiveMul =
                    negatives.get(negatives.size() - 1) * positives.get(0)
                        * positives.get(1);
                answer = Math.max(answer, max1Negatives2PositiveMul);
            }
            if (3 <= positives.size()) {
                int max3PositiveMul =
                    positives.get(positives.size() - 1) * positives.get(positives.size() - 2)
                        * positives.get(positives.size() - 3);
                answer = Math.max(answer, max3PositiveMul);
            }
            System.out.println(answer);
        }

        private void setNegatives() {
            for (int value : numbers) {
                if (0 <= value) {
                    continue;
                }
                negatives.add(value);
            }
        }

        private void setPositives() {
            for (int value : numbers) {
                if (value < 0) {
                    continue;
                }
                positives.add(value);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        new Solver(numbers).solve();
    }
}
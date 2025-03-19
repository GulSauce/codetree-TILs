import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int result = 0;
        int numbersLength;
        List<Integer> numbers = new ArrayList<>();

        public Solver(
            int N
        ) {
            this.numbersLength = N;
        }

        public void solve() {
            makeNumber();
            System.out.println(result);
        }

        private void makeNumber() {
            if (numbers.size() == numbersLength) {
                if (!isBeautifulNumber()) {
                    return;
                }
                result++;
                return;
            }
            for (int i = 1; i <= 4; i++) {
                numbers.add(i);
                makeNumber();
                numbers.remove(numbers.size() - 1);
            }
        }

        private boolean isBeautifulNumber() {
            for (int i = 0; i < numbers.size(); i += numbers.get(i)) {
                if (numbers.size() < i + numbers.get(i)) {
                    return false;
                }
                for (int j = i + 1; j < i + numbers.get(i); j++) {
                    if (!numbers.get(j - 1).equals(numbers.get(j))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        new Main.Solver(N).solve();
    }
}
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

            int[] eachNumberCounts = new int[5];

            int firstNumber = numbers.get(0);
            eachNumberCounts[firstNumber]++;

            for (int i = 1; i < numbers.size(); i++) {
                int currentNumber = numbers.get(i);
                int prevNumber = numbers.get(i - 1);
                if (currentNumber != prevNumber) {
                    if (eachNumberCounts[prevNumber] != prevNumber) {
                        return false;
                    }
                    eachNumberCounts[prevNumber] = 0;
                    eachNumberCounts[currentNumber] = 1;
                    continue;
                }
                if (currentNumber < eachNumberCounts[currentNumber] + 1) {
                    eachNumberCounts[currentNumber] = 1;
                    continue;
                }
                eachNumberCounts[currentNumber]++;
            }

            for (int i = 1; i <= 4; i++) {
                if (eachNumberCounts[i] != i && eachNumberCounts[i] != 0) {
                    return false;
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
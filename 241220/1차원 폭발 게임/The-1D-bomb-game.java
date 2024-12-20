import java.util.*;

public class Main {
    private static class Solver {
        private List<Integer> bombNumbers;
        private final int minConsecutive;

        public Solver(List<Integer> bombNumbers, int minConsecutive) {
            this.bombNumbers = new ArrayList<>(bombNumbers);
            this.minConsecutive = minConsecutive;
        }

        public void solve() {
            boolean exploded;
            do {
                exploded = explodeBombs();
            } while (exploded);
            printResult();
        }

        private boolean explodeBombs() {
            List<Integer> updatedBombs = new ArrayList<>();
            int i = 0;
            boolean explodedThisRound = false;

            while (i < bombNumbers.size()) {
                int currentNumber = bombNumbers.get(i);
                int count = 1;
                int j = i + 1;

                while (j < bombNumbers.size() && bombNumbers.get(j).equals(currentNumber)) {
                    count++;
                    j++;
                }

                if (count >= minConsecutive) {
                    explodedThisRound = true;
                } else {
                    for (int k = i; k < j; k++) {
                        updatedBombs.add(bombNumbers.get(k));
                    }
                }
                i = j;
            }

            bombNumbers = updatedBombs;
            return explodedThisRound;
        }

        private void printResult() {
            System.out.println(bombNumbers.size());
            for (int number : bombNumbers) {
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }

        Solver solver = new Solver(numbers, M);
        solver.solve();
    }
}
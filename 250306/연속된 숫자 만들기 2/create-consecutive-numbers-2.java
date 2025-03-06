import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        List<Integer> positions;

        public Solver(
            List<Integer> positions
        ) {
            this.positions = positions;
        }

        public void solve() {
            Collections.sort(positions);
            if (isConsecutive()) {
                System.out.println(0);
            }
            if (isDistOne()) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }

        public boolean isDistOne() {
            return positions.get(0) + 2 == positions.get(1) ||
                positions.get(1) + 2 == positions.get(2);
        }

        private boolean isConsecutive() {
            return positions.get(0) + 1 == positions.get(1)
                && positions.get(1) + 1 == positions.get(2);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            positions.add(sc.nextInt());
        }
        new Solver(positions).solve();
    }
}
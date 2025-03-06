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
            int maxDistance = getMaxDistance();
            System.out.println(maxDistance-1);
        }

        private int getMaxDistance() {
            return Math.max(positions.get(1) - positions.get(0),
                positions.get(2) - positions.get(1));
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
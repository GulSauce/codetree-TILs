import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int peopleIndex;
        int wifiDist;
        final int NOT_FOUND = -1;
        final int NOT_EXIST = 0;
        List<Integer> people;


        public Solver(
            int n,
            int m,
            List<Integer> people
        ) {
            this.peopleIndex = n - 1;
            this.wifiDist = m;
            this.people = people;
        }

        public void solve() {
            int target = getTarget(0);
            int wifiCount = 1;
            if (target == NOT_FOUND) {
                wifiCount = 0;
            }
            while (true) {
                target = getTarget(target + 2 * wifiDist + 1);
                if (target == NOT_FOUND) {
                    break;
                }
                wifiCount++;
            }
            System.out.println(wifiCount);
        }

        private int getTarget(int start) {
            for (int i = start; i <= peopleIndex; i++) {
                if (people.get(i) == NOT_EXIST) {
                    continue;
                }
                return i;
            }
            return NOT_FOUND;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            people.add(sc.nextInt());
        }
        new Solver(n, m, people).solve();
    }
}
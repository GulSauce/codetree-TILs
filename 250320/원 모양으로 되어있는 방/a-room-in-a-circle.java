import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int answer = Integer.MAX_VALUE;
        int maxPeopleLimitsCount;
        List<Integer> maxPeopleLimits;

        public Solver(
            int N,
            List<Integer> maxPeopleLimits
        ) {
            this.maxPeopleLimitsCount = N;
            this.maxPeopleLimits = maxPeopleLimits;
        }

        public void solve() {
            for (int startRoom = 1; startRoom <= maxPeopleLimitsCount; startRoom++) {
                int distSum = getDistSum(startRoom);
                answer = Math.min(answer, distSum);
            }
            System.out.println(answer);
        }

        private int getDistSum(int startRoom) {
            int distSum = 0;
            int prevElapsedDist = 0;
            int prevIndex = startRoom;
            while (true) {
                int currentIndex = prevIndex + 1;
                int currentElapsedDist = prevElapsedDist + 1;
                if (currentElapsedDist == maxPeopleLimitsCount) {
                    break;
                }
                if (maxPeopleLimitsCount < currentIndex) {
                    currentIndex = 1;
                }
                distSum += maxPeopleLimits.get(currentIndex) * currentElapsedDist;
                prevElapsedDist = currentElapsedDist;
                prevIndex = currentIndex;
            }
            return distSum;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> maxPeopleLimits = new ArrayList<>();
        maxPeopleLimits.add(-1);
        for (int i = 0; i < N; i++) {
            maxPeopleLimits.add(sc.nextInt());
        }
        sc.close();

        new Solver(N, maxPeopleLimits).solve();
    }
}
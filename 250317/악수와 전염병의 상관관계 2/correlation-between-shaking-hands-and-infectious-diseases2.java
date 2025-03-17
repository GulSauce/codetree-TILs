import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int programmersNumber;
        int maxInfectCount;
        int firstInfectedProgrammer;


        boolean[] isInfected;
        int[] handShakeCounts;
        List<HandShake> handShakes;

        public Solver(
            int N,
            int K,
            int P,
            List<HandShake> handShakes
        ) {
            this.programmersNumber = N;
            this.maxInfectCount = K;
            this.firstInfectedProgrammer = P;
            this.handShakes = handShakes;
            this.isInfected = new boolean[N + 1];
            this.handShakeCounts = new int[N + 1];
        }

        public void solve() {
            simulate();
            printAnswer();
        }

        private void printAnswer() {
            for (int i = 1; i < isInfected.length; i++) {
                if (isInfected[i]) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }
        }


        private void simulate() {
            Collections.sort(handShakes);
            isInfected[firstInfectedProgrammer] = true;
            for (HandShake handShake : handShakes) {
                int programmer1 = handShake.programmer1;
                int programmer2 = handShake.programmer2;
                if (!isInfected[programmer1] && !isInfected[programmer2]) {
                    continue;
                }
                if (isInfected[programmer1] && isInfected[programmer2]) {
                    handShakeCounts[programmer1]++;
                    handShakeCounts[programmer2]++;
                }
                if (isInfected[programmer1] && !isInfected[programmer2]) {
                    if (maxInfectCount <= handShakeCounts[programmer1]) {
                        continue;
                    }
                    handShakeCounts[programmer1]++;
                    isInfected[programmer2] = true;
                }
                if (isInfected[programmer2] && !isInfected[programmer1]) {
                    if (maxInfectCount <= handShakeCounts[programmer2]) {
                        continue;
                    }
                    handShakeCounts[programmer2]++;
                    isInfected[programmer1] = true;
                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int P = sc.nextInt();
        int T = sc.nextInt();
        List<HandShake> handShakes = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            int t = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            handShakes.add(new HandShake(t, x, y));
        }
        new Solver(N, K, P, handShakes).solve();
    }

    private static class HandShake implements Comparable<HandShake> {

        int time;
        int programmer1;
        int programmer2;

        @Override
        public int compareTo(HandShake handShake) {
            return this.time - handShake.time;
        }

        public HandShake(
            int t,
            int x,
            int y
        ) {
            this.time = t;
            this.programmer1 = x;
            this.programmer2 = y;
        }
    }
}
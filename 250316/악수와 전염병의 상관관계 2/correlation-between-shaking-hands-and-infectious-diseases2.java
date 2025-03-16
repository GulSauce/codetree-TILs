import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int programmersNumber;
        int maxInfectCount;
        int target;

        final int MAX_PROGRAMMER_NUMBER = 100;
        final int MAX_TIME = 250;

        boolean[] isInfected = new boolean[MAX_PROGRAMMER_NUMBER + 1];
        int[] infectCount = new int[MAX_PROGRAMMER_NUMBER + 1];
        int[][] handShakeHistory = new int[MAX_PROGRAMMER_NUMBER + 1][MAX_TIME + 1];

        List<InfectInfo> infectInfos = new ArrayList<>();
        List<HandShake> handShakes;

        public Solver(
            int N,
            int K,
            int P,
            List<HandShake> handShakes
        ) {
            this.programmersNumber = N;
            this.maxInfectCount = K;
            this.target = P;
            this.handShakes = handShakes;
        }

        public void solve() {
            memoOnHandShakeHistory();
            infectInfos.add(new InfectInfo(0, target));
            while (!infectInfos.isEmpty()) {
                List<InfectInfo> nextInfectInfos = new ArrayList<>();
                for (InfectInfo infectInfo : infectInfos) {
                    nextInfectInfos = getInfectInfos(infectInfo.time, infectInfo.programmer);
                }
                infectInfos = nextInfectInfos;
            }
            printAnswer();
        }

        private void printAnswer() {
            for (int i = 1; i <= programmersNumber; i++) {
                if (isInfected[i]) {
                    System.out.print(1);
                    continue;
                }
                System.out.print(0);
            }
        }

        private List<InfectInfo> getInfectInfos(int time, int target) {
            List<InfectInfo> infectInfos = new ArrayList<>();
            isInfected[target] = true;
            for (int i = time; i <= MAX_TIME; i++) {
                int partner = handShakeHistory[target][i];
                if (partner == 0) {
                    continue;
                }
                if (maxInfectCount < infectCount[target] + 1) {
                    continue;
                }

                infectCount[target]++;
                infectInfos.add(new InfectInfo(i, partner));
                if (isInfected[partner]) {
                    continue;
                }
                isInfected[partner] = true;
            }
            return infectInfos;
        }

        private void memoOnHandShakeHistory() {
            for (HandShake handShake : handShakes) {
                handShakeHistory[handShake.programmer1][handShake.time] = handShake.programmer2;
                handShakeHistory[handShake.programmer2][handShake.time] = handShake.programmer1;
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

    private static class InfectInfo {

        int time;
        int programmer;

        public InfectInfo(
            int time,
            int programmer
        ) {
            this.time = time;
            this.programmer = programmer;
        }
    }

    private static class HandShake {

        int time;
        int programmer1;
        int programmer2;

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
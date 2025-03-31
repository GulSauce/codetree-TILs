import java.util.Scanner;

public class Main {

    private static class Solver {

        int targetMeter;

        public Solver(
            int X
        ) {
            this.targetMeter = X;
        }

        public void solve() {
            int answer = getAnswer();
            System.out.println(answer);
        }

        private int getAnswer() {
            int currentV = 1;
            int currentT = 1;
            while (true) {
                if (targetMeter - currentV == 0) {
                    break;
                }
                targetMeter -= currentV;
                int nextV;
                if (canIncrease(currentV)) {
                    nextV = currentV + 1;
                } else if (canMaintain(currentV)) {
                    nextV = currentV;
                } else {
                    nextV = currentV - 1;
                }
                currentV = nextV;
                int nextT = currentT + 1;
                currentT = nextT;
            }
            return currentT;
        }

        private boolean canIncrease(int v) {
            int nextV = v + 1;
            return (nextV + 1) * (nextV + 2) / 2 <= targetMeter;
        }

        private boolean canMaintain(int v) {
            return (v + 1) * v / 2 <= targetMeter;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        new Solver(X).solve();
    }
}
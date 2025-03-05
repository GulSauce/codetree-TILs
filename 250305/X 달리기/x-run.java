import java.util.Scanner;

public class Main {

    private static class Solver {

        int targetLength;
        int maxIncreaseDecrease;
        int maxIncreaseDecreaseTime;

        public Solver(
            int X
        ) {
            this.targetLength = X;
        }

        public void solve() {
            getMaxIncreaseDecrease();
            int remain = targetLength - maxIncreaseDecrease;
            System.out.println(maxIncreaseDecreaseTime + remain);
        }

        private void getMaxIncreaseDecrease() {
            int t = 0;
            int length = 0;
            while (true) {
                int nextTime = t + 2;
                int next = nextTime * nextTime + nextTime;
                if (targetLength < next) {
                    break;
                }
                length = next;
                t = nextTime;
            }
            this.maxIncreaseDecrease = length;
            this.maxIncreaseDecreaseTime = t;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();

        new Solver(X).solve();
    }
}
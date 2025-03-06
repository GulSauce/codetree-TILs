import java.util.Scanner;

public class Main {

    private static class Solver {

        int targetLength;
        int increaseDecrease;
        int increaseDecreaseTime;

        int remain;
        int remainTime;

        public Solver(
            int X
        ) {
            this.targetLength = X;
        }

        public void solve() {
            getMaxIncreaseDecrease();
            this.remain = targetLength - increaseDecrease;
            getRemainTime();
            System.out.println(increaseDecreaseTime + remainTime);
        }

        private void getRemainTime() {
            int speed = increaseDecreaseTime / 2;
            int t = 0;
            while (true) {
                int nextTime = t + 1;
                if (this.remain - speed < 0) {
                    speed--;
                    continue;
                }
                if (speed < 1) {
                    break;
                }
                this.remain -= speed;
                t = nextTime;
            }
            this.remainTime = t;
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
            this.increaseDecrease = length;
            this.increaseDecreaseTime = t;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();

        new Solver(X).solve();
    }
}
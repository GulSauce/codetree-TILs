import java.util.Scanner;

public class Main {

    private static class Solver {

        int day;
        int hour;
        int minute;

        final int START_DAY = 11;
        final int START_HOUR = 11;
        final int START_MINUTE = 11;

        public Solver(
            int A,
            int B,
            int C
        ) {
            this.day = A;
            this.hour = B;
            this.minute = C;
        }

        public void solve() {
            int dayDiff = day - START_DAY;
            int hourDiff = hour - START_HOUR;
            int minuteDiff = minute - START_MINUTE;

            int totalMinuteDiff = dayDiff * 24 * 60
                + hourDiff * 60 + minuteDiff;
            printAnswer(totalMinuteDiff);
        }

        private void printAnswer(int totalMinuteDiff) {
            if (totalMinuteDiff < 0) {
                System.out.println(-1);
                return;
            }
            System.out.println(totalMinuteDiff);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        sc.close();
        new Solver(A, B, C).solve();
    }
}
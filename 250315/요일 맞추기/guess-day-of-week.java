import java.util.Scanner;

public class Main {

    private static class Solver {

        CustomDate mondayDate;
        CustomDate targetDate;

        private final String[] dayToString = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        public Solver(
            CustomDate mondayDate,
            CustomDate targetDate
        ) {
            this.mondayDate = mondayDate;
            this.targetDate = targetDate;
        }

        public void solve() {
            int dayDiff = mondayDate.getTotalDayDiffTo(targetDate);
            int dayDiffMod7 = Math.floorMod(dayDiff, 7);
            printAnswer(dayDiffMod7);
        }

        private void printAnswer(int dayDiff) {
            System.out.println(dayToString[dayDiff]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m1 = sc.nextInt();
        int d1 = sc.nextInt();
        int m2 = sc.nextInt();
        int d2 = sc.nextInt();
        sc.close();

        new Main.Solver(new CustomDate(m1, d1), new CustomDate(m2, d2)).solve();
    }

    private static class CustomDate {

        int month;
        int day;

        private static final int[] dayPerMonth = {0, 31, 28, 31, 30, 31,
            30, 31, 31, 30, 31, 30, 31};

        public CustomDate(
            int m,
            int d
        ) {
            this.month = m;
            this.day = d;
        }

        public int getTotalDayDiffTo(CustomDate target) {
            int myTotalDay = getTotalDayOfMonth(this.month) + this.day;
            int targetTotalDay = getTotalDayOfMonth(target.month) + target.day;
            return targetTotalDay - myTotalDay;
        }

        private int getTotalDayOfMonth(int month) {
            int totalDay = 0;
            for (int i = 1; i < month; i++) {
                totalDay += CustomDate.dayPerMonth[i];
            }
            return totalDay;
        }
    }
}
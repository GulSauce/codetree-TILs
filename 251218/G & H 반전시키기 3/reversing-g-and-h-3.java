import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        String source;
        String target;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        source = sc.next();
        target = sc.next();

        sc.close();

        new Solver(source, target).solve();
    }
}

class Solver {

    final int MAX_RANGE = 4;
    String source;
    String target;

    public Solver(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public void solve() {
        int rangeCount = 0;

        int curRangeSize = 0;
        boolean onRange = false;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == target.charAt(i)) {
                curRangeSize = 0;
                onRange = false;
                continue;
            }
            if (onRange && curRangeSize < MAX_RANGE) {
                curRangeSize++;
                continue;
            }
            if (onRange && MAX_RANGE <= curRangeSize) {
                rangeCount++;
                curRangeSize = 1;
            }
            if (!onRange) {
                onRange = true;
                curRangeSize = 1;
                rangeCount++;
            }
        }
        System.out.println(rangeCount);
    }
}
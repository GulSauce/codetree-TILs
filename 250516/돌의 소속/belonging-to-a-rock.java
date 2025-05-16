import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, Q;
        ArrayList<Integer> groupNumbers = new ArrayList<>();
        groupNumbers.add(-1);
        ArrayList<Range> ranges = new ArrayList<>();
        int a, b;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 0; i < N; i++) {
            groupNumbers.add(sc.nextInt());
        }
        for (int i = 0; i < Q; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            ranges.add(new Range(a, b));
        }
        sc.close();

        new Solver(groupNumbers, ranges).solve();
    }
}

class Solver {

    ArrayList<Integer> groupNumbers;
    ArrayList<Range> ranges;

    int[][] prefixSum;

    public Solver(
        ArrayList<Integer> groupNumbers,
        ArrayList<Range> ranges
    ) {
        this.prefixSum = new int[4][groupNumbers.size()];
        this.groupNumbers = groupNumbers;
        this.ranges = ranges;
    }

    public void solve() {
        for (int i = 1; i < groupNumbers.size(); i++) {
            int curGroupNumber = groupNumbers.get(i);
            for (int groupNumber = 1; groupNumber <= 3; groupNumber++) {
                if (groupNumber == curGroupNumber) {
                    prefixSum[groupNumber][i] = 1 + prefixSum[groupNumber][i - 1];
                } else {
                    prefixSum[groupNumber][i] = prefixSum[groupNumber][i - 1];
                }
            }
        }
        for (Range range : ranges) {
            for (int groupNumber = 1; groupNumber <= 3; groupNumber++) {
                System.out.print(
                    prefixSum[groupNumber][range.end] - prefixSum[groupNumber][range.start - 1]
                        + " ");
            }
            System.out.println();
        }
    }
}

class Range {

    int start;
    int end;

    public Range(
        int a,
        int b
    ) {
        this.start = a;
        this.end = b;
    }
}
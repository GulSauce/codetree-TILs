import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(N, numbers).solve();
    }
}

class Segment implements Comparable<Segment> {
    int length;
    int start;
    int end;

    public Segment(
            int start,
            int end
    ) {
        this.length = end - start + 1;
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Segment other) {
        if (length == other.length) {
            if (start == other.start) {
                return end - other.end;
            }
            return start - other.start;
        }
        return length - other.length;
    }
}

class Solver {
    int maxNumber;
    ArrayList<Integer> removeNumbers;
    TreeSet<Segment> segmentTreeSet = new TreeSet<>();
    TreeSet<Integer> removedNumberSet = new TreeSet<>();

    public Solver(
            int N,
            ArrayList<Integer> numbers
    ) {
        this.maxNumber = N;
        this.removeNumbers = numbers;
    }

    public void solve() {
        init();

        for (Integer removeNumber : removeNumbers) {
            int removeSegmentStart = removedNumberSet.lower(removeNumber) + 1;
            int removeSegmentEnd = removedNumberSet.higher(removeSegmentStart) - 1;

            segmentTreeSet.remove(new Segment(removeSegmentStart, removeSegmentEnd));
            segmentTreeSet.add(new Segment(removeSegmentStart, removeNumber - 1));
            segmentTreeSet.add(new Segment(removeNumber + 1, removeSegmentEnd));
            System.out.println(segmentTreeSet.last().length);

            removedNumberSet.add(removeNumber);
        }
    }

    private void init() {
        removedNumberSet.add(-1);
        removedNumberSet.add(maxNumber + 1);
        segmentTreeSet.add(new Segment(0, maxNumber));
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<ComputerUseInfo> computerUseInfos = new ArrayList<>();
        int p, q;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            computerUseInfos.add(new ComputerUseInfo(p, q));
        }
        br.close();

        new Solver(N, computerUseInfos).solve();
    }
}

class Solver {

    int personCount;

    TreeSet<Integer> availableComputerNumber = new TreeSet<>();
    ArrayList<ComputerUseInfo> computerUseInfos;
    ArrayList<Point> points = new ArrayList<>();
    HashMap<Integer, Integer> pointComputerMap = new HashMap<>();
    TreeMap<Integer, Integer> answer = new TreeMap<>();

    public Solver(
        int personCount,
        ArrayList<ComputerUseInfo> computerUseInfos
    ) {
        this.personCount = personCount;
        this.computerUseInfos = computerUseInfos;
    }

    public void solve() {
        for (int i = 1; i <= personCount; i++) {
            availableComputerNumber.add(i);
        }
        for (int i = 0; i < computerUseInfos.size(); i++) {
            ComputerUseInfo computerUseInfo = computerUseInfos.get(i);
            points.add(new Point(computerUseInfo.start, i + 1, Status.start));
            points.add(new Point(computerUseInfo.end, i + 1, Status.end));
        }
        Collections.sort(points);
        for (Point point : points) {

            switch (point.status) {
                case start:
                    int numberToUse = availableComputerNumber.pollFirst();
                    pointComputerMap.put(point.index, numberToUse);
                    answer.put(point.index, numberToUse);
                    break;

                case end:
                    int usedNumber = pointComputerMap.get(point.index);
                    availableComputerNumber.add(usedNumber);
                    pointComputerMap.remove(point.index);
                    break;
            }
        }
        for (int computerNumber : answer.values()) {
            System.out.print(computerNumber + " ");
        }
    }
}

class Point implements Comparable<Point> {

    @Override
    public int compareTo(Point other) {
        return Integer.compare(this.x, other.x);
    }

    int x;
    int index;
    Status status;

    public Point(
        int x,
        int index,
        Status status
    ) {
        this.x = x;
        this.index = index;
        this.status = status;
    }
}

enum Status {
    start,
    end
}

class ComputerUseInfo {

    int start;
    int end;

    public ComputerUseInfo(
        int p,
        int q
    ) {
        this.start = p;
        this.end = q;
    }
}
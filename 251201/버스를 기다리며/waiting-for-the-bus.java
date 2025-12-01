import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M, C;
        List<ArrivalInfo> arrivalInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);
        C = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arrivalInfos.add(new ArrivalInfo(i, toInt(st)));
        }

        new Solver(M, C, arrivalInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int MAX_BUS_COUNT;
    final int MAX_BUS_PEOPLE;
    List<ArrivalInfo> arrivalInfos;

    public Solver(int MAX_BUS_COUNT, int MAX_BUS_PEOPLE, List<ArrivalInfo> arrivalInfos) {
        this.MAX_BUS_COUNT = MAX_BUS_COUNT;
        this.MAX_BUS_PEOPLE = MAX_BUS_PEOPLE;
        this.arrivalInfos = arrivalInfos;
    }

    public void solve() {
        Collections.sort(arrivalInfos);

        int left = 0;
        int right = 1_000_000_000;
        int answer = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(mid)) {
                right = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isValid(int maxWaitTime) {
        int curWaitStartTime = arrivalInfos.get(0).arrivalTime;
        int curWaitPerson = 1;
        int leaveBusCount = 1;
        for (int i = 1; i < arrivalInfos.size(); i++) {
            ArrivalInfo arrivalInfo = arrivalInfos.get(i);

            boolean isBusFull = MAX_BUS_PEOPLE < curWaitPerson + 1;
            boolean isTimeOver = maxWaitTime < arrivalInfo.arrivalTime - curWaitStartTime;

            // 새 버스를 만든다
            if (isBusFull || isTimeOver) {
                leaveBusCount++;
                curWaitStartTime = arrivalInfo.arrivalTime;
                curWaitPerson = 1;
            } else {
                curWaitPerson++;
            }
        }

        return leaveBusCount <= MAX_BUS_COUNT;
    }
}


class ArrivalInfo implements Comparable<ArrivalInfo> {

    int number;
    int arrivalTime;

    public ArrivalInfo(int number, int arrivalTime) {
        this.number = number;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int compareTo(ArrivalInfo other) {
        return Integer.compare(this.arrivalTime, other.arrivalTime);
    }
}
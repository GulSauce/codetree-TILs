import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<EnterInfo> enterInfos = new ArrayList<>();
        int a, t;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            a = sc.nextInt();
            t = sc.nextInt();
            enterInfos.add(new EnterInfo(i + 1, a, t));
        }
        sc.close();

        new Solver(enterInfos).solve();
    }
}

class Solver {
    ArrayList<EnterInfo> enterInfos;
    PriorityQueue<WaitInfo> waitInfoPriorityQueue = new PriorityQueue<>();
    PriorityQueue<EnterInfo> enterInfoPriorityQueue;

    public Solver(
            ArrayList<EnterInfo> enterInfos

    ) {
        this.enterInfos = enterInfos;
    }

    public void solve() {
        enterInfoPriorityQueue = new PriorityQueue<>(enterInfos);
        int leaveTime = 0;
        int answer = 0;
        while (!enterInfoPriorityQueue.isEmpty()) {
            while (!enterInfoPriorityQueue.isEmpty() && enterInfoPriorityQueue.peek().arriveTime <= leaveTime) {
                waitInfoPriorityQueue.add(WaitInfo.of(enterInfoPriorityQueue.poll()));
            }
            if (waitInfoPriorityQueue.isEmpty()) {
                EnterInfo nextEnter = enterInfoPriorityQueue.poll();
                int nextLeaveTime = nextEnter.arriveTime + nextEnter.stayTime;
                leaveTime = nextLeaveTime;
                continue;
            }
            WaitInfo nextEnter = waitInfoPriorityQueue.poll();
            int diff = leaveTime - nextEnter.arriveTime;
            int nextLeaveTime = leaveTime + nextEnter.stayTime;
            leaveTime = nextLeaveTime;
            answer = Math.max(answer, diff);
        }
        System.out.println(answer);
    }
}

class WaitInfo implements Comparable<WaitInfo> {
    @Override
    public int compareTo(WaitInfo other) {
        return Integer.compare(ticketNo, other.ticketNo);
    }

    int ticketNo;
    int arriveTime;
    int stayTime;

    public WaitInfo(
            int ticketNo,
            int arriveTime,
            int stayTime

    ) {
        this.ticketNo = ticketNo;
        this.arriveTime = arriveTime;
        this.stayTime = stayTime;
    }

    public static WaitInfo of(EnterInfo enterInfo) {
        return new WaitInfo(enterInfo.ticketNo, enterInfo.arriveTime, enterInfo.stayTime);
    }
}

class EnterInfo implements Comparable<EnterInfo> {
    @Override
    public int compareTo(EnterInfo other) {
        return Integer.compare(arriveTime, other.arriveTime);
    }

    int ticketNo;
    int arriveTime;
    int stayTime;

    public EnterInfo(
            int ticketNo,
            int arriveTime,
            int stayTime

    ) {
        this.ticketNo = ticketNo;
        this.arriveTime = arriveTime;
        this.stayTime = stayTime;
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, K;
        ArrayList<ChangeInfo> changeInfos = new ArrayList<>();
        int a, b;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < K; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            changeInfos.add(new ChangeInfo(a, b));
        }
        sc.close();

        new Solver(N, changeInfos).solve();
    }
}

class Solver {
    int personCount;
    int[] answer;
    int[] curSeatPerson;
    ArrayList<HashSet<Integer>> personSeatHistories = new ArrayList<>();
    ArrayList<ChangeInfo> changeInfos;

    public Solver(
            int personCount,
            ArrayList<ChangeInfo> changeInfos
    ) {
        this.answer = new int[personCount + 1];
        this.curSeatPerson = new int[personCount + 1];
        this.personCount = personCount;
        this.changeInfos = changeInfos;
    }

    public void solve() {
        init();

        for (int i = 0; i < 3; i++) {
            for (ChangeInfo changeInfo : changeInfos) {
                changeSeat(changeInfo);

                int firstPerson = curSeatPerson[changeInfo.firstSeat];
                if (!personSeatHistories.get(changeInfo.firstSeat).contains(firstPerson)) {
                    personSeatHistories.get(changeInfo.firstSeat).add(firstPerson);
                    answer[firstPerson]++;
                }

                int secondPerson = curSeatPerson[changeInfo.secondSeat];
                if (!personSeatHistories.get(changeInfo.secondSeat).contains(secondPerson)) {
                    personSeatHistories.get(changeInfo.secondSeat).add(secondPerson);
                    answer[secondPerson]++;
                }
            }
        }
        for (int i = 1; i <= personCount; i++) {
            System.out.println(answer[i]);
        }
    }

    private void init() {
        Arrays.fill(answer, 1);
        for (int i = 0; i <= personCount; i++) {
            curSeatPerson[i] = i;
            HashSet<Integer> hashSet = new HashSet<>();
            hashSet.add(i);
            personSeatHistories.add(hashSet);
        }

    }

    private void changeSeat(ChangeInfo changeInfo) {
        int firstPerson = curSeatPerson[changeInfo.firstSeat];
        int secondPerson = curSeatPerson[changeInfo.secondSeat];
        curSeatPerson[changeInfo.firstSeat] = secondPerson;
        curSeatPerson[changeInfo.secondSeat] = firstPerson;
    }
}

class ChangeInfo {
    int firstSeat;
    int secondSeat;

    public ChangeInfo(
            int a,
            int b
    ) {
        this.firstSeat = a;
        this.secondSeat = b;
    }
}
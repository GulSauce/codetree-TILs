import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N, M, D, S;
        List<CheeseEatingInfo> cheeseEatingInfos = new ArrayList<>();
        int p, m, t;
        List<SickInfo> sickInfos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt();
        S = sc.nextInt();
        for (int i = 0; i < D; i++) {
            p = sc.nextInt();
            m = sc.nextInt();
            t = sc.nextInt();
            cheeseEatingInfos.add(new CheeseEatingInfo(p, m, t));
        }
        for (int i = 0; i < S; i++) {
            p = sc.nextInt();
            m = sc.nextInt();
            sickInfos.add(new SickInfo(p, m));
        }
        sc.close();

        new Solver(N, M, cheeseEatingInfos, sickInfos).solve();
    }
}

class Solver {
    int personCount;
    int cheeseCount;
    List<CheeseEatingInfo> cheeseEatingInfos;
    List<SickInfo> sickInfos;
    boolean[][] cheeseEatBeforeSick;

    public Solver(
            int N,
            int M,
            List<CheeseEatingInfo> cheeseEatingInfos,
            List<SickInfo> sickInfos
    ) {
        this.personCount = N;
        this.cheeseCount = M;
        this.cheeseEatingInfos = cheeseEatingInfos;
        this.sickInfos = sickInfos;
        this.cheeseEatBeforeSick = new boolean[N + 1][M + 1];
    }

    public void solve() {
        calcSickCheese();
        printAnswer();
    }


    private void printAnswer() {
        int answer = 0;
        for (int i = 1; i <= cheeseCount; i++) {
            boolean badCheese = true;
            for (SickInfo sickInfo : sickInfos) {
                if (!cheeseEatBeforeSick[sickInfo.personNo][i]) {
                    badCheese = false;
                    break;
                }
            }
            if (!badCheese) {
                continue;
            }
            
            Set<Integer> personCount = new HashSet<>();
            for (CheeseEatingInfo cheeseEatingInfo : cheeseEatingInfos) {
                if (cheeseEatingInfo.cheeseNo == i) {
                    personCount.add(cheeseEatingInfo.personNo);
                }
            }
            answer = Math.max(answer, personCount.size());

        }
        System.out.println(answer);
    }

    private void calcSickCheese() {
        for (SickInfo sickInfo : sickInfos) {
            int curPersonNo = sickInfo.personNo;
            int curSickTime = sickInfo.sickTime;

            for (CheeseEatingInfo cheeseEatingInfo : cheeseEatingInfos) {
                if (cheeseEatingInfo.personNo != curPersonNo) {
                    continue;
                }
                if (curSickTime <= cheeseEatingInfo.eatingTime) {
                    continue;
                }
                cheeseEatBeforeSick[curPersonNo][cheeseEatingInfo.cheeseNo] = true;
            }
        }
    }
}

class SickInfo {
    int personNo;
    int sickTime;

    public SickInfo(
            int p,
            int t
    ) {
        this.personNo = p;
        this.sickTime = t;
    }
}

class CheeseEatingInfo {
    int personNo;
    int cheeseNo;
    int eatingTime;

    public CheeseEatingInfo(
            int p,
            int m,
            int t
    ) {
        this.personNo = p;
        this.cheeseNo = m;
        this.eatingTime = t;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M;
        List<ClothInfo> clothInfos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int v = sc.nextInt();
            clothInfos.add(new ClothInfo(s, e, v));
        }
        sc.close();

        new Solver(N, M, clothInfos).solve();
    }
}

class Solver {

    int clothInfosIndex;
    int endDay;
    final int NOT_ALLOCATED = Integer.MIN_VALUE;

    int[][] dp;

    List<ClothInfo> clothInfos;

    public Solver(
        int N,
        int M,
        List<ClothInfo> clothInfos
    ) {
        this.clothInfosIndex = N - 1;
        this.endDay = M;
        this.clothInfos = clothInfos;
        this.dp = new int[M + 1][N];
    }

    public void solve() {
        initDP();
        calcDP();
        printAnswer();
    }

    private void printAnswer() {
        int answer = 0;
        for (int clothIndex = 0; clothIndex <= clothInfosIndex; clothIndex++) {
            answer = Math.max(answer, dp[endDay][clothIndex]);
        }
        System.out.println(answer);
    }

    private void calcDP() {
        for (int day = 2; day <= endDay; day++) {
            for (int curClothIndex = 0; curClothIndex <= clothInfosIndex; curClothIndex++) {
                ClothInfo curClothInfo = clothInfos.get(curClothIndex);
                if (isCanNotWear(day, curClothInfo)) {
                    continue;
                }
                for (int prevClothIndex = 0; prevClothIndex <= clothInfosIndex; prevClothIndex++) {
                    ClothInfo prevClothInfo = clothInfos.get(prevClothIndex);
                    if (dp[day - 1][prevClothIndex] == NOT_ALLOCATED) {
                        continue;
                    }
                    int curSatisfy = Math.abs(curClothInfo.variety - prevClothInfo.variety);
                    dp[day][curClothIndex] = Math.max(dp[day][curClothIndex],
                        dp[day - 1][prevClothIndex] + curSatisfy);
                }
            }
        }
    }

    private void initDP() {
        for (int[] array : dp) {
            Arrays.fill(array, NOT_ALLOCATED);
        }
        for (int curClothIndex = 0; curClothIndex <= clothInfosIndex; curClothIndex++) {
            ClothInfo curClothInfo = clothInfos.get(curClothIndex);
            if (isCanNotWear(1, curClothInfo)) {
                continue;
            }
            dp[1][curClothIndex] = 0;
        }
    }

    private boolean isCanNotWear(int day, ClothInfo clothInfo) {
        return !(clothInfo.startDay <= day && day <= clothInfo.endDay);
    }
}

class ClothInfo {

    int startDay;
    int endDay;
    int variety;

    public ClothInfo(
        int s,
        int e,
        int v
    ) {
        this.startDay = s;
        this.endDay = e;
        this.variety = v;
    }
}
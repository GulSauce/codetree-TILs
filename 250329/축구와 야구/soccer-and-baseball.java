import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        List<Status> statuses = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        statuses.add(new Status(-1, -1));
        for (int i = 0; i < N; i++) {
            statuses.add(new Status(sc.nextInt(), sc.nextInt()));
        }
        new Solver(N, statuses).solve();
    }
}

class Solver {

    int statuesIndex;
    final int SOCCER_MEMBER = 11;
    final int BASEBALL_MEMBER = 9;
    final int NOT_ALLOCATED = Integer.MIN_VALUE;
    int[][][] dp;

    List<Status> statuses;

    public Solver(
        int N,
        List<Status> statuses
    ) {
        this.statuesIndex = N;
        this.statuses = statuses;
        this.dp = new int[N + 1][SOCCER_MEMBER + 1][BASEBALL_MEMBER + 1];
    }

    public void solve() {
        initDP();
        calcDP();
        printAnswer();
    }

    private void printAnswer() {
        System.out.println(dp[statuesIndex][SOCCER_MEMBER][BASEBALL_MEMBER]);
    }

    private void calcDP() {
        for (int i = 0; i < statuesIndex; i++) {
            Status nextStatus = statuses.get(i + 1);
            for (int soccerCount = 0; soccerCount <= SOCCER_MEMBER; soccerCount++) {
                for (int baseballCount = 0; baseballCount <= BASEBALL_MEMBER; baseballCount++) {
                    if (dp[i][soccerCount][baseballCount] == NOT_ALLOCATED) {
                        continue;
                    }
                    if (soccerCount < SOCCER_MEMBER) {
                        dp[i + 1][soccerCount + 1][baseballCount] = Math.max(
                            dp[i + 1][soccerCount + 1][baseballCount],
                            dp[i][soccerCount][baseballCount] + nextStatus.soccer);
                    }
                    if (baseballCount < BASEBALL_MEMBER) {
                        dp[i + 1][soccerCount][baseballCount + 1] = Math.max(
                            dp[i + 1][soccerCount][baseballCount + 1],
                            dp[i][soccerCount][baseballCount] + nextStatus.baseBall);
                    }
                    dp[i + 1][soccerCount][baseballCount] = Math.max(
                        dp[i + 1][soccerCount][baseballCount],
                        dp[i][soccerCount][baseballCount]);
                }
            }
        }
    }

    private void initDP() {
        for (int[][] arrays : dp) {
            for (int[] array : arrays) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
        }
        dp[0][0][0] = 0;
    }
}

class Status {

    int soccer;
    int baseBall;

    public Status(
        int s,
        int b
    ) {
        this.soccer = s;
        this.baseBall = b;
    }
}
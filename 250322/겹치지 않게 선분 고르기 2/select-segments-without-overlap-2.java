import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int lineInfosIndex;

        List<LineInfo> lineInfos;
        int[] dp;

        public Solver(
            int N,
            List<LineInfo> lineInfos
        ) {
            this.lineInfosIndex = N - 1;
            this.lineInfos = lineInfos;
            this.dp = new int[N];
        }

        public void solve() {
            Collections.sort(lineInfos);
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int value : dp) {
                answer = Math.max(answer, value);
            }
            System.out.println(answer);
        }

        private void calcDP() {
            for (int cur = 1; cur <= lineInfosIndex; cur++) {
                for (int prev = 0; prev < cur; prev++) {
                    if (isCollide(lineInfos.get(prev), lineInfos.get(cur))) {
                        continue;
                    }
                    dp[cur] = Math.max(dp[cur], dp[prev] + 1);
                }
            }
        }

        private boolean isCollide(LineInfo prev, LineInfo cur) {
            if (prev.end < cur.start) {
                return false;
            }
            return true;
        }

        private void initDP() {
            Arrays.fill(dp, 1);
        }
    }

    public static void main(String[] args) {
        int N;
        List<LineInfo> lineInfos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            lineInfos.add(new LineInfo(sc.nextInt(), sc.nextInt()));
        }
        sc.close();

        new Solver(N, lineInfos).solve();
    }

    private static class LineInfo implements Comparable<LineInfo> {

        int start;
        int end;

        @Override
        public int compareTo(LineInfo lineInfo) {
            return this.start - lineInfo.start;
        }

        public LineInfo(
            int x1,
            int x2
        ) {
            this.start = x1;
            this.end = x2;
        }
    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int answer = 0;
        List<LineInfo> lineInfos;
        List<LineInfo> lineCombination = new ArrayList<>();

        public Solver(
            List<LineInfo> lineInfos
        ) {
            this.lineInfos = lineInfos;
        }

        public void solve() {
            Collections.sort(lineInfos);
            setLineCombinationCheckingNotCollide(0);
            System.out.println(answer);
        }

        private void setLineCombinationCheckingNotCollide(int curIndex) {
            if (curIndex == lineInfos.size()) {
                if (isNotCollide()) {
                    answer = Math.max(answer, lineCombination.size());
                }
                return;
            }
            lineCombination.add(lineInfos.get(curIndex));
            setLineCombinationCheckingNotCollide(curIndex + 1);
            lineCombination.remove(lineCombination.size() - 1);
            setLineCombinationCheckingNotCollide(curIndex + 1);
        }

        private boolean isNotCollide() {
            for (int i = 1; i < lineCombination.size(); i++) {
                if (lineCombination.get(i).left <= lineCombination.get(i - 1).right) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<LineInfo> lineInfos = new ArrayList<>();
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            lineInfos.add(new LineInfo(sc.nextInt(), sc.nextInt()));
        }
        new Main.Solver(lineInfos).solve();
    }

    private static class LineInfo implements Comparable<LineInfo> {

        int left;
        int right;

        @Override
        public int compareTo(LineInfo lineInfo) {
            return this.left - lineInfo.left;
        }

        public LineInfo(
            int l,
            int r
        ) {
            this.left = l;
            this.right = r;
        }
    }
}
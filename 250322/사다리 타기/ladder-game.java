import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int answer = Integer.MAX_VALUE;
        int verticalLineCount;
        int horizontalLineCount;
        int horizontalLinesIndex;
        final int VERTICAL_LINE_HEIGHT = 15;
        List<Integer> goals = new ArrayList<>();
        List<Integer> results = new ArrayList<>();

        List<HorizontalLine> horizontalLines;
        List<HorizontalLine> selectedHorizontalLines = new ArrayList<>();

        int[][] ladder;

        public Solver(
            int N,
            int M,
            List<HorizontalLine> horizontalLines
        ) {
            this.verticalLineCount = N;
            this.horizontalLineCount = M;
            this.horizontalLinesIndex = M - 1;
            this.horizontalLines = horizontalLines;
            this.ladder = new int[N + 1][VERTICAL_LINE_HEIGHT + 1];
        }

        public void solve() {
            initLadder(horizontalLines);
            setGoals();
            setSelectedHorizontalLinesCheckingResult(-1);
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(answer);
        }

        private void setSelectedHorizontalLinesCheckingResult(int prev) {
            if (prev == horizontalLinesIndex) {
                initLadder(selectedHorizontalLines);
                setResults();
                if (isGoalsAndResultsEqual()) {
                    answer = Math.min(answer, selectedHorizontalLines.size());
                }
                return;
            }
            int current = prev + 1;
            selectedHorizontalLines.add(horizontalLines.get(current));
            setSelectedHorizontalLinesCheckingResult(current);
            selectedHorizontalLines.remove(selectedHorizontalLines.size() - 1);
            setSelectedHorizontalLinesCheckingResult(current);
        }

        private boolean isGoalsAndResultsEqual() {
            for (int i = 0; i < goals.size(); i++) {
                if (!goals.get(i).equals(results.get(i))) {
                    return false;
                }
            }
            return true;
        }

        private void setResults() {
            results.clear();
            for (int start = 1; start <= verticalLineCount; start++) {
                int curVerticalNumber = start;
                for (int i = 1; i <= VERTICAL_LINE_HEIGHT; i++) {
                    int nextVerticalNumber = ladder[curVerticalNumber][i];
                    curVerticalNumber = nextVerticalNumber;
                }
                results.add(curVerticalNumber);
            }
        }

        private void setGoals() {
            for (int start = 1; start <= verticalLineCount; start++) {
                int curVerticalNumber = start;
                for (int i = 1; i <= VERTICAL_LINE_HEIGHT; i++) {
                    int nextVerticalNumber = ladder[curVerticalNumber][i];
                    curVerticalNumber = nextVerticalNumber;
                }
                goals.add(curVerticalNumber);
            }
        }

        private void initLadder(List<HorizontalLine> horizontalLines) {
            for (int i = 1; i <= verticalLineCount; i++) {
                for (int j = 1; j <= VERTICAL_LINE_HEIGHT; j++) {
                    ladder[i][j] = i;
                }
            }
            for (HorizontalLine horizontalLine : horizontalLines) {
                ladder[horizontalLine.startVerticalLine][horizontalLine.pointInVerticalLine] =
                    horizontalLine.startVerticalLine + 1;
                ladder[horizontalLine.startVerticalLine + 1][horizontalLine.pointInVerticalLine] =
                    horizontalLine.startVerticalLine;
            }
        }
    }

    public static void main(String[] args) {
        int N, M;
        List<HorizontalLine> horizontalLines = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            horizontalLines.add(new HorizontalLine(a, b));
        }
        sc.close();

        new Solver(N, M, horizontalLines).solve();
    }

    private static class HorizontalLine {

        int startVerticalLine;
        int pointInVerticalLine;

        public HorizontalLine(
            int a,
            int b
        ) {
            this.startVerticalLine = a;
            this.pointInVerticalLine = b;
        }
    }
}
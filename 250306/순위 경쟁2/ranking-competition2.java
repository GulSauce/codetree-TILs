import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int curAScore = 0;
        int curBScore = 0;
        int answer = 0;

        List<ScoreInfo> scoreInfos;

        public Solver(
            List<ScoreInfo> scoreInfos
        ) {
            this.scoreInfos = scoreInfos;
        }

        public void solve() {
            for (ScoreInfo scoreInfo : scoreInfos) {
                processBy(scoreInfo);
            }
            System.out.println(answer);
        }

        private void processBy(ScoreInfo scoreInfo) {
            int nextAScore = curAScore;
            int nextBScore = curBScore;
            if (Objects.equals(scoreInfo.student, "A")) {
                nextAScore += scoreInfo.deltaScore;
            } else if (Objects.equals(scoreInfo.student, "B")) {
                nextBScore += scoreInfo.deltaScore;
            } else {
                throw new IllegalArgumentException();
            }
            if (curAScore == curBScore) {
                if (nextAScore > nextBScore) {
                    answer++;
                }
                if (nextBScore > nextAScore) {
                    answer++;
                }
            }
            if (curAScore > curBScore) {
                if (nextAScore == nextBScore) {
                    answer++;
                }
                if (nextBScore > nextAScore) {
                    answer++;
                }
            }
            if (curAScore < curBScore) {
                if (nextAScore == nextBScore) {
                    answer++;
                }
                if (nextBScore < nextAScore) {
                    answer++;
                }
            }
            curAScore = nextAScore;
            curBScore = nextBScore;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<ScoreInfo> scoreInfos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            scoreInfos.add(new ScoreInfo(sc.next(), sc.nextInt()));
        }
        new Main.Solver(scoreInfos).solve();
    }

    private static class ScoreInfo {

        String student;
        int deltaScore;

        public ScoreInfo(
            String c,
            int s
        ) {
            this.student = c;
            this.deltaScore = s;
        }
    }
}
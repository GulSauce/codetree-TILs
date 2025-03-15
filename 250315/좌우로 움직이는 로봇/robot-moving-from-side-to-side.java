import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        Robot robotA;
        Robot robotB;

        public Solver(
            Robot robotA,
            Robot robotB
        ) {
            this.robotA = robotA;
            this.robotB = robotB;
        }

        public void solve() {
            int answer = 0;
            robotA.move();
            robotB.move();
            int lastMoveTime = Math.max(robotA.currentMoveCount, robotB.currentMoveCount);
            for (int i = 1; i <= lastMoveTime; i++) {
                if (robotA.getPositionAt(i - 1) == robotB.getPositionAt(i - 1)) {
                    continue;
                }
                if (robotA.getPositionAt(i) != robotB.getPositionAt(i)) {
                    continue;
                }
                answer++;
            }
            System.out.println(answer);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<MoveInfo> moveInfosOfA = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            moveInfosOfA.add(new MoveInfo(sc.nextInt(), sc.next()));
        }
        List<MoveInfo> moveInfosOfB = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            moveInfosOfB.add(new MoveInfo(sc.nextInt(), sc.next()));
        }

        new Solver(new Robot(moveInfosOfA), new Robot(moveInfosOfB)).solve();
    }

    private static class Robot {

        int[] moveHistory = new int[1_000_001];
        int currentMoveCount = 0;
        int currentX = 1_000_000;

        List<MoveInfo> moveInfos;

        public Robot(
            List<MoveInfo> moveInfos
        ) {
            this.moveInfos = moveInfos;
        }

        public void move() {
            moveHistory[currentMoveCount] = currentX;
            int move;
            for (MoveInfo moveInfo : moveInfos) {
                if (moveInfo.direction.equals("L")) {
                    move = -1;
                } else {
                    move = 1;
                }
                for (int i = 0; i < moveInfo.moveCount; i++) {
                    currentX += move;
                    moveHistory[++currentMoveCount] = currentX;
                }
            }
        }

        private int getPositionAt(int time) {
            if (time >= currentMoveCount) {
                return moveHistory[currentMoveCount];
            }
            return moveHistory[time];
        }
    }

    private static class MoveInfo {

        int moveCount;
        String direction;

        public MoveInfo(
            int t,
            String d
        ) {
            this.moveCount = t;
            this.direction = d;
        }
    }
}
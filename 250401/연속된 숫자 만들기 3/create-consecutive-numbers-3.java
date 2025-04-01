import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int aPos, bPos, cPos;

        Scanner sc = new Scanner(System.in);
        aPos = sc.nextInt();
        bPos = sc.nextInt();
        cPos = sc.nextInt();

        new Solver(aPos, bPos, cPos).solve();
    }
}

class Solver {

    int aPos;
    int bPos;
    int cPos;

    public Solver(int aPos, int bPos, int cPos) {
        this.aPos = aPos;
        this.bPos = bPos;
        this.cPos = cPos;
    }

    public void solve() {
        if (isConsecutive()) {
            System.out.println(0);
            return;
        }
        int resultLeftFirstMove = getResultLeftFirstMove();
        int resultRightFirstMove = getResultRightFirstMove();
        System.out.println(Math.max(resultLeftFirstMove, resultRightFirstMove));
    }

    private int getResultLeftFirstMove() {
        return cPos - bPos - 1;
    }

    private int getResultRightFirstMove() {
        return bPos - aPos - 1;
    }

    private boolean isConsecutive() {
        if (aPos + 1 == bPos && bPos + 1 == cPos) {
            return true;
        }
        return false;
    }

    private int getMaxDist() {
        return Math.max(bPos - aPos, cPos - bPos);
    }
}
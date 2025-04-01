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
        int maxDist = getMaxDist();
        System.out.println(maxDist - 1);
    }

    private int getMaxDist() {
        return Math.max(bPos - aPos, cPos - bPos);
    }
}
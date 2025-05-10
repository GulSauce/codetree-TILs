import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<Integer> nNumbers = new ArrayList<>();
        ArrayList<Integer> mNumbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            nNumbers.add(sc.nextInt());
        }
        for (int i = 0; i < M; i++) {
            mNumbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(nNumbers, mNumbers).solve();
    }
}

class Solver {
    ArrayList<Integer> nNumbers;
    ArrayList<Integer> mNumbers;
    TreeSet<Integer> nNumbersSet;

    public Solver(ArrayList<Integer> nNumbers,
                  ArrayList<Integer> mNumbers
    ) {
        this.nNumbers = nNumbers;
        this.mNumbers = mNumbers;
        this.nNumbersSet = new TreeSet<>(nNumbers);
    }

    public void solve() {
        for (Integer mNumber : mNumbers) {
            Integer lowerBound = nNumbersSet.ceiling(mNumber);
            int value = lowerBound == null ? -1 : lowerBound;
            System.out.println(value);
        }
    }

}
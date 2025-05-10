import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<Integer> queryNumbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            queryNumbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(M, queryNumbers).solve();
    }
}

class Solver {
    int numberCount;
    ArrayList<Integer> targetNumbers = new ArrayList<>();
    ArrayList<Integer> queryNumbers;

    TreeSet<Integer> targetNumbersSet;


    public Solver(
            int numberCount,
            ArrayList<Integer> queryNumbers
    ) {
        this.numberCount = numberCount;
        this.queryNumbers = queryNumbers;
    }

    public void solve() {
        for (int i = 1; i <= numberCount; i++) {
            targetNumbers.add(i);
        }
        targetNumbersSet = new TreeSet<>(targetNumbers);
        for (Integer queryNumber : queryNumbers) {
            targetNumbersSet.remove(queryNumber);
            System.out.println(targetNumbersSet.last());
        }
    }
}
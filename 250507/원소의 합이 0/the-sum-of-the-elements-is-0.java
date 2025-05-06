import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        ArrayList<Integer> C = new ArrayList<>();
        ArrayList<Integer> D = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }
        for (int i = 0; i < N; i++) {
            B.add(sc.nextInt());
        }
        for (int i = 0; i < N; i++) {
            C.add(sc.nextInt());
        }
        for (int i = 0; i < N; i++) {
            D.add(sc.nextInt());
        }
        sc.close();

        new Solver(A, B, C, D).solve();
    }
}

class Solver {
    ArrayList<Integer> A;
    ArrayList<Integer> B;
    ArrayList<Integer> C;
    ArrayList<Integer> D;

    public Solver(
            ArrayList<Integer> A,
            ArrayList<Integer> B,
            ArrayList<Integer> C,
            ArrayList<Integer> D
    ) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    public void solve() {
        int answer = 0;
        HashMap<Integer, Integer> CDHashMap = new HashMap<>();
        for (Integer cNumber : C) {
            for (Integer dNumber : D) {
                CDHashMap.put(cNumber + dNumber, CDHashMap.getOrDefault(cNumber + dNumber, 0) + 1);
            }
        }
        for (Integer aNumber : A) {
            for (Integer bNumber : B) {
                int targetNumber = -1 * (aNumber + bNumber);
                if (!CDHashMap.containsKey(targetNumber)) {
                    continue;
                }
                answer += CDHashMap.get(targetNumber);
            }
        }
        System.out.println(answer);
    }
}
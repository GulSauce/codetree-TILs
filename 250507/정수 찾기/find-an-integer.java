import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Integer> A = new ArrayList<>();
        int M;
        ArrayList<Integer> B = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }
        M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            B.add(sc.nextInt());
        }
        sc.close();

        new Solver(A, B).solve();
    }
}

class Solver {
    ArrayList<Integer> A;
    ArrayList<Integer> B;

    public Solver(
            ArrayList<Integer> A,
            ArrayList<Integer> B
    ) {
        this.A = A;
        this.B = B;
    }

    public void solve() {
        HashSet<Integer> hashSet = new HashSet<>(A);
        for (Integer queryNumber : B) {
            if (hashSet.contains(queryNumber)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
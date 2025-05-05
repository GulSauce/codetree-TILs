import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M;
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }
        for (int i = 0; i < M; i++) {
            B.add(sc.nextInt());
        }
        sc.close();

        new Solver(A, B).solve();
    }
}

class Solver {
    List<Integer> A;
    List<Integer> B;

    int[] bNumberCount = new int[101];

    public Solver(
            List<Integer> A,
            List<Integer> B
    ) {
        this.A = A;
        this.B = B;
    }

    public void solve() {
        Collections.sort(B);
        int answer = 0;
        for (int i = 0; i <= A.size() - B.size(); i++) {
            if (isSameWithB(i)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private boolean isSameWithB(int aStart) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = aStart; i < aStart + B.size(); i++) {
            numbers.add(A.get(i));
        }
        Collections.sort(numbers);

        return numbers.equals(B);
    }
}
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(M, numbers).solve();
    }
}

class Solver {
    int minDiff;
    ArrayList<Integer> numbers;
    TreeSet<Integer> numbersTreeSet;

    public Solver(
            int M,
            ArrayList<Integer> numbers
    ) {
        this.minDiff = M;
        this.numbers = numbers;
    }

    public void solve() {
        numbersTreeSet = new TreeSet<>(numbers);
        int answer = Integer.MAX_VALUE;
        for (Integer number : numbersTreeSet) {
            Integer other = numbersTreeSet.ceiling(number + minDiff);
            if (other == null) {
                continue;
            }
            answer = Math.min(answer, other - number);
        }
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
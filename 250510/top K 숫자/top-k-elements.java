import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N, K;
        ArrayList<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(K, numbers).solve();
    }
}

class Solver {
    int topK;
    ArrayList<Integer> numbers;
    TreeSet<Integer> numbersSet;

    public Solver(
            int topK,
            ArrayList<Integer> numbers
    ) {
        this.topK = topK;
        this.numbers = numbers;
    }

    public void solve() {
        numbersSet = new TreeSet<>(numbers);
        for (int i = 0; i < topK; i++) {
            System.out.print(numbersSet.last() + " ");
            numbersSet.remove(numbersSet.last());
        }
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M;
        List<Pair> pairs = new ArrayList<>();
        int a, b;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            pairs.add(new Pair(a, b));
        }
        sc.close();

        new Solver(N, pairs).solve();
    }
}

class Solver {
    int maxNumber;
    List<Pair> pairs;

    public Solver(
            int N,
            List<Pair> pairs
    ) {
        this.maxNumber = N;
        this.pairs = pairs;
    }

    public void solve() {
        int answer = 0;
        for (int i = 1; i <= maxNumber; i++) {
            for (int j = i + 1; j <= maxNumber; j++) {
                int count = 0;
                for (Pair pair : pairs) {
                    if (pair.a == i && pair.b == j) {
                        count++;
                    } else if (pair.a == j && pair.b == i) {
                        count++;
                    }
                }
                answer = Math.max(answer, count);
            }
        }
        System.out.println(answer);
    }
}

class Pair {
    int a;
    int b;

    public Pair(
            int a,
            int b
    ) {
        this.a = a;
        this.b = b;
    }
}
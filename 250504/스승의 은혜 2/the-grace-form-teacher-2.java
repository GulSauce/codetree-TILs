import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        int B;
        List<Integer> prices = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        B = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int P = sc.nextInt();
            prices.add(P);
        }
        sc.close();

        new Solver(B, prices).solve();
    }
}

class Solver {
    int budget;
    List<Integer> prices;

    public Solver(
            int B,
            List<Integer> prices
    ) {
        this.budget = B;
        this.prices = prices;
    }

    public void solve() {
        Collections.sort(prices);
        int answer = 0;
        for (int i = 0; i < prices.size(); i++) {
            int sum = 0;
            for (int j = 0; j < prices.size(); j++) {
                if (i == j) {
                    sum += prices.get(j) / 2;
                } else {
                    sum += prices.get(j);
                }
                if (sum <= budget) {
                    answer = Math.max(answer, j + 1);
                } else {
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}
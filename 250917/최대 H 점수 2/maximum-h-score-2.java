import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, L;
        List<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(L, numbers).solve();
    }
}

class Solver {
    int maxPlusOneCount;
    List<Integer> numbers;

    public Solver(
            int L,
            List<Integer> numbers
    ) {
        this.maxPlusOneCount = L;
        this.numbers = numbers;
    }

    public void solve() {
        for (int answer = 100; answer >= 0; answer--) {
            int curPlusOneCount = 0;
            int greaterEqualThenAnswerCount = 0;

            for (Integer number : numbers) {
                if (answer <= number) {
                    greaterEqualThenAnswerCount++;
                    continue;
                }
                if (number + 1 < answer) {
                    continue;
                }
                if (maxPlusOneCount <= curPlusOneCount) {
                    continue;
                }
                greaterEqualThenAnswerCount++;
                curPlusOneCount++;
            }
            if (answer <= greaterEqualThenAnswerCount) {
                System.out.println(answer);
                break;
            }
        }
    }
}
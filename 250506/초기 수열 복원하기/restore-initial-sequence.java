import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        List<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N - 1; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(numbers).solve();
    }
}

class Solver {
    List<Integer> numbers;

    public Solver(
            List<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        List<Integer> answer = new ArrayList<>();
        for (int start = 1; start <= 1000; start++) {
            answer.clear();
            boolean success = true;
            boolean[] exitsOfNumber = new boolean[1001];
            int cur = start;
            for (Integer number : numbers) {
                if (number <= cur) {
                    success = false;
                    break;
                }
                if (exitsOfNumber[cur]) {
                    success = false;
                    break;
                }
                exitsOfNumber[cur] = true;
                answer.add(cur);
                int next = number - cur;
                cur = next;
            }
            answer.add(cur);
            if (success) {
                break;
            }
        }
        for (Integer number : answer) {
            System.out.print(number + " ");
        }
    }
}
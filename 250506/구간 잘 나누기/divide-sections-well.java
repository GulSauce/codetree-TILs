import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M;
        List<Integer> numbers = new ArrayList<>();

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
    int maxBorderCount;
    List<Integer> numbers;

    public Solver(
            int areaCount,
            List<Integer> numbers
    ) {
        this.maxBorderCount = areaCount - 1;
        this.numbers = numbers;
    }

    public void solve() {
        for (int answer = 1; answer <= 100000; answer++) {
            boolean success = true;
            int curAreaSum = 0;
            int curAreaCount = 0;
            for (Integer number : numbers) {
                if (answer < number) {
                    success = false;
                    break;
                }

                int nextAreaSum;
                if (answer < number + curAreaSum) {
                    curAreaCount++;
                    nextAreaSum = number;
                } else {
                    nextAreaSum = number + curAreaSum;
                }
                curAreaSum = nextAreaSum;
            }
            if (maxBorderCount < curAreaCount) {
                success = false;
            }
            if (success) {
                System.out.println(answer);
                break;
            }
        }
    }
}
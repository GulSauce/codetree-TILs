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
            boolean samePartitionCount = false;
            boolean answerFound = false;
            int curAreaSum = 0;
            int curAreaCount = 0;
            for (Integer number : numbers) {
                if (answer < number) {
                    answerFound = false;
                    break;
                }
                if (curAreaSum == answer) {
                    answerFound = true;
                }

                int nextAreaCount;
                int nextAreaSum;
                if (answer < number + curAreaSum) {
                    nextAreaCount = curAreaCount + 1;
                    nextAreaSum = number;
                } else {
                    nextAreaCount = curAreaCount;
                    nextAreaSum = number + curAreaSum;
                }
                curAreaCount = nextAreaCount;
                curAreaSum = nextAreaSum;
            }
            if (maxBorderCount == curAreaCount) {
                samePartitionCount = true;
            }
            if (samePartitionCount && answerFound) {
                System.out.println(answer);
                break;
            }
        }
    }
}
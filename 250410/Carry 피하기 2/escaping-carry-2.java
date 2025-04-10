import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        List<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(N, numbers).solve();
    }
}

class Solver {

    int numbersIndex;
    List<Integer> numbers;

    public Solver(
        int N,
        List<Integer> numbers
    ) {
        this.numbersIndex = N - 1;
        this.numbers = numbers;
    }

    public void solve() {
        int maxSum = -1;
        for (int first = 0; first <= numbersIndex; first++) {
            for (int second = first + 1; second <= numbersIndex; second++) {
                for (int third = second + 1; third <= numbersIndex; third++) {
                    if (isCarry(first, second, third)) {
                        continue;
                    }
                    int curSum = getSum(first, second, third);
                    maxSum = Math.max(maxSum, curSum);
                }
            }
        }
        System.out.println(maxSum);
    }

    private int getSum(int firstIndex, int secondIndex, int thirdIndex) {
        return numbers.get(firstIndex) + numbers.get(secondIndex) + numbers.get(thirdIndex);
    }

    private boolean isCarry(int firstIndex, int secondIndex, int thirdIndex) {
        int first = numbers.get(firstIndex);
        int second = numbers.get(secondIndex);
        int third = numbers.get(thirdIndex);

        while (first > 0 || second > 0 || third > 0) {
            int digit1 = (first % 10) + (second % 10) + (third % 10);
            if (digit1 >= 10) {
                return true;
            }
            int nextFirst = first / 10;
            int nextSecond = second / 10;
            int nextThird = third / 10;
            first = nextFirst;
            second = nextSecond;
            third = nextThird;
        }
        return false;
    }
}
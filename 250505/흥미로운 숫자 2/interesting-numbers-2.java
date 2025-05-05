import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int X, Y;

        Scanner sc = new Scanner(System.in);
        X = sc.nextInt();
        Y = sc.nextInt();
        sc.close();

        new Solver(X, Y).solve();
    }
}

class Solver {
    int start;
    int end;

    public Solver(
            int X,
            int Y
    ) {
        this.start = X;
        this.end = Y;
    }

    public void solve() {
        int answer = 0;
        for (int i = start; i <= end; i++) {
            if (isInterestingNumber(i)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private boolean isInterestingNumber(int number) {
        String numberString = String.valueOf(number);
        int[] numberCount = new int[10];
        for (int i = 0; i < numberString.length(); i++) {
            int numberValue = numberString.charAt(i) - '0';
            numberCount[numberValue]++;
        }

        boolean conditionA = false;
        boolean conditionB = false;
        for (int i = 0; i <= 9; i++) {
            if (numberCount[i] == numberString.length() - 1) {
                conditionA = true;
            }
            if (numberCount[i] == 1) {
                conditionB = true;
            }
        }
        return conditionA & conditionB;
    }
}
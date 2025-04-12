import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N, M;
        int[] bombNumbers;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        bombNumbers = new int[N];
        for (int i = 0; i < N; i++) {
            bombNumbers[i] = sc.nextInt();
        }
        sc.close();

        new Solver(N, M, bombNumbers).solve();
    }
}

class Solver {

    final int EMPTY = -1;
    int consecutiveCount;
    int bombNumbersIndex;
    int[] bombNumbers;

    public Solver(
        int N,
        int M,
        int[] bombNumbers
    ) {
        this.bombNumbersIndex = N - 1;
        this.consecutiveCount = M;
        this.bombNumbers = bombNumbers;
    }

    public void solve() {
        while (explode()) {
            applyGravity();
        }
        applyGravity();
        printAnswer();
    }

    private void printAnswer() {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i <= bombNumbersIndex; i++) {
            if (bombNumbers[i] == EMPTY) {
                continue;
            }
            answer.add(bombNumbers[i]);
        }
        System.out.println(answer.size());
        for (int number : answer) {
            System.out.println(number);
        }
    }

    private void applyGravity() {
        int[] temp = new int[bombNumbersIndex + 1];
        int tempIndex = bombNumbersIndex;
        Arrays.fill(temp, EMPTY);
        for (int i = bombNumbersIndex; i >= 0; i--) {
            if (bombNumbers[i] == EMPTY) {
                continue;
            }
            temp[tempIndex] = bombNumbers[i];
            tempIndex--;
        }
        bombNumbers = temp;
    }

    private boolean explode() {
        boolean explode = false;
        if (bombNumbers[bombNumbersIndex] == EMPTY) {
            return explode;
        }
        int curConsecutive = 1;
        int endRow = bombNumbersIndex;
        for (int i = bombNumbersIndex - 1; i >= 0; i--) {
            if (bombNumbers[i] == EMPTY) {
                break;
            }
            if (bombNumbers[i] == bombNumbers[i + 1]) {
                curConsecutive++;
            } else {
                if (consecutiveCount <= curConsecutive) {
                    explode = true;
                    removeFrom(endRow, curConsecutive);
                }
                curConsecutive = 1;
            }
            endRow = i;
        }
        if (consecutiveCount <= curConsecutive) {
            removeFrom(endRow, curConsecutive);
        }
        return explode;
    }

    private void removeFrom(int start, int removeCount) {
        for (int i = 0; i < removeCount; i++) {
            bombNumbers[start] = EMPTY;
            start++;
        }
    }
}
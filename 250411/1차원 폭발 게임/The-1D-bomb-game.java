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
        this.bombNumbers = reverseOf(bombNumbers);
    }

    private int[] reverseOf(int[] array) {
        int[] reverseArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            reverseArray[array.length - 1 - i] = array[i];
        }
        return reverseArray;
    }

    public void solve() {
        while (explode()) {
            applyGravity();
        }
        printAnswer();
    }

  
    private void printAnswer() {
        List<Integer> answer = new ArrayList<>();
        for (int i = bombNumbersIndex; i >= 0; i--) {
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
        int tempIndex = 0;
        Arrays.fill(temp, EMPTY);
        for (int number : bombNumbers) {
            if (number == EMPTY) {
                continue;
            }
            temp[tempIndex] = number;
            tempIndex++;
        }
        bombNumbers = temp;
    }

    private boolean explode() {
        boolean explode = false;
        int curConsecutive = 1;
        for (int i = 1; i <= bombNumbersIndex; i++) {
            if (bombNumbers[i] == bombNumbers[i - 1]) {
                curConsecutive++;
            } else {
                if (consecutiveCount <= curConsecutive) {
                    explode = true;
                    removeFrom(i - 1, curConsecutive);
                }
                curConsecutive = 1;
            }
        }
        if (consecutiveCount <= curConsecutive) {
            removeFrom(bombNumbersIndex, curConsecutive);
        }
        return explode;
    }

    private void removeFrom(int start, int removeCount) {
        for (int i = 0; i < removeCount; i++) {
            bombNumbers[start] = EMPTY;
            start--;
        }
    }
}
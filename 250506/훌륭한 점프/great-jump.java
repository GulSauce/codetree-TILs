import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, K;
        List<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(K, numbers).solve();
    }
}

class Solver {
    int maxJump;
    int numbersIndex;
    final int JUMP_DISABLE = -1;
    List<Integer> numbers;

    public Solver(
            int K,
            List<Integer> numbers
    ) {
        this.maxJump = K;
        this.numbersIndex = numbers.size() - 1;
        this.numbers = numbers;
    }

    public void solve() {
        int answer = Integer.MAX_VALUE;
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        for (Integer maxNumber : sortedNumbers) {
            List<Integer> maskedNumbers = new ArrayList<>(numbers);
            for (int i = 0; i <= numbersIndex; i++) {
                if (maskedNumbers.get(i) <= maxNumber) {
                    continue;
                }
                maskedNumbers.set(i, JUMP_DISABLE);
            }
            if (isCanArrive(maskedNumbers)) {
                answer = maxNumber;
                break;
            }
        }
        System.out.println(answer);
    }

    private boolean isCanArrive(List<Integer> numbers) {
        if (numbers.get(0) == JUMP_DISABLE) {
            return false;
        }
        if (numbers.get(numbersIndex) == JUMP_DISABLE) {
            return false;
        }
        boolean canArrive = false;
        int curIndex = 0;
        goPossible:
        while (true) {
            for (int jumpDist = maxJump; jumpDist >= 1; jumpDist--) {
                if (numbersIndex <= curIndex + jumpDist) {
                    canArrive = true;
                    break goPossible;
                }
                int nextIndex = curIndex + jumpDist;
                if (numbers.get(nextIndex) != JUMP_DISABLE) {
                    curIndex = nextIndex;
                    break;

                }
                if (jumpDist == 1) {
                    break goPossible;
                }
            }
        }
        return canArrive;
    }
}
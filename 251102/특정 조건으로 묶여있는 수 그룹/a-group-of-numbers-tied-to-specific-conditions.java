import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, K;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        K = toInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers.add(toInt(st.nextToken()));
        }

        new Solver(K, numbers).solve();
    }

    private static Integer toInt(String number) {
        return Integer.parseInt(number);
    }
}

class Solver {

    final int MAX_NUMBER_COUNT = 50000;
    int maxDiff;
    List<Integer> numbers;
    int[] L = new int[MAX_NUMBER_COUNT + 1];
    int[] R = new int[MAX_NUMBER_COUNT + 1];

    public Solver(int maxDiff, List<Integer> numbers) {
        this.maxDiff = maxDiff;
        this.numbers = numbers;
    }

    public void solve() {
        Collections.sort(numbers);
        doTwoPointerFromStart();
        doTwoPointerFromEnd();

        int answer = 0;
        for (int i = 0; i < numbers.size(); i++) {
            answer = Math.max(answer, L[i] + R[i + 1]);
        }
        answer = Math.max(answer, L[numbers.size() - 1]);
        answer = Math.max(answer, R[0]);
        System.out.println(answer);
    }

    private void doTwoPointerFromEnd() {
        int i = numbers.size() - 1;
        int j = numbers.size() - 1;
        for (; i >= 0; i--) {
            while (true) {
                // 유지 조건 1
                if (j < i) {
                    break;
                }
                // 유지 조건 2
                if (numbers.get(j) - numbers.get(i) <= maxDiff) {
                    R[i] = j - i + 1;
                    break;
                }
                // 유지 조건 3
                if (j == 0) {
                    break;
                }
                j--;
            }
        }
    }

    private void doTwoPointerFromStart() {
        int i = 0;
        int j = 0;
        for (; i < numbers.size(); i++) {
            while (true) {
                // 유지 조건 1
                if (i < j) {
                    break;
                }
                // 유지 조건 2
                if (numbers.get(i) - numbers.get(j) <= maxDiff) {
                    L[i] = i - j + 1;
                    break;
                }
                // 유지 조건 3
                if (j == numbers.size() - 1) {
                    break;
                }
                j++;
            }
        }
    }
}
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
        N = toInt(st);
        K = toInt(st);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers.add(toInt(st));
        }

        new Solver(K, numbers).solve();
    }

    private static Integer toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int MAX_NUMBER_COUNT = 50_000;
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
        System.out.println(answer);
    }

    private void doTwoPointerFromEnd() {
        int i = numbers.size() - 1;
        int j = numbers.size() - 1;
        int maxRange = 0;
        for (; i >= 0; i--) {
            while (true) {
                // 유지 조건 1
                if (j < i) {
                    break;
                }
                // 유지 조건 2
                if (numbers.get(j) - numbers.get(i) <= maxDiff) {
                    maxRange = Math.max(maxRange, j - i + 1);
                    R[i] = Math.max(R[i], maxRange);
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
        int maxRange = 0;
        for (; i < numbers.size(); i++) {
            while (true) {
                // 유지 조건 1
                if (i < j) {
                    break;
                }
                // 유지 조건 2
                if (numbers.get(i) - numbers.get(j) <= maxDiff) {
                    maxRange = Math.max(maxRange, i - j + 1);
                    L[i] = Math.max(L[i], maxRange);
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

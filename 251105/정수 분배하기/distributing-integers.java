import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers.add(toInt(st));
        }

        new Solver(M, numbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int MAX_NUMBER = 100_000;
    int targetCount;
    List<Integer> numbers;

    public Solver(int targetCount, List<Integer> numbers) {
        this.targetCount = targetCount;
        this.numbers = numbers;
    }

    public void solve() {
        int left = 0;
        int right = MAX_NUMBER;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(mid)) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private boolean isValid(int divNumber) {
        int count = 0;
        for (Integer number : numbers) {
            count += number / divNumber;
        }
        if (targetCount <= count) {
            return true;
        } else {
            return false;
        }
    }
}
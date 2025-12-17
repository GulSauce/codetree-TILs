import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(toInt(st));
        }

        new Solver(numbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int FAILED = -1;
    List<Integer> numbers;

    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void solve() {
        int pushCount = 0;
        for (int i = 1; i < numbers.size() - 1; i++) {
            if (numbers.get(i - 1) == 0) {
                pushCount++;
                numbers.set(i - 1, numbers.get(i - 1) ^ 1);
                numbers.set(i, numbers.get(i) ^ 1);
                numbers.set(i + 1, numbers.get(i + 1) ^ 1);
            }
        }
        if (0 <= numbers.size() - 2 && numbers.get(numbers.size() - 2) == 0) {
            pushCount++;
            numbers.set(numbers.size() - 2, numbers.get(numbers.size() - 2) ^ 1);
            numbers.set(numbers.size() - 1, numbers.get(numbers.size() - 1) ^ 1);
        }

        System.out.println(numbers.get(numbers.size() - 1) == 0 ? FAILED : pushCount);
    }
}
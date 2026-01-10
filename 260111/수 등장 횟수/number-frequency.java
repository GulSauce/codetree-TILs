import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Integer> numbers = new ArrayList<>();
        List<Integer> queryNumbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(toInt(st));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            queryNumbers.add(toInt(st));
        }

        new Solver(numbers, queryNumbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<Integer> numbers;
    List<Integer> queryNumbers;

    public Solver(
        List<Integer> numbers,
        List<Integer> queryNumbers
    ) {
        this.numbers = numbers;
        this.queryNumbers = queryNumbers;
    }

    public void solve() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Integer number : numbers) {
            hashMap.compute(number, (key, value) -> value == null ? 1 : value + 1);
        }

        for (Integer queryNumber : queryNumbers) {
            System.out.print(hashMap.getOrDefault(queryNumber, 0) + " ");
        }
    }
}
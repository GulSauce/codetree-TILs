import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(toInt(st));
        }

        new Solver(K, numbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int maxSameCount;
    List<Integer> numbers;
    HashMap<Integer, Integer> numberCountHashMap = new HashMap<>();

    public Solver(int maxSameCount, List<Integer> numbers) {
        this.maxSameCount = maxSameCount;
        this.numbers = numbers;
    }

    public void solve() {
        int i = 0;
        int j = 0;
        int answer = j - i + 1;
        numberCountHashMap.put(numbers.get(j), 1);
        for (; i < numbers.size(); i++) {
            while (true) {
                if (j < i) {
                    break;
                }
                if (maxSameCount < numberCountHashMap.get(numbers.get(j))) {
                    break;
                }
                answer = Math.max(answer, j - i + 1);
                if (j == numbers.size() - 1) {
                    break;
                }
                j++;
                numberCountHashMap.compute(numbers.get(j),
                    (key, value) -> value == null ? 1 : value + 1);
            }
            numberCountHashMap.compute(numbers.get(i), (key, value) -> value - 1);
        }
        System.out.println(answer);
    }
}
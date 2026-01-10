import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N, K;
        ArrayList<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }
        br.close();

        new Solver(K, numbers).solve();
    }
}

class Solver {

    int targetSum;
    ArrayList<Integer> numbers;

    public Solver(
        int K,
        ArrayList<Integer> numbers
    ) {
        this.targetSum = K;
        this.numbers = numbers;
    }

    public void solve() {
        int answer = 0;
        HashMap<Integer, Integer> numberCountMap = new HashMap<>();
        for (Integer number : numbers) {
            numberCountMap.compute(number, (key, value) -> value == null ? 1 : value + 1);
        }

        for (Integer number : numbers) {
            numberCountMap.put(number, numberCountMap.get(number) - 1);
            int targetDiff = targetSum - number;
            if (numberCountMap.containsKey(targetDiff)) {
                answer += numberCountMap.get(targetDiff);
            }
        }

        System.out.println(answer);
    }
}
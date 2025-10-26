import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N, K;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        new Solver(numbers, K).solve();
    }

}

class Solver {

    List<Integer> numbers;
    long targetSum;

    public Solver(
        List<Integer> numbers,
        int K
    ) {
        this.numbers = numbers;
        this.targetSum = K;
    }

    public void solve() {
        Collections.sort(numbers);
        int i = 0;
        int j = numbers.size() - 1;
        long answer = 0;
        int currentSum = numbers.get(i) + numbers.get(j);
        for (i = 0; i < numbers.size() - 1; i++) {
            while (true) {
                if (i < j && currentSum <= targetSum) {
                    answer += (j - i);
                    break;
                }
                if (j == 0) {
                    break;
                }
                currentSum -= numbers.get(j);
                j--;
                currentSum += numbers.get(j);
            }
            currentSum -= numbers.get(i);
            currentSum += numbers.get(i + 1);
        }
        System.out.println(answer);
    }
}
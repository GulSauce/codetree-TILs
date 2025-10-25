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
    int targetSum;

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
        int j = 1;
        int answer = 0;
        int curSum = numbers.get(i) + numbers.get(j);
        while (true) {
            while (true) {
                if (numbers.size() == j + 1) {
                    break;
                }
                if (i < j && targetSum <= curSum) {
                    break;
                }
                curSum -= numbers.get(j);
                j++;
                curSum += numbers.get(j);
            }
            if (i < j && targetSum <= curSum) {
                answer++;
            }
            curSum -= numbers.get(i);
            if (numbers.size() == i + 1) {
                break;
            }
            i++;
        }
        System.out.println(answer);
    }
}
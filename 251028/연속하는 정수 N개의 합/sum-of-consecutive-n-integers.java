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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        new Solver(numbers, M).solve();
    }
}

class Solver {

    List<Integer> numbers;
    int targetSum;

    public Solver(
        List<Integer> numbers,
        int M
    ) {
        this.numbers = numbers;
        this.targetSum = M;
    }

    public void solve() {
        int i = 0;
        int j = 0;
        int answer = 0;
        int sum = numbers.get(j);
        for (i = 0; i < numbers.size(); i++) {
            while (true) {
                if (j < i) {
                    break;
                }
                if (targetSum < sum) {
                    break;
                }
                if (sum == targetSum) {
                    answer++;
                    if (j == numbers.size() - 1) {
                        break;
                    }
                    j++;
                    sum += numbers.get(j);
                }
                if (sum < targetSum) {
                    if (j == numbers.size() - 1) {
                        break;
                    }
                    j++;
                    sum += numbers.get(j);
                }
            }
            sum -= numbers.get(i);
        }
        System.out.println(answer);
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, S;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        new Solver(numbers, S).solve();
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    List<Integer> numbers;
    int targetSum;

    public Solver(
        List<Integer> numbers,
        int S
    ) {
        this.numbers = numbers;
        this.targetSum = S;
    }

    public void solve() {
        int i = 0;
        int j = -1;
        int answer = NOT_ALLOCATED;
        int sum = 0;
        for (i = 0; i < numbers.size(); i++) {
            while (true) {
                if (j == numbers.size() - 1) {
                    break;
                }
                if (j < i) {
                    break;
                }
                if (targetSum <= sum) {
                    break;
                }
                if (sum < targetSum) {
                    j++;
                    answer = Math.min(answer, j - i + 1);
                    sum += numbers.get(j);
                }
            }
            sum -= numbers.get(i);
        }
        if (answer == NOT_ALLOCATED) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}

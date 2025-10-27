import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        new Solver(numbers).solve();
    }
}

class Solver {

    List<Integer> numbers;

    public Solver(
        List<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        int i = 0;
        int j = numbers.size() - 1;
        Collections.sort(numbers);
        int answer = numbers.get(j) + numbers.get(i);
        for (i = 0; i < numbers.size() - 1; i++) {
            while (true) {
                if (i < j && numbers.get(j) + numbers.get(i) < 0) {
                    break;
                }
                if (j == 0) {
                    break;
                }
                answer = Math.min(answer, numbers.get(j) + numbers.get(i));
                j--;
            }
        }
        System.out.println(answer);
    }
}
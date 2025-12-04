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

    List<Integer> numbers;

    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void solve() {
        int sum = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (sum + numbers.get(i) < numbers.get(i)) {
                sum = numbers.get(i);
            } else {
                sum += numbers.get(i);
            }
        }
        System.out.println(sum);
    }
}
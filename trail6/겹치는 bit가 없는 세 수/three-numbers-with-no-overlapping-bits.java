import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
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
        int answer = 0;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                for (int k = j + 1; k < numbers.size(); k++) {
                    int sum = numbers.get(i) + numbers.get(j) + numbers.get(k);
                    int bit = numbers.get(i) | numbers.get(j) | numbers.get(k);
                    if (sum == bit) {
                        answer = Math.max(answer, sum);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
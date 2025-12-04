import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Integer> numbers = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
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
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                long number1 = Long.parseLong(a.toString() + b.toString());
                long number2 = Long.parseLong(b.toString() + a.toString());
                return Long.compare(number2, number1);
            }
        });
        for (Integer number : numbers) {
            System.out.print(number);
        }
    }
}
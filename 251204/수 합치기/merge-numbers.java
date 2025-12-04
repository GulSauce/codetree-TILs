import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
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
    PriorityQueue<Integer> pq;

    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void solve() {
        pq = new PriorityQueue<>(numbers);
        int answer = 0;
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            answer += first + second;
            pq.add(first + second);
        }
        System.out.println(answer);
    }
}
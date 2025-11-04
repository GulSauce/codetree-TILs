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
        List<Integer> queries = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(toInt(st));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            queries.add(toInt(st));
        }

        new Solver(numbers, queries).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_FOUND = -1;
    List<Integer> numbers;
    List<Integer> queries;

    public Solver(List<Integer> numbers, List<Integer> queries) {
        this.numbers = numbers;
        this.queries = queries;
    }

    public void solve() {
        for (Integer query : queries) {
            int result = binarySearch(query);
            if (result == NOT_FOUND) {
                System.out.println(-1);
            } else {
                System.out.println(result + 1);
            }
        }
    }

    private int binarySearch(int query) {
        int left = 0;
        int right = numbers.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int found = numbers.get(mid);
            if (query < found) {
                right = mid - 1;
            }
            if (query == found) {
                return mid;
            }
            if (found < query) {
                left = mid + 1;
            }
        }
        return NOT_FOUND;
    }
}
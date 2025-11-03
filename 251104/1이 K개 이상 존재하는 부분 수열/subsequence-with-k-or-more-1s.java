import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, K;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        K = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(toInt(st));
        }

        new Solver(K, numbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int minExistCount;
    List<Integer> numbers;
    int oneCount;

    public Solver(int minExistCount, List<Integer> numbers) {
        this.minExistCount = minExistCount;
        this.numbers = numbers;
    }

    public void solve() {
        int i = 0;
        int j = 0;
        if (numbers.get(j) == 1) {
            oneCount++;
        }
        int answer = NOT_ALLOCATED;
        for (; i < numbers.size(); i++) {
            while (true) {
                if (j < i) {
                    break;
                }
                if (minExistCount <= oneCount) {
                    answer = Math.min(answer, j - i + 1);
                    break;
                }
                if (j == numbers.size() - 1) {
                    break;
                }
                j++;
                if (numbers.get(j) == 1) {
                    oneCount++;
                }
            }
            if (numbers.get(i) == 1) {
                oneCount--;
            }
        }
        if (answer == NOT_ALLOCATED) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
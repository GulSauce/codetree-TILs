import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        new Solver(numbers).solve();
    }

}

class Solver {

    final int NOT_ALLOCATED = Integer.MIN_VALUE;
    List<Integer> points;
    int[] firstOccur = new int[7];

    public Solver(
        List<Integer> points
    ) {
        this.points = points;
    }

    public void solve() {
        Arrays.fill(firstOccur, NOT_ALLOCATED);
        setFirstOccur();

        int answer = 0;
        long sum = 0;
        for (int i = 0; i < points.size(); i++) {
            sum += points.get(i);
            int mod = (int) (sum % 7);
            if (mod == 0) {
                answer = Math.max(answer, i + 1);
            } else {
                answer = Math.max(answer, i - firstOccur[mod]);
            }
        }
        System.out.println(answer);
    }

    private void setFirstOccur() {
        long sum = 0;
        for (int i = 0; i < points.size(); i++) {
            sum += points.get(i);
            int mod = (int) (sum % 7);
            if (firstOccur[mod] != NOT_ALLOCATED) {
                continue;
            }
            firstOccur[mod] = i;
        }
    }
}
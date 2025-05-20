import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(-1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }
        br.close();

        new Solver(numbers).solve();
    }
}

class Solver {

    ArrayList<Integer> numbers;
    int[] leftPrefixSum;
    int[] rightPrefixSum;

    public Solver(
        ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
        this.leftPrefixSum = new int[numbers.size()];
        this.rightPrefixSum = new int[numbers.size() + 1];
    }

    public void solve() {
        for (int i = 1; i < numbers.size(); i++) {
            leftPrefixSum[i] = Math.max(leftPrefixSum[i - 1], numbers.get(i));
        }
        for (int i = numbers.size() - 1; i >= 1; i--) {
            rightPrefixSum[i] = Math.max(rightPrefixSum[i + 1], numbers.get(i));
        }

        int answer = 0;
        for (int i = 3; i < numbers.size() - 2; i++) {
            answer = Math.max(answer,
                leftPrefixSum[i - 2] + numbers.get(i) + rightPrefixSum[i + 2]);
        }
        System.out.println(answer);
    }
}

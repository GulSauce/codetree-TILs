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
            leftPrefixSum[i] = leftPrefixSum[i - 1] + numbers.get(i);
        }
        for (int i = numbers.size() - 1; i >= 1; i--) {
            rightPrefixSum[i] = rightPrefixSum[i + 1] + numbers.get(i);
        }
        int minSum = Integer.MAX_VALUE;
        for (int i = 1; i < numbers.size(); i++) {
            for (int j = i + 2; j < numbers.size(); j++) {
                for (int k = j + 2; k < numbers.size(); k++) {
                    int leftSum = leftPrefixSum[i - 1];
                    int midSum1 = leftPrefixSum[j - 1] - leftPrefixSum[i];
                    int midSum2 = leftPrefixSum[k - 1] - leftPrefixSum[j];
                    int rightSum = rightPrefixSum[k + 1];
                    int curSum = leftSum + midSum1 + midSum2 + rightSum;
                    minSum = Math.min(minSum, curSum);
                }
            }
        }
        System.out.println(leftPrefixSum[numbers.size() - 1] - minSum);
    }
}

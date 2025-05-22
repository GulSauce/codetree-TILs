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

    long targetSum = 0;

    ArrayList<Integer> numbers;
    int[] leftPrefixCount;
    int[] rightPrefixCount;

    public Solver(
        ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
        this.leftPrefixCount = new int[numbers.size()];
        this.rightPrefixCount = new int[numbers.size() + 1];
    }

    public void solve() {
        long sum = 0;
        for (int i = 1; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }
        if (sum % 4 != 0) {
            System.out.println(0);
            return;
        }
        targetSum = sum / 4;
        setLeftPrefixCount();
        setRightPrefixCount();

        long answer = 0;
        for (int i = 1; i < numbers.size() - 1; i++) {
            answer += (long) leftPrefixCount[i] * rightPrefixCount[i + 1];
        }
        System.out.println(answer);
    }

    private void setLeftPrefixCount() {
        int cnt = 0;
        long sum = 0;
        for (int i = 1; i < numbers.size(); i++) {
            sum += numbers.get(i);
            if (sum == 2 * targetSum) {
                leftPrefixCount[i] = cnt;
            }
            if (sum == targetSum) {
                cnt++;
            }
        }
    }

    private void setRightPrefixCount() {
        int cnt = 0;
        long sum = 0;
        for (int i = numbers.size() - 1; i >= 1; i--) {
            sum += numbers.get(i);
            if (sum == 2 * targetSum) {
                rightPrefixCount[i] = cnt;
            }
            if (sum == targetSum) {
                cnt++;
            }
        }
    }
}
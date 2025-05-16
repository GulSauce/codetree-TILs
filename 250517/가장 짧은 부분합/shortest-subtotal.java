import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N, S;
        ArrayList<Integer> numbers = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }
        br.close();

        new Solver(S, numbers).solve();
    }
}

class Solver {

    int targetMinSum;
    ArrayList<Integer> numbers;

    public Solver(
        int targetMinSum,
        ArrayList<Integer> numbers
    ) {
        this.targetMinSum = targetMinSum;
        this.numbers = numbers;
    }

    public void solve() {
        int answer = Integer.MAX_VALUE;
        int prevJ = -1;
        int prevSum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            while (true) {
                int curJ = prevJ + 1;
                if (numbers.size() <= curJ) {
                    break;
                }
                if (targetMinSum <= prevSum) {
                    break;
                }
                int curSum = prevSum + numbers.get(curJ);
                prevSum = curSum;
                prevJ = curJ;
            }

            if (targetMinSum <= prevSum) {
                answer = Math.min(answer, prevJ - i + 1);
            }
            prevSum = prevSum - numbers.get(i);
        }
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}

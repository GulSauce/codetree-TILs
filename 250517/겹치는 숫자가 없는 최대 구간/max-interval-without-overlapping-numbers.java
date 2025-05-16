import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<Integer> numbers = new ArrayList<>();
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

    int[] numberCount = new int[100_000 + 1];
    ArrayList<Integer> numbers;

    public Solver(
        ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        int prevJ = -1;
        int answer = 0;
        for (int i = 0; i < numbers.size(); i++) {
            while (true) {
                int curJ = prevJ + 1;
                if (numbers.size() <= curJ) {
                    break;
                }
                int nextNumberCount = numberCount[numbers.get(curJ)] + 1;
                if (nextNumberCount == 2) {
                    break;
                }
                numberCount[numbers.get(curJ)] = nextNumberCount;
                prevJ = curJ;
            }

            answer = Math.max(answer, prevJ - i + 1);
            numberCount[numbers.get(i)]--;
        }
        System.out.println(answer);
    }
}
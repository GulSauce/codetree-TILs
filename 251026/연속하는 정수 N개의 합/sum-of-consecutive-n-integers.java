import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        long N, M;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        new Solver(M, numbers).solve();

    }

}

class Solver {

    long targetSum;
    List<Integer> numbers;

    public Solver(
        long M,
        List<Integer> numbers
    ) {
        this.targetSum = M;
        this.numbers = numbers;
    }

    public void solve() {
        int i = 0;
        int j = -1;
        int answer = 0;
        int curSum = 0;
        while (true) {
            while (true) {
                // 갱신 실패 조건 1: 이전 인덱스가 이미 끝이였음
                if (numbers.size() <= j + 1) {
                    break;
                }
                // 갱신 실패 조건 2: 더하기 전부터 이미 목표 수를 넘었음
                if (targetSum <= curSum) {
                    break;
                }
                j++;
                curSum += numbers.get(j);
            }
            // 목표 달성
            if (curSum == targetSum) {
                answer++;
            }
            curSum -= numbers.get(i);
            i++;
            if (i == numbers.size()) {
                break;
            }
        }
        System.out.println(answer);
    }
}
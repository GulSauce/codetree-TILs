import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m;
        List<Integer> numbers = new ArrayList<>();
        List<Integer> limits = new ArrayList<>();
        numbers.add(-1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        m = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers.add(toInt(st));
        }

        if (m > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                limits.add(toInt(st));
            }
        }

        new Solver(numbers, limits).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final long NOT_ALLOCATED = Long.MAX_VALUE;

    List<Integer> limits;
    List<Integer> numbers;
    HashSet<Integer> limitsHashSet;

    public Solver(List<Integer> numbers, List<Integer> limits) {
        this.limits = limits;
        this.numbers = numbers;
    }

    public void solve() {
        int N = numbers.size();
        // 롤링 배열: prev = dp[i-1][...][...], cur = dp[i][...][...]
        // (j, p) 차원만 유지. j ∈ [0, N), p ∈ {0,1}
        long[][] prev = new long[N][2];
        long[][] cur = new long[N][2];
        for (long[] arr : prev) Arrays.fill(arr, NOT_ALLOCATED);

        limitsHashSet = new HashSet<>(limits);
        // dp[1][0][p] 초기화
        //   p=0: 1번 사람이 1번 카드를 가짐, p=1: 2번 사람이 1번 카드를 가짐
        prev[0][0] = 0;
        if (!limitsHashSet.contains(1)) {
            prev[0][1] = 0;
        }
        for (int curAppend = 2; curAppend < N; curAppend++) {
            int blue = curAppend - 1;
            for (long[] arr : cur) Arrays.fill(arr, NOT_ALLOCATED);
            for (int green = 0; green < blue; green++) {
                for (int p = 0; p < 2; p++) {
                    if (prev[green][p] == NOT_ALLOCATED) {
                        continue;
                    }
                    long source = prev[green][p];
                    // 같은 사람(p)이 이어서 curAppend를 가져감
                    if (!(p == 1 && limitsHashSet.contains(curAppend))) {
                        long newCost =
                            source + Math.abs(numbers.get(curAppend) - numbers.get(blue));
                        if (newCost < cur[green][p]) {
                            cur[green][p] = newCost;
                        }
                    }
                    // 다른 사람(1-p)이 curAppend를 가져감 (이전엔 green 위치)
                    int newP = 1 - p;
                    if (!(newP == 1 && limitsHashSet.contains(curAppend))) {
                        long extraCost = green == 0 ? 0
                            : Math.abs(numbers.get(curAppend) - numbers.get(green));
                        long newCost = source + extraCost;
                        if (newCost < cur[blue][newP]) {
                            cur[blue][newP] = newCost;
                        }
                    }
                }
            }
            long[][] tmp = prev;
            prev = cur;
            cur = tmp;
        }
        long answer = Long.MAX_VALUE;
        int last = N - 1;
        for (int j = 0; j < last; j++) {
            for (int p = 0; p < 2; p++) {
                if (prev[j][p] != NOT_ALLOCATED) {
                    answer = Math.min(answer, prev[j][p]);
                }
            }
        }
        // n=1 인 경우: 루프 미실행, prev = dp[1][...]
        if (last == 1) {
            answer = 0;
        }
        System.out.println(answer);
    }
}

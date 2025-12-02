import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, K;
        List<Integer> coins = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        K = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            coins.add(toInt(st));
        }
        new Solver(K, coins).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int targetSum;
    List<Integer> coins;

    public Solver(int targetSum, List<Integer> coins) {
        this.targetSum = targetSum;
        this.coins = coins;
    }

    public void solve() {
        int curRemains = targetSum;
        int answer = 0;
        for (int i = coins.size() - 1; i >= 0; i--) {
            answer += curRemains / coins.get(i);
            curRemains %= coins.get(i);
        }
        System.out.println(answer);
    }
}
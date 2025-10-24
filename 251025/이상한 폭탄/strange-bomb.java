import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N, K;
        List<Integer> bombs = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            bombs.add(parseInt(st.nextToken()));
        }

        new Solver(bombs, K).solve();
    }

    private static int parseInt(String numberString) {
        return Integer.parseInt(numberString);
    }

}

class Solver {

    final int NOT_ALLOCATED = Integer.MIN_VALUE;
    List<Integer> bombs;
    int maxDist;
    int[] rightIndexOf = new int[200_000];
    HashMap<Integer, Integer> lastIndexHashMap = new HashMap<>();

    public Solver(
        List<Integer> bombs,
        int K
    ) {
        this.bombs = bombs;
        this.maxDist = K;
    }

    public void solve() {
        int answer = -1;
        Arrays.fill(rightIndexOf, NOT_ALLOCATED);

        for (int i = bombs.size() - 1; i >= 0; i--) {
            int number = bombs.get(i);
            if (lastIndexHashMap.containsKey(number)) {
                int dist = lastIndexHashMap.get(number) - i;
                if (dist <= maxDist) {
                    answer = Math.max(answer, number);
                }
            }
            lastIndexHashMap.put(number, i);
        }

        System.out.println(answer);
    }
}
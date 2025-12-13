import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Bomb> bombs = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            bombs.add(new Bomb(toInt(st), toInt(st)));
        }

        new Solver(bombs).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int MAX_TIME = 10_000;
    List<Bomb> bombs;
    PriorityQueue<Bomb> bombPQ;

    public Solver(List<Bomb> bombs) {
        this.bombs = bombs;
    }

    public void solve() {
        Collections.sort(bombs, (a, b) -> {
            // 왼쪽이 오른쪽보다 크면 위치가 바뀐다
            // b가 a보다 크면 위치가 바뀐다: 뒤가 앞으로 온다
            return Integer.compare(b.timeLimit, a.timeLimit);
        });
        bombPQ = new PriorityQueue<>((a, b) -> {
            return Integer.compare(b.score, a.score);
        });

        int bombIndex = 0;
        int answer = 0;
        for (int t = MAX_TIME; t >= 0; t--) {
            while (true) {
                if (bombIndex >= bombs.size()) {
                    break;
                }
                if (t >= bombs.get(bombIndex).timeLimit) {
                    break;
                }
                bombPQ.add(bombs.get(bombIndex));
                bombIndex++;
            }
            if (!bombPQ.isEmpty()) {
                answer += bombPQ.poll().score;
            }
        }
        System.out.println(answer);
    }
}

class Bomb {

    int score;
    int timeLimit;

    public Bomb(int score, int timeLimit) {
        this.score = score;
        this.timeLimit = timeLimit;
    }
}
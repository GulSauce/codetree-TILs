import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int C, N;
        List<RedStone> redStones = new ArrayList<>();
        List<BlackStone> blackStones = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = toInt(st);
        N = toInt(st);

        for (int i = 1; i <= C; i++) {
            st = new StringTokenizer(br.readLine());
            redStones.add(new RedStone(i, toInt(st)));
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            blackStones.add(new BlackStone(i, toInt(st), toInt(st)));
        }

        new Solver(redStones, blackStones).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<RedStone> redStones;
    List<BlackStone> blackStones;

    public Solver(List<RedStone> redStones, List<BlackStone> blackStones) {
        this.redStones = redStones;
        this.blackStones = blackStones;
    }

    public void solve() {
        Collections.sort(redStones, (a, b) -> Integer.compare(a.number, b.number));
        Collections.sort(blackStones, (a, b) -> {
            if (a.B == b.B) {
                return Integer.compare(a.A, b.A);
            }
            return Integer.compare(a.B, b.B);
        });

        int answer = 0;
        int blackStoneIndex = 0;
        for (RedStone redStone : redStones) {
            while (true) {
                if (blackStoneIndex >= blackStones.size()) {
                    break;
                }
                BlackStone blackStone = blackStones.get(blackStoneIndex);
                if (blackStone.A <= redStone.number
                    && redStone.number <= blackStone.B) {
                    blackStoneIndex++;
                    answer++;
                    break;
                } else if (redStone.number < blackStone.A) {
                    break;
                } else {
                    blackStoneIndex++;
                }
            }
        }
        System.out.println(answer);
    }
}

class RedStone {

    int id;
    int number;

    public RedStone(int id, int number) {
        this.id = id;
        this.number = number;
    }
}

class BlackStone {

    int id;
    int A;
    int B;

    public BlackStone(int id, int a, int b) {
        this.id = id;
        A = a;
        B = b;
    }
}
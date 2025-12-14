import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

    TreeSet<RedStone> redStoneTreeSet;

    public Solver(List<RedStone> redStones, List<BlackStone> blackStones) {
        this.redStones = redStones;
        this.blackStones = blackStones;
    }

    public void solve() {
        redStoneTreeSet = new TreeSet<>((a, b) -> Integer.compare(a.number, b.number));
        redStoneTreeSet.addAll(redStones);
        Collections.sort(blackStones, (a, b) -> Integer.compare(a.B, b.B));

        int answer = 0;
        for (BlackStone blackStone : blackStones) {
            RedStone found = redStoneTreeSet.ceiling(new RedStone(0, blackStone.A));
            if (found == null) {
                continue;
            }
            if (found.number > blackStone.B) {
                continue;
            }
            redStoneTreeSet.remove(found);
            answer++;
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
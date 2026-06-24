import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        new Solver(n).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int R = 10_007;
    final int HEIGHT = 3;
    final int WIDTH;

    final int FULL = (1 << HEIGHT) - 1;
    int[][] dp;

    HashMap<Integer, List<Integer>> matchHashSet = new HashMap<>();

    public Solver(int WIDTH) {
        this.WIDTH = WIDTH;
        this.dp = new int[WIDTH + 1][FULL + 1];
    }

    public void solve() {
        for (int bitMask = 0; bitMask <= FULL; bitMask++) {
            matchHashSet.put(bitMask, new ArrayList<>());
            setMatchHashSet(bitMask, 0, 1);
        }
        dp[0][0] = 1;
        for (int i = 0; i < WIDTH; i++) {
            for (int bitMask = 0; bitMask <= FULL; bitMask++) {
                List<Integer> matchValues = matchHashSet.get(bitMask);
                for (int value : matchValues) {
                    dp[i + 1][value] += dp[i][bitMask];
                    dp[i + 1][value] %= R;
                }
            }
        }
        System.out.println(dp[WIDTH][0]);
    }

    private void setMatchHashSet(int prev, int curValue, int curH) {
        if (curH == HEIGHT + 1) {
            matchHashSet.get(prev).add(curValue);
            return;
        }
        // 이미 블록이 존재함
        if (((prev >> (HEIGHT - curH) & 1)) == 1) {
            setMatchHashSet(prev, curValue, curH + 1);
        }
        // 블록이 존재하지 않음
        else {
            if (curH <= HEIGHT - 1) {
                setMatchHashSet(prev, curValue | (1 << (HEIGHT - curH)), curH + 1);
                if (((prev >> (HEIGHT - (curH + 1))) & 1) == 0) {
                    setMatchHashSet(prev, curValue, curH + 2);
                }
            } else {
                setMatchHashSet(prev, curValue | (1 << (HEIGHT - curH)), curH + 1);
            }
        }
    }
}
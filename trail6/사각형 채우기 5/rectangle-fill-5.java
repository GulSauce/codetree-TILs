import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        new Solver(n).solve();
    }
}

class Solver {

    int[][] dp;
    final int R = 10_007;
    final int WIDTH;
    final int HEIGHT = 4;
    final int FULL = (1 << HEIGHT) - 1;
    HashMap<Integer, List<Integer>> matchHashMap = new HashMap<>();

    public Solver(int WIDTH) {
        this.WIDTH = WIDTH;
        this.dp = new int[WIDTH + 1][FULL + 1];
    }

    public void solve() {
        for (int bitMask = 0; bitMask <= FULL; bitMask++) {
            matchHashMap.put(bitMask, new ArrayList<>());
            setMatchHashMap(bitMask, 0, 1);
        }

        dp[0][0] = 1;
        for (int i = 0; i < WIDTH; i++) {
            for (int bitMask = 0; bitMask <= FULL; bitMask++) {
                List<Integer> matchValues = matchHashMap.get(bitMask);
                for (int matchValue : matchValues) {
                    dp[i + 1][matchValue] += dp[i][bitMask];
                    dp[i + 1][matchValue] %= R;
                }
            }
        }

        System.out.println(dp[WIDTH][0]);
    }

    private void setMatchHashMap(int target, int curValue, int height) {
        if (height == HEIGHT + 1) {
            matchHashMap.get(target).add(curValue);
            return;
        }
        int hDiff = HEIGHT - height;
        if (height <= HEIGHT - 1) {
            if ((target >> hDiff & 1) == 0 && (target >> (hDiff - 1) & 1) == 0) {
                setMatchHashMap(target, curValue, height + 2);
            }
        }
        if ((target >> hDiff & 1) == 0) {
            setMatchHashMap(target, curValue | 1 << hDiff, height + 1);
        } else {
            setMatchHashMap(target, curValue, height + 1);
        }
    }
}